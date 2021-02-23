package br.com.francielilima.movies.modules.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.util.Log
import br.com.francielilima.movies.models.Genre
import br.com.francielilima.movies.models.Movie
import br.com.francielilima.movies.modules.categories.CategoryActivity
import br.com.francielilima.movies.modules.details.DetailsActivity
import br.com.francielilima.movies.utils.Constants
import br.com.francielilima.movies.utils.extensions.withGenres
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
                ApiDataManager.discoverMoviesObservable(1),
                ApiDataManager.getTopRatedMoviesObservable(1),
                ApiDataManager.getPopularMoviesObservable(1),
                ApiDataManager.getNowPlayingMoviesObservable(1))
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    count++

                    when (count) {
                        1 -> genres = (it as GenreResults).genres
                        5 -> {
                            movies.add(it as MovieResults)
                            discoverMoviesData.value = movies.withGenres(genres)
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

    fun onSeeAllClicked(position: Int, context: Context) {
        val intent = Intent(context, CategoryActivity::class.java)
        intent.putExtra(Constants.IntentExtras.MOVIE_POSITION, position)
        context.startActivity(intent)
    }

    //endregion
}
