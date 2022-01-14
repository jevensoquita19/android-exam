package com.example.myapplication.data.local.person

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "person")
class Person: Serializable {

    @PrimaryKey
    var email: String = ""

    var firstName: String = ""

    var lastName: String = ""

    var dob: String = ""

    var mobileNumber: String = ""

    var address: String = ""
}