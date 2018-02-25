package br.com.francielilima.movies.utils.network

import br.com.francielilima.movies.models.Movie
import br.com.francielilima.movies.utils.network.pokos.GenreResults
import br.com.francielilima.movies.utils.network.pokos.MovieResults
import rx.Observable

object ApiDataManager {

    fun discoverMoviesObservable(): Observable<MovieResults>? {
        val retrofit = RetrofitUtil.getRetrofit()
        val client = retrofit.create(ApiClientUtil::class.java)

        return client.discover()
    }

    fun getNowPlayingMoviesObservable(): Observable<MovieResults>? {
        val retrofit = RetrofitUtil.getRetrofit()
        val client = retrofit.create(ApiClientUtil::class.java)

        return client.getNowPlaying()
    }

    fun getPopularMoviesObservable(): Observable<MovieResults>? {
        val retrofit = RetrofitUtil.getRetrofit()
        val client = retrofit.create(ApiClientUtil::class.java)

        return client.getPopular()
    }

    fun getTopRatedMoviesObservable(): Observable<MovieResults>? {
        val retrofit = RetrofitUtil.getRetrofit()
        val client = retrofit.create(ApiClientUtil::class.java)

        return client.getTopRated()
    }

    fun getLatestMovieObservable(): Observable<Movie>? {
        val retrofit = RetrofitUtil.getRetrofit()
        val client = retrofit.create(ApiClientUtil::class.java)

        return client.getLatest()
    }

    fun getMoviesDetailsObservable(id: Long): Observable<Movie>? {
        val retrofit = RetrofitUtil.getRetrofit()
        val client = retrofit.create(ApiClientUtil::class.java)

        return client.getMovieDetails(id)
    }

    fun getMovieGenresObservable(): Observable<GenreResults>? {
        val retrofit = RetrofitUtil.getRetrofit()
        val client = retrofit.create(ApiClientUtil::class.java)

        return client.getMovieGenres()
    }
}