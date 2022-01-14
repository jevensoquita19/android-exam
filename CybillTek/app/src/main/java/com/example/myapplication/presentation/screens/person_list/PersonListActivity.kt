package com.example.myapplication.presentation.screens.person_list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.local.person.Person
import com.example.myapplication.data.util.Resource
import com.example.myapplication.databinding.ActivityPersonListBinding
import com.example.myapplication.presentation.base.BaseActivity
import com.example.myapplication.presentation.screens.person_details.PersonDetailsActivity
import com.example.myapplication.presentation.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PersonListActivity :
    BaseActivity<ActivityPersonListBinding>(R.layout.activity_person_list),
    PersonAdapter.PersonHandler
{

    private lateinit var viewModel: PersonListViewModel

    @Inject
    lateinit var personAdapter: PersonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeObservers()
        setUpViews()
    }

    private fun setUpViews() {
        binding.apply {
            setPersonList()

            getPersonList()
        }
    }

    private fun subscribeObservers() {
        viewModel = ViewModelProvider(this).get(PersonListViewModel::class.java)

        viewModel.personsLiveData.observe(this, EventObserver{
            when (it) {
                is Resource.Loading -> {
                    displayLoader(View.VISIBLE)
                }
                is Resource.Error -> {
                    displayLoader(View.GONE)
                    viewModel.getPersonListLocal()
                }
                is Resource.Success -> {
                    displayLoader(View.GONE)
                }
            }
        })

        viewModel.personsLocalLiveData.observe(this, Observer {
            showPersonList(it)
        })
    }

    private fun getPersonList() {
        viewModel.getPersonsApi()
    }

    private fun setPersonList() {
        binding.apply {
            rvPersons.layoutManager = LinearLayoutManager(this@PersonListActivity)
            personAdapter.handler = this@PersonListActivity
            rvPersons.adapter = personAdapter
        }
    }

    private fun showPersonList(persons: List<Person>) {
        personAdapter.setUpPersonList(persons)
    }

    private fun displayLoader(visibility: Int) {
        binding.apply {
            progressBar.visibility = visibility
            rvPersons.visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
    }

    override fun onPersonClick(item: Person) {
        PersonDetailsActivity.start(this, item)
    }
}