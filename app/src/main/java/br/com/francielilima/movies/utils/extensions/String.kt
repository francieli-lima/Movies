package br.com.francielilima.movies.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.year(): String {
    if (this.isNotEmpty()) {
        return try {
            val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val calendar = Calendar.getInstance()
            calendar.time = format.parse(this)

            calendar.get(Calendar.YEAR).toString()
        } catch (e: Exception) {
            //someone used a string that is not a date
            this
        }
    }

    return this
}