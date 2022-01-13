package com.example.myapplication.presentation.util

import com.example.myapplication.BuildConfig
import com.example.myapplication.data.util.Resource
import java.net.ConnectException

abstract class BaseRepository {

    suspend fun <T> handleReceivedData(handler: suspend () -> Resource<T>): Resource<T> {
        return try {
            handler()
        } catch (ex: Exception) {
            if (BuildConfig.DEBUG) {
                ex.printStackTrace()
            }
            when (ex) {
                is ConnectException ->
                    Resource.Error("No internet connection and please try again")
                else ->
                    Resource.Error("An unexpected error occurred.")
            }
        }
    }
}