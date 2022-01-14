package com.example.myapplication.presentation.screens.person_details

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.data.local.person.Person
import com.example.myapplication.databinding.ActivityPersonDetailsBinding
import com.example.myapplication.presentation.base.BaseActivity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*


const val SELECTED_PERSON_KEY = "SELECTED_PERSON_KEY"

class PersonDetailsActivity : BaseActivity<ActivityPersonDetailsBinding>(R.layout.activity_person_details) {

    companion object {
        @JvmStatic
        fun start(context: Context, selectedPerson: Person?) {
            val starter = Intent(context, PersonDetailsActivity::class.java)
            starter.putExtra(SELECTED_PERSON_KEY, selectedPerson)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        binding.apply {
            getSelectedPerson()
        }
    }

    private fun getSelectedPerson() {
        intent.getSerializableExtra(SELECTED_PERSON_KEY)?.let {
            val person = it as Person
            showSelectedPerson(person)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showSelectedPerson(person: Person) {
        binding.apply {
            tvFirstName.text = person.firstName
            tvLastName.text = person.lastName
            tvBday.text = formatDate(person.dob)
            tvEmail.text = person.email
            tvMobile.text = person.mobileNumber
            tvAddress.text = person.address
            tvAge.text = "${calculateAge(person.dob)} yrs old"
        }
    }

    private fun formatDate(bday: String): String {
        val outputFormat: DateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US)

        val date: Date? = inputFormat.parse(bday)
        date?.let {
            return outputFormat.format(it)
        }

        return ""
    }

    private fun calculateAge(bday: String): Int {

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
        val birthdate = LocalDate.parse(bday, formatter)

        val current = LocalDateTime.now()
        val period: Period = Period.between(birthdate, current.toLocalDate())
        return period.years
    }
}