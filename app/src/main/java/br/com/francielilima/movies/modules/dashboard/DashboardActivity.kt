package br.com.francielilima.movies.modules.dashboard

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.transition.TransitionManager
import android.view.View
import br.com.francielilima.movies.R
import br.com.francielilima.movies.utils.adapters.DashboardAdapter
import br.com.francielilima.movies.utils.interfaces.RecyclerViewClickListener
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AlertDialog
import br.com.francielilima.movies.utils.extensions.isThereInternet


class DashboardActivity: AppCompatActivity(), RecyclerViewClickListener {

    private lateinit var adapter: DashboardAdapter

    private var viewModel: DashboardViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)

        setup()
    }

    //region Private

    private fun setup() {
        showLoading()

        registerObservables()
        setupRecyclerView()
        fetchMovies()
    }

    private fun setupRecyclerView() {
        adapter = DashboardAdapter(this, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun registerObservables() {
        viewModel?.discoverMoviesData?.observe(this, Observer {
            it?.let {
                hideLoading()

                adapter.items = it
            }
        })
    }

    private fun showLoading() {
        viewLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        TransitionManager.beginDelayedTransition(container)

        viewLoading.visibility = View.GONE
    }

    private fun showError(message: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        builder.setTitle(getString(R.string.problem))
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.retry), DialogInterface.OnClickListener { dialog, _ ->
                    fetchMovies()
                })
                .show()
    }

    //endregion

    //region ViewModel Calls

    private fun fetchMovies() {
        if (isThereInternet()) viewModel?.fetchDiscoverMovies() else showError(getString(R.string.no_internet_sierra_madre))
    }
    //endregion

    override fun onRecyclerViewItemClicked(movieId: Any) {
        viewModel?.onMovieClicked(movieId as Long, this)
    }
}