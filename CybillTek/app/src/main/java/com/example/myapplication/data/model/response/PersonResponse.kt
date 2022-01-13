package com.example.myapplication.data.model.response

data class PersonResponse (

    val name: Name,
    val dob: Dob,
    val email: String = "",
    val cell: String = "",
    val location: Location,

)

data class Name(
    val title: String = "",
    val first: String = "",
    val last: String = ""
)

data class Dob(
    val date: String = "",
    val age: String = ""
)

data class Location(
    val street: Street,
    val city: String = "",
    val state: String = "",
    val country: String = "",
)

data class Street(
    val number: Int = 0,
    val name: String = ""
)