package com.example.myapplication.data.local.person

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PersonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePerson(person: Person)

    @Query("SELECT * FROM person")
    suspend fun getPersonList(): List<Person>
}