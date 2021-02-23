package br.com.francielilima.movies.utils.extensions

import br.com.francielilima.movies.models.Genre
import br.com.francielilima.movies.models.Movie
import br.com.francielilima.movies.utils.network.pokos.MovieResults

fun List<Genre>.text(): String {
    if (this.isNotEmpty()) {
        var genreText = ""
        this.forEachIndexed { index, genre ->
            genreText += "${genre.name}"

            if (index != this.size - 1) genreText += ", "
        }

        return genreText
    }

    return ""
}

fun List<MovieResults>.withGenres(genres: List<Genre>): List<MovieResults> {
    return this.map {
        it.movies.map { movie ->
            val innerGenres = arrayListOf<Genre>()
            movie.genreIds.forEach { id ->
                innerGenres.addAll(genres.filter { it.id == id })
            }

            movie.genres = innerGenres

            movie
        }
        it
    }
}