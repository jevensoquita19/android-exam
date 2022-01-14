package com.example.myapplication.presentation.screens.person_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.local.person.Person
import com.example.myapplication.databinding.ItemPersonBinding

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    val personList = mutableListOf<Person>()
    var handler: PersonHandler? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemPersonBinding>(
            inflater, R.layout.item_person, parent, false
        )
        return ViewHolder(binding, handler)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = personList[position]
        holder.bind(person)
    }

    override fun getItemCount(): Int = personList.size

    fun setUpPersonList(persons: List<Person>) {
        personList.clear()
        personList.addAll(persons)
        notifyItemRangeChanged(0, persons.size)
    }

    class ViewHolder(val binding: ItemPersonBinding, val handler: PersonHandler?) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(person: Person) {
            binding.apply {

                tvName.text = "${person.firstName} ${person.lastName}"

                itemLayout.setOnClickListener {
                    handler?.onPersonClick(person)
                }
            }
        }
    }

    interface PersonHandler{
        fun onPersonClick(item: Person)
    }

}