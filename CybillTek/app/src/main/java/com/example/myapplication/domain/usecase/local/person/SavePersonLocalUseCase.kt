package com.example.myapplication.domain.usecase.local.person

import com.example.myapplication.data.local.person.Person
import com.example.myapplication.data.local.person.PersonDAO

class SavePersonLocalUseCase(
    private val personDAO: PersonDAO
) {

    suspend operator fun invoke(person: Person) {
        return personDAO.savePerson(person)
    }
}