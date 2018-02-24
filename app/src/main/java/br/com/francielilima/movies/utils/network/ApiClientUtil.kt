package br.com.francielilima.movies.utils.network

import br.com.francielilima.movies.models.Movie
import br.com.francielilima.movies.utils.network.pokos.DiscoverMovies
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface ApiClientUtil {

    @GET("discover/movie")
    fun discover(): Observable<DiscoverMovies>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Long): Observable<Movie>

}