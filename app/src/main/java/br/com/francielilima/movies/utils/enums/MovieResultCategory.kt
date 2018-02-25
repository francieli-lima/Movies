package br.com.francielilima.movies.utils.enums

import br.com.francielilima.movies.R

enum class MovieResultCategory {
    DISCOVER {
        override var title = R.string.discover
    },
    TOP_RATED {
        override var title = R.string.top_ratings
    },
    POPULAR {
        override var title = R.string.popular
    },
    NOW_PLAYING {
        override var title = R.string.now_playing
    };

    abstract var title: Int

    companion object {
        private val map = MovieResultCategory.values().associateBy { it.ordinal }
        fun from(index: Int) = map[index]
    }
}