package br.com.francielilima.movies.utils.view_holders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import br.com.francielilima.movies.utils.adapters.MovieAdapter
import br.com.francielilima.movies.utils.interfaces.RecyclerViewClickListener
import br.com.francielilima.movies.utils.network.pokos.MovieResults
import kotlinx.android.synthetic.main.row_dashboard.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import br.com.francielilima.movies.utils.enums.ClickCategory
import br.com.francielilima.movies.utils.enums.MovieResultCategory

class DashboardViewHolder(itemView: View, var listener: RecyclerViewClickListener, private val context: Context): RecyclerView.ViewHolder(itemView) {

    private var adapter: MovieAdapter = MovieAdapter(context, listener)

    init {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        itemView.recyclerView.adapter = adapter
        itemView.recyclerView.layoutManager = layoutManager
    }

    var movies: MovieResults? = null
        set(value) {
            field = value

            itemView.textViewTitle.text = MovieResultCategory.from(adapterPosition)?.title?.let { context.getString(it) }

            value?.movies?.let {
                adapter.items = ArrayList(it)
            }

            itemView.buttonSeeAll.setOnClickListener {
                listener.onRecyclerViewItemClicked(adapterPosition, ClickCategory.SEE_MORE)
            }
        }
}