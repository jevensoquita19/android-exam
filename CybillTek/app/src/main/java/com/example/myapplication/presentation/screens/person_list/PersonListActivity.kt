package com.example.myapplication.presentation.screens.person_list

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.util.Resource
import com.example.myapplication.databinding.ActivityPersonListBinding
import com.example.myapplication.presentation.base.BaseActivity
import com.example.myapplication.presentation.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonListActivity : BaseActivity<ActivityPersonListBinding>(R.layout.activity_person_list) {

    private lateinit var viewModel: PersonListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeObservers()
        setUpViews()
    }

    private fun setUpViews() {
        binding.apply {
            getPersonList()
        }
    }

    private fun subscribeObservers() {
        viewModel = ViewModelProvider(this).get(PersonListViewModel::class.java)

        viewModel.personsLiveData.observe(this, EventObserver{
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Error -> {

                }
                is Resource.Success -> {
                }
            }
        })
    }

    private fun getPersonList() {
        viewModel.getPersons()
    }
}