package com.example.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.person.Person
import com.example.myapplication.data.local.person.PersonDAO

@Database(entities = [Person::class], version = 1)
abstract  class AppDataBase : RoomDatabase() {

    abstract fun personDao(): PersonDAO
}