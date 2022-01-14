package com.example.myapplication.domain.usecase.local.person

import com.example.myapplication.data.local.person.Person
import com.example.myapplication.data.local.person.PersonDAO

class GetPersonLocalUseCase(
    private val personDAO: PersonDAO
) {

    suspend operator fun invoke(): List<Person> {
        return personDAO.getPersonList()
    }
}