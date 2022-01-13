package com.example.myapplication.domain.usecase.api.person

import com.example.myapplication.data.model.response.PersonResponse
import com.example.myapplication.data.util.Resource
import com.example.myapplication.domain.repository.GetPersonRepository

class GetPersonUseCase(val repository: GetPersonRepository) {

    suspend operator fun invoke(): Resource<List<PersonResponse>> {
        return repository.getPersonList()
    }
}