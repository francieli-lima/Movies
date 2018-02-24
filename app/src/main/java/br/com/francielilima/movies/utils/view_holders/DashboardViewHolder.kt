package br.com.francielilima.movies.utils.view_holders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.francielilima.movies.models.Movie
import br.com.francielilima.movies.utils.Constants
import br.com.francielilima.movies.utils.interfaces.RecyclerViewClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_movie.view.*

class DashboardViewHolder(itemView: View, listener: RecyclerViewClickListener, private val context: Context): RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            movie?.id?.let { listener.onRecyclerViewItemClicked(it) }
        }
    }

    var movie: Movie? = null
        set(value) {
            field = value

            Picasso.with(context)
                    .load("${Constants.URLs.smallImageURL}${value?.posterPath}")
                    .into(itemView.imageViewPoster)
        }
}