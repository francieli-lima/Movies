package br.com.francielilima.movies.utils.extensions

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.net.ConnectivityManager

fun AppCompatActivity.isThereInternet(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = connectivityManager.activeNetworkInfo
    return netInfo != null && netInfo.isConnectedOrConnecting
}