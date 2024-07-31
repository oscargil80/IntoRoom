package com.oscargil80.intoroom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oscargil80.intoroom.R
import com.oscargil80.intoroom.databinding.SingleItemBinding

class PersonDetailsAdapter : ListAdapter<Person, PersonViewHolder>(PersonDiffUtill()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PersonViewHolder(layoutInflater.inflate(R.layout.single_item, parent, false))
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val item = getItem(position)
        holder.render(item)
    }


}

class PersonDiffUtill() : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person) = oldItem.pId == newItem.pId


    override fun areContentsTheSame(oldItem: Person, newItem: Person) = oldItem == newItem

}


