package br.com.francielilima.movies.utils.view_holders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import br.com.francielilima.movies.models.Genre
import br.com.francielilima.movies.models.Movie
import br.com.francielilima.movies.utils.Constants
import br.com.francielilima.movies.utils.enums.ClickCategory
import br.com.francielilima.movies.utils.extensions.text
import br.com.francielilima.movies.utils.extensions.year
import br.com.francielilima.movies.utils.interfaces.RecyclerViewClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_movie.view.*
import java.text.SimpleDateFormat
import java.util.*

class MovieViewHolder(itemView: View, listener: RecyclerViewClickListener, private val context: Context): RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            movie?.id?.let { listener.onRecyclerViewItemClicked(it, ClickCategory.MOVIE) }
        }
    }

    var movie: Movie? = null
        set(value) {
            field = value

            itemView.textViewTitle.text = value?.title
            itemView.textViewGenres.text = value?.genres?.text()
            itemView.textViewReleaseDate.text = value?.releaseDate?.year()

            Picasso.with(context)
                    .load("${Constants.URLs.mediumImageURL}${value?.backgroundPath}")
                    .into(itemView.imageViewPoster)
        }
}