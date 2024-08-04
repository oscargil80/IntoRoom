package com.oscargil80.intoroom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class PersonDetailsAdapter(
    var editClickListener: (Person) -> Unit,
    var deleteClickListener: (Person) -> Unit,
) : ListAdapter<Person, PersonViewHolder>(PersonDiffUtill()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PersonViewHolder(layoutInflater.inflate(R.layout.single_item, parent, false))
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val item = getItem(position)
        holder.render(item, editClickListener, deleteClickListener)
    }

    interface PersonDetailsClickListener {
        fun onEditPersonClick(person: Person)
        fun onDeletePersonClick(person: Person)
    }

}

class PersonDiffUtill() : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person) = oldItem.pId == newItem.pId


    override fun areContentsTheSame(oldItem: Person, newItem: Person) = oldItem == newItem

}


