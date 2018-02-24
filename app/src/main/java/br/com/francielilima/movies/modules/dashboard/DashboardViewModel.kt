package br.com.francielilima.movies.modules.dashboard

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.util.Log
import br.com.francielilima.movies.modules.details.DetailsActivity
import br.com.francielilima.movies.utils.Constants
import br.com.francielilima.movies.utils.network.ApiDataManager
import br.com.francielilima.movies.utils.network.pokos.DiscoverMovies
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DashboardViewModel: ViewModel() {

    var discoverMoviesData = MutableLiveData<DiscoverMovies>()

    //region DataManager

    fun fetchDiscoverMovies() {
        ApiDataManager.discoverMoviesObservable()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    discoverMoviesData.value = it
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
}
