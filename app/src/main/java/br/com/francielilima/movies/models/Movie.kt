package br.com.francielilima.movies.models

import com.google.gson.annotations.SerializedName

open class Movie {

    var id: Long = 0

    @SerializedName("vote_count")
    var votes: Int = 0

    @SerializedName("video")
    var isThereVideo: Boolean = false

    @SerializedName("vote_average")
    var rating: Double = 0.0

    var title: String? = null

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("backdrop_path")
    var backgroundPath: String? = null

    @SerializedName("genre_ids")
    var genreIds: List<Long> = listOf()

    var overview: String? = null

    @SerializedName("release_date")
    var releaseDate: String? = null
}