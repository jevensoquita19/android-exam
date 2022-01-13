package com.example.myapplication.data.util

import okhttp3.ResponseBody
import org.json.JSONObject
import java.lang.Exception

fun ResponseBody?.getDeserializedErrorMessage(fallbackMessage: String): String {
    return try {
        val errorObject = JSONObject(this!!.string())
        val message = errorObject.getString("message")


        if (message.isNullOrBlank()) fallbackMessage else {
            if (message.endsWith(".") || message.endsWith("!") || message.endsWith("?")) {
                message
            } else {
                "$message."
            }
        }
    } catch (ex: Exception) {
        return fallbackMessage
    }
}