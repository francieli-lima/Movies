package br.com.francielilima.movies.utils.view_holders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.francielilima.movies.models.Genre
import br.com.francielilima.movies.models.Movie
import br.com.francielilima.movies.utils.Constants
import br.com.francielilima.movies.utils.interfaces.RecyclerViewClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_movie.view.*
import java.text.SimpleDateFormat
import java.util.*

class MovieViewHolder(itemView: View, listener: RecyclerViewClickListener, private val context: Context): RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            movie?.id?.let { listener.onRecyclerViewItemClicked(it) }
        }
    }

    var movie: Movie? = null
        set(value) {
            field = value

            itemView.textViewTitle.text = value?.title
            itemView.textViewGenres.text = getGenresText(value?.genres)
            itemView.textViewReleaseDate.text = getReleaseYear(value?.releaseDate)

            Picasso.with(context)
                    .load("${Constants.URLs.mediumImageURL}${value?.backgroundPath}")
                    .into(itemView.imageViewPoster)
        }


    //region Private

    private fun getGenresText(genres: List<Genre>?): String {
        var genreText = ""
        genres?.forEachIndexed { index, genre ->
            genreText += "${genre.name}"

            if (index != genres.size - 1) genreText += ", "
        }

        return genreText
    }

    private fun getReleaseYear(date: String?): String {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.time = format.parse(date)

        return calendar.get(Calendar.YEAR).toString()
    }

    //endregion
}