package br.com.francielilima.movies.modules.categories

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import br.com.francielilima.movies.models.Genre
import br.com.francielilima.movies.models.Movie
import br.com.francielilima.movies.modules.details.DetailsActivity
import br.com.francielilima.movies.utils.Constants
import br.com.francielilima.movies.utils.enums.MovieResultCategory
import br.com.francielilima.movies.utils.extensions.withGenres
import br.com.francielilima.movies.utils.network.ApiDataManager
import br.com.francielilima.movies.utils.network.pokos.GenreResults
import br.com.francielilima.movies.utils.network.pokos.MovieResults
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class CategoryViewModel: ViewModel() {

    private var genres: List<Genre> = listOf()

    private var position: Int = 0
        set(value) {
            field = value

            fetchMovieDetails()
        }

    private var page = 1L
    private var totalPages = 1L

    var movieDetailsData = MutableLiveData<MovieResults>()
    var categoryData = MutableLiveData<MovieResultCategory>()

    var extras: Bundle? = null
        set(value) {
            field = value
            position = value?.getInt(Constants.IntentExtras.MOVIE_POSITION) ?: 0
        }

    //region DataManager

    fun fetchMovieDetails() {
        var count = 0
        if (page <= totalPages) {
            categoryData.value = MovieResultCategory.from(position)

            val request: Observable<MovieResults>? = when (position) {
                MovieResultCategory.POPULAR.ordinal -> ApiDataManager.getPopularMoviesObservable(page)
                MovieResultCategory.NOW_PLAYING.ordinal -> ApiDataManager.getNowPlayingMoviesObservable(page)
                MovieResultCategory.TOP_RATED.ordinal -> ApiDataManager.getTopRatedMoviesObservable(page)
                else -> ApiDataManager.discoverMoviesObservable(page)
            }

            Observable.merge(
                    ApiDataManager.getMovieGenresObservable(),
                    request)
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe({
                        count++

                        when (count) {
                            1 -> genres = (it as GenreResults).genres
                            else -> {
                                val movieResults = it as MovieResults

                                page = movieResults.page + 1
                                totalPages = movieResults.totalPages
                                movieDetailsData.value = listOf<MovieResults>(movieResults).withGenres(genres)[0]
                            }
                        }
                    }, {
                        Log.e("Error", it.message, it)
                    })
        }
    }

    //endregion

    //region Router

    fun onMovieClicked(movieId: Long, context: Context) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra(Constants.IntentExtras.MOVIE_ID, movieId)
        context.startActivity(intent)
    }

    //endregion
}