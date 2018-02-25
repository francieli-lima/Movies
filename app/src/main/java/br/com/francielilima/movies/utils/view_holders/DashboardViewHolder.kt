package br.com.francielilima.movies.utils.view_holders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.francielilima.movies.utils.adapters.MovieAdapter
import br.com.francielilima.movies.utils.interfaces.RecyclerViewClickListener
import br.com.francielilima.movies.utils.network.pokos.MovieResults
import kotlinx.android.synthetic.main.row_dashboard.view.*
import android.support.v7.widget.LinearLayoutManager
import br.com.francielilima.movies.R
import br.com.francielilima.movies.utils.enums.MovieResultCategory


class DashboardViewHolder(itemView: View, listener: RecyclerViewClickListener, private val context: Context): RecyclerView.ViewHolder(itemView) {

    private var adapter: MovieAdapter = MovieAdapter(context, listener)

    init {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        itemView.recyclerView.adapter = adapter
        itemView.recyclerView.layoutManager = layoutManager
    }

    var movies: MovieResults? = null
        set(value) {
            field = value

            itemView.textViewTitle.text = when (adapterPosition) {
                MovieResultCategory.DISCOVER.ordinal -> context.getString(R.string.discover)
                MovieResultCategory.TOP_RATED.ordinal -> context.getString(R.string.top_ratings)
                MovieResultCategory.POPULAR.ordinal -> context.getString(R.string.popular)
                else -> context.getString(R.string.now_playing)
            }

            value?.movies?.let {
                adapter.items = it
            }
        }
}