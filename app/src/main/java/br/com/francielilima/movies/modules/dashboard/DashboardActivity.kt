package br.com.francielilima.movies.modules.dashboard

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.francielilima.movies.R

class DashboardActivity: AppCompatActivity() {

    var viewModel: DashboardViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)

        registerObservables()
        fetchMovies()
    }

    //region Private

    private fun registerObservables(){
        viewModel?.discoverMoviesData?.observe(this, Observer {
            it?.let {
                //TODO
            }
        })
    }

    //endregion

    //region ViewModel Calls

    private fun fetchMovies() {
        viewModel?.fetchDiscoverMovies()
    }
    //endregion
}