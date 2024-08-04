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

    fun render(
        Person: Person,
        editClickListener: (Person) -> Unit,
        deleteClickListener: (Person) -> Unit
    ) {
        binding.personNameTv.text = Person.name
        binding.personCityTv.text = Person.city
        binding.personAgeTv.text = Person.age.toString()

        binding.editBtn.setOnClickListener{
            editClickListener(Person)
        }

        binding.deleteBtn.setOnClickListener{
            deleteClickListener(Person)
        }



    }
}
