package br.com.francielilima.movies.utils.network.pokos

import br.com.francielilima.movies.models.Movie
import com.google.gson.annotations.SerializedName

open class MovieResults {

    var page: Long = 0

    @SerializedName("total_pages")
    var totalPages: Long = 0

    @SerializedName("results")
    var movies: List<Movie> = listOf()
}