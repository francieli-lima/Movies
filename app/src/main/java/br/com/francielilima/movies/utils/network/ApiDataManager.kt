package br.com.francielilima.movies.utils.network

import br.com.francielilima.movies.models.Movie
import br.com.francielilima.movies.utils.network.pokos.DiscoverMovies
import rx.Observable

object ApiDataManager {

    fun discoverMoviesObservable(): Observable<DiscoverMovies>? {
        val retrofit = RetrofitUtil.getRetrofit()
        val client = retrofit.create(ApiClientUtil::class.java)

        return client.discover()
    }

    fun getMoviesDetailsObservable(id: Long): Observable<Movie>? {
        val retrofit = RetrofitUtil.getRetrofit()
        val client = retrofit.create(ApiClientUtil::class.java)

        return client.getMovieDetails(id)
    }
}