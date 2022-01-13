package com.example.myapplication.data.api

import com.example.myapplication.data.model.response.PersonResponse
import com.example.myapplication.data.model.response.base.ResponseListWrapper
import retrofit2.Response
import retrofit2.http.GET

interface CybillTekService {

    @GET("?results=10")
    suspend fun getPersonList(
    ): Response<ResponseListWrapper<PersonResponse>>
}