package br.com.francielilima.movies.utils.extensions

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager

fun Activity.isThereInternet(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = connectivityManager.activeNetworkInfo
    return netInfo != null && netInfo.isConnectedOrConnecting
}