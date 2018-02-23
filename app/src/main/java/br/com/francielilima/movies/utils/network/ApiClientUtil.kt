package br.com.francielilima.movies.utils.network

import br.com.francielilima.movies.utils.network.pokos.DiscoverMovies
import retrofit2.http.GET
import rx.Observable

interface ApiClientUtil {

    @GET("discover/movie")
    fun discover(): Observable<DiscoverMovies>

}