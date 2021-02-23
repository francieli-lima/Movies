package br.com.francielilima.movies.utils.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import br.com.francielilima.movies.R
import br.com.francielilima.movies.utils.interfaces.RecyclerViewClickListener
import br.com.francielilima.movies.utils.network.pokos.MovieResults
import br.com.francielilima.movies.utils.view_holders.DashboardViewHolder
import kotlinx.android.synthetic.main.row_dashboard.view.*

class DashboardAdapter(private val context: Context, private val listener: RecyclerViewClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = listOf<MovieResults>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (holder is DashboardViewHolder) {
            holder.movies = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = View.inflate(context, R.layout.row_dashboard, null)
        return DashboardViewHolder(view, listener, context)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}