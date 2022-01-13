package com.example.myapplication.domain.repository

import com.example.myapplication.data.model.response.PersonResponse
import com.example.myapplication.data.util.Resource

interface GetPersonRepository {

    suspend fun getPersonList(): Resource<List<PersonResponse>>
}