package br.com.francielilima.movies.modules.dashboard

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.util.Log
import br.com.francielilima.movies.models.Genre
import br.com.francielilima.movies.models.Movie
import br.com.francielilima.movies.modules.details.DetailsActivity
import br.com.francielilima.movies.utils.Constants
import br.com.francielilima.movies.utils.network.ApiDataManager
import br.com.francielilima.movies.utils.network.pokos.GenreResults
import br.com.francielilima.movies.utils.network.pokos.MovieResults
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DashboardViewModel: ViewModel() {

    private var genres: List<Genre> = listOf()

    var latestMovieData = MutableLiveData<Movie>()
    var discoverMoviesData = MutableLiveData<List<MovieResults>>()

    //region DataManager

    fun fetchDiscoverMovies() {
        var movies = arrayListOf<MovieResults>()
        var count = 0

        Observable.merge(
                ApiDataManager.getMovieGenresObservable(),
                ApiDataManager.discoverMoviesObservable(),
                ApiDataManager.getTopRatedMoviesObservable(),
                ApiDataManager.getPopularMoviesObservable(),
                ApiDataManager.getNowPlayingMoviesObservable())
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    count++

                    when (count) {
                        1 -> genres = (it as GenreResults).genres
                        5 -> {
                            movies.add(it as MovieResults)
                            discoverMoviesData.value = setGenres(movies)
                        }
                        else -> movies.add(it as MovieResults)
                    }
                }, {
                    Log.e("Error", it.message, it)
                })
    }

    //endregion

    //region Router

    fun onMovieClicked(movieId: Long, context: Context) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra(Constants.IntentExtras.MOVIE_ID, movieId)
        context.startActivity(intent)
    }

    //endregion

    //region Private

    private fun setGenres(results: List<MovieResults>): List<MovieResults> {
        return results.map {
            it.movies.map { movie ->
                val genres = arrayListOf<Genre>()
                movie.genreIds.forEach { id ->
                    genres.addAll(this.genres.filter { it.id == id })
                }

                movie.genres = genres

                movie
            }
            it
        }
    }

    //endregion
}
