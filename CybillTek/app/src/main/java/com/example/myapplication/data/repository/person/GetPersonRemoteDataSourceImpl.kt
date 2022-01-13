package com.example.myapplication.data.repository.person

import com.example.myapplication.data.api.CybillTekService
import com.example.myapplication.data.model.response.PersonResponse
import com.example.myapplication.data.model.response.base.ResponseListWrapper
import retrofit2.Response

class GetPersonRemoteDataSourceImpl(
    private val service: CybillTekService
): GetPersonRemoteDataSource {

    override suspend fun getPersonList(): Response<ResponseListWrapper<PersonResponse>> {
        return service.getPersonList()
    }
}