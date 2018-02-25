package br.com.francielilima.movies.utils.network

import br.com.francielilima.movies.models.Movie
import br.com.francielilima.movies.utils.network.pokos.GenreResults
import br.com.francielilima.movies.utils.network.pokos.MovieResults
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface ApiClientUtil {

    @GET("discover/movie")
    fun discover(): Observable<MovieResults>

    @GET("movie/latest")
    fun getLatest(): Observable<Movie>

    @GET("movie/now_playing")
    fun getNowPlaying(): Observable<MovieResults>

    @GET("movie/popular")
    fun getPopular(): Observable<MovieResults>

    @GET("movie/top_rated")
    fun getTopRated(): Observable<MovieResults>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Long): Observable<Movie>

    @GET("genre/movie/list")
    fun getMovieGenres(): Observable<GenreResults>

}