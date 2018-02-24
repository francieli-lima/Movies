package br.com.francielilima.movies.utils.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import br.com.francielilima.movies.R
import br.com.francielilima.movies.models.Movie
import br.com.francielilima.movies.utils.interfaces.RecyclerViewClickListener
import br.com.francielilima.movies.utils.view_holders.DashboardViewHolder

class DashboardAdapter(private val context: Context, private val listener: RecyclerViewClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val item = items[position]

        if (holder is DashboardViewHolder) {
            holder.movie = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        val view = View.inflate(context, R.layout.row_movie, null)
        return DashboardViewHolder(view, listener, context)
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}