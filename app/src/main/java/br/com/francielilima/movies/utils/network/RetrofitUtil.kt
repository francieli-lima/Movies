package br.com.francielilima.movies.utils.network

import android.util.Log
import br.com.francielilima.movies.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {
    private lateinit var retrofit: Retrofit

    //region Public
    fun getRetrofit(): Retrofit {
        rebuildRetrofit()
        return retrofit
    }

    //endregion

    //region Private

    private fun rebuildRetrofit() {
        val client = buildOkHttpClient()

        retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(client)
                .build()
    }

    private fun buildOkHttpClient(): OkHttpClient {
        var builder = OkHttpClient.Builder()
        builder = addTokenInterceptor(builder)
        builder = addLoggingInterceptor(builder)

        return builder.build()
    }

    private fun addTokenInterceptor(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        val tokenInterceptor = Interceptor { chain ->
            var originalRequest = chain.request()
            val accessToken = BuildConfig.API_KEY
            val httpUrl = originalRequest.url()

            val url = httpUrl.newBuilder()
                    .addQueryParameter("api_key", accessToken)
                    .build()

            val requestBuilder = originalRequest.newBuilder()
                    .url(url)

            val newRequest = requestBuilder.build()

            chain.proceed(newRequest)
        }

        builder.addNetworkInterceptor(tokenInterceptor)
        return builder
    }

    private fun addLoggingInterceptor(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        // This adds logging for all requests if the app is run in Debug mode
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Log.d("HttpLoggingInterceptor", message) }
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addNetworkInterceptor(httpLoggingInterceptor)
        }
        return builder
    }


    //endregion
}