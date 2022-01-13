package com.example.myapplication.data.repository.person

import com.example.myapplication.data.model.response.PersonResponse
import com.example.myapplication.data.util.Resource
import com.example.myapplication.data.util.getDeserializedErrorMessage
import com.example.myapplication.domain.repository.GetPersonRepository
import com.example.myapplication.presentation.util.BaseRepository

class GetPersonRepositoryImpl(
    private val remoteDataSource: GetPersonRemoteDataSource
): BaseRepository(), GetPersonRepository {
    override suspend fun getPersonList(): Resource<List<PersonResponse>> {
        return handleReceivedData {
            val response = remoteDataSource.getPersonList()
            if (response.isSuccessful) {
                response.body()?.let {
                    it.data?.let { data ->
                        return@handleReceivedData Resource.Success(
                            data = data,
                            message = it.message
                        )
                    }
                }
            }
            return@handleReceivedData Resource.Error(
                response.errorBody().getDeserializedErrorMessage(response.message())
            )
        }
    }
}