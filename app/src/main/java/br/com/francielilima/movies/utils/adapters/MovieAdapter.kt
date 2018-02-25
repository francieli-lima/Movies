package br.com.francielilima.movies.utils.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import br.com.francielilima.movies.R
import br.com.francielilima.movies.models.Movie
import br.com.francielilima.movies.utils.interfaces.RecyclerViewClickListener
import br.com.francielilima.movies.utils.view_holders.MovieViewHolder

class MovieAdapter(private val context: Context, private val listener: RecyclerViewClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = arrayListOf<Movie>()
        set(value) {
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val item = items[position]

        if (holder is MovieViewHolder) {
            holder.movie = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        val view = View.inflate(context, R.layout.row_movie, null)
        return MovieViewHolder(view, listener, context)
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}