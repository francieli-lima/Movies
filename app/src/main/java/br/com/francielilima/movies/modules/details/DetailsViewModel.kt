package br.com.francielilima.movies.modules.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.os.Bundle
import android.util.Log
import br.com.francielilima.movies.models.Movie
import br.com.francielilima.movies.utils.Constants
import br.com.francielilima.movies.utils.network.ApiDataManager
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DetailsViewModel: ViewModel() {

    private var movieId: Long = 0L
        set(value) {
            field = value

            fetchMovieDetails()
        }

    var movieDetailsData = MutableLiveData<Movie>()
    var extras: Bundle? = null
        set(value) {
            field = value
            movieId = value?.getLong(Constants.IntentExtras.MOVIE_ID) ?: 0L
        }

    //region DataManager

    fun fetchMovieDetails() {
        ApiDataManager.getMoviesDetailsObservable(movieId)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    movieDetailsData.value = it
                }, {
                    Log.e("Error", it.message, it)
                })
    }

    //endregion

    //region Router

    //endregion
}