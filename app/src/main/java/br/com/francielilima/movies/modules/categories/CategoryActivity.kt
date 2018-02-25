package br.com.francielilima.movies.modules.categories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import br.com.francielilima.movies.R
import br.com.francielilima.movies.utils.adapters.MovieAdapter
import br.com.francielilima.movies.utils.enums.ClickCategory
import br.com.francielilima.movies.utils.extensions.isThereInternet
import br.com.francielilima.movies.utils.interfaces.RecyclerViewClickListener
import kotlinx.android.synthetic.main.activity_category.*
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.support.v7.widget.RecyclerView
import android.view.View

class CategoryActivity: AppCompatActivity(), RecyclerViewClickListener {

    private var viewModel: CategoryViewModel? = null
    private lateinit var adapter: MovieAdapter
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        viewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
        adapter = MovieAdapter(this, this)

        if (isThereInternet()) {
            showLoading()

            viewModel?.extras = intent.extras
            setup()
        }
    }

    //region Private

    private fun setup() {
        registerObservables()
        setupRecyclerView()
    }

    private fun registerObservables() {
        viewModel?.movieDetailsData?.observe(this, Observer {
            it?.let {
                hideLoading()
                adapter.items = ArrayList(it.movies)
            }
        })

        viewModel?.categoryData?.observe(this, Observer {
            it?.let {
                textViewTitle.text = getString(it.title)
            }
        })
    }

    private fun setupRecyclerView() {
        layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener)
    }


    private fun showLoading() {
        viewLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        viewLoading.visibility = View.GONE
    }

    //endregion

    override fun onRecyclerViewItemClicked(value: Any, category: ClickCategory) {
        viewModel?.onMovieClicked(value as Long, this)
    }

    private val recyclerViewOnScrollListener = object: RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }

        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

            if (viewLoading.visibility == View.GONE) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {
                    showLoading()
                    viewModel?.fetchMovieDetails()
                }
            }
        }
    }
}
