package br.com.francielilima.movies.modules.dashboard

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.com.francielilima.movies.R
import br.com.francielilima.movies.utils.Constants
import br.com.francielilima.movies.utils.adapters.DashboardAdapter
import br.com.francielilima.movies.utils.interfaces.RecyclerViewClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

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
                adapter.items = it
            }
        })
    }

    //endregion

    //region ViewModel Calls

    private fun fetchMovies() {
        viewModel?.fetchDiscoverMovies()
    }
    //endregion

    override fun onRecyclerViewItemClicked(movieId: Any) {
        viewModel?.onMovieClicked(movieId as Long, this)
    }
}