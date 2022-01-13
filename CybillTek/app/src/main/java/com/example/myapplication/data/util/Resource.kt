package com.example.myapplication.data.util

import com.example.myapplication.presentation.util.Event

// A generic class that contains data and status about loading this data.
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val code: Int? = null
){
    class Success<T>(data: T, message: String? = "") : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null, code: Int? = null) : Resource<T>(data, message, code)

    companion object {
        fun <T> loadingEvent(): Event<Resource<T>> {
            return Event(Loading())
        }
    }
}

fun <T> Resource.Error<T>.checkIfExpiredToken(expiredCallback: () -> Unit, notExpiredCallback: () -> Unit) {
    if (code != null && code == 405) {
        expiredCallback()
        return
    }
    notExpiredCallback()
}