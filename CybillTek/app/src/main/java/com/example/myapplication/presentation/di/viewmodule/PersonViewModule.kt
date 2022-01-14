package com.example.myapplication.presentation.di.viewmodule

import com.example.myapplication.presentation.screens.person_list.PersonAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class PersonViewModule {

    @Provides
    fun providePersonAdapter(): PersonAdapter = PersonAdapter()
}