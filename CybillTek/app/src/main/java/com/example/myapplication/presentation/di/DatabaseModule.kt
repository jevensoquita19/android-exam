package com.example.myapplication.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.local.AppDataBase
import com.example.myapplication.data.local.person.PersonDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun providePersonDao(appDataBase: AppDataBase): PersonDAO {
        return appDataBase.personDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            "CybillTek"
        ).build()
    }
}