package com.example.myapplication.presentation.screens.person_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.local.person.Person
import com.example.myapplication.data.model.response.PersonResponse
import com.example.myapplication.data.util.Resource
import com.example.myapplication.domain.usecase.api.person.GetPersonUseCase
import com.example.myapplication.domain.usecase.local.person.SavePersonLocalUseCase
import com.example.myapplication.presentation.util.Event
import com.example.myapplication.presentation.util.toEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonListViewModel @Inject constructor(
    private val getPersonUseCase: GetPersonUseCase,
    private val savePersonLocalUseCase: SavePersonLocalUseCase
): ViewModel() {

    private val getPersons = MutableLiveData<Event<Resource<List<PersonResponse>>>>()
    val personsLiveData: LiveData<Event<Resource<List<PersonResponse>>>>
        get() = getPersons


    fun getPersons() {
        viewModelScope.launch((Dispatchers.IO)) {
            getPersons.postValue(Resource.loadingEvent())
            val result = getPersonUseCase()

            result.data?.let { personList ->
                personList.map {

                    // Save to Database
                    Person().apply {
                        email = it.email
                        firstName = it.name.first
                        lastName = it.name.last
                        dob = it.dob.date
                        mobileNumber = it.cell
                        address =
                            "${it.location.street.number} ${it.location.street.name} ${it.location.city} ${it.location.state} ${it.location.country}"
                    }.also {
                        savePersonLocalUseCase.invoke(it)
                    }
                }
            }


            getPersons.postValue(result.toEvent())
        }
    }
}