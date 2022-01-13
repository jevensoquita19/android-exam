package com.example.myapplication.data.repository.person

import com.example.myapplication.data.model.response.PersonResponse
import com.example.myapplication.data.model.response.base.ResponseListWrapper
import retrofit2.Response

interface GetPersonRemoteDataSource {
    suspend fun getPersonList(): Response<ResponseListWrapper<PersonResponse>>
}