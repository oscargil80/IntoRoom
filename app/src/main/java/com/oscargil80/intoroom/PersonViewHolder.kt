package com.oscargil80.intoroom

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.oscargil80.intoroom.databinding.SingleItemBinding

class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var binding = SingleItemBinding.bind(view)
   /* init {
        binding.editBtn.setOnClickListener{

        }
    }*/

    fun render(Person: Person, listener: PersonDetailsAdapter.PersonDetailsClickListener) {
        binding.personNameTv.text = Person.name
        binding.personCityTv.text = Person.city
        binding.personAgeTv.text = Person.age.toString()

        binding.editBtn.setOnClickListener{
            listener.onEditPersonClick(Person)
        }

        binding.deleteBtn.setOnClickListener{
            listener.onDeletePersonClick(Person)
        }



    }
}
