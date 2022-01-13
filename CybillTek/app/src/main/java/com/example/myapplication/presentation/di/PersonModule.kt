package com.example.myapplication.presentation.di

import com.example.myapplication.data.api.CybillTekService
import com.example.myapplication.data.local.person.PersonDAO
import com.example.myapplication.data.repository.person.GetPersonRemoteDataSource
import com.example.myapplication.data.repository.person.GetPersonRemoteDataSourceImpl
import com.example.myapplication.data.repository.person.GetPersonRepositoryImpl
import com.example.myapplication.domain.repository.GetPersonRepository
import com.example.myapplication.domain.usecase.api.person.GetPersonUseCase
import com.example.myapplication.domain.usecase.local.person.GetPersonLocalUseCase
import com.example.myapplication.domain.usecase.local.person.SavePersonLocalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class PersonModule {

    @Provides
    fun provideGetPersonListRemoteDataSource(
        service: CybillTekService
    ): GetPersonRemoteDataSource {
        return GetPersonRemoteDataSourceImpl(service)
    }

    @Provides
    fun provideGetPersonListRepository(remoteDataSource: GetPersonRemoteDataSource): GetPersonRepository {
        return GetPersonRepositoryImpl(remoteDataSource)
    }

    @Provides
    fun provideGetPersonUseCase(repository: GetPersonRepository): GetPersonUseCase {
        return GetPersonUseCase(repository)
    }

    @Provides
    fun provideSavePersonLocalUseCase(personDAO: PersonDAO): SavePersonLocalUseCase {
        return SavePersonLocalUseCase(personDAO)
    }
}