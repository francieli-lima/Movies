package br.com.francielilima.movies.utils.extensions

import br.com.francielilima.movies.models.Genre

fun List<Genre>.text(): String {
    if (this.isNotEmpty()) {
        var genreText = ""
        this?.forEachIndexed { index, genre ->
            genreText += "${genre.name}"

            if (index != this.size - 1) genreText += ", "
        }

        return genreText
    }

    return ""
}