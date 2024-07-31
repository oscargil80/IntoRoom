package com.oscargil80.intoroom

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.oscargil80.intoroom.databinding.SingleItemBinding

class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var binding = SingleItemBinding.bind(view)

    fun render(Person: Person) {
        binding.personNameTv.text = Person.name
        binding.personCityTv.text = Person.city
        binding.personAgeTv.text = Person.age.toString()
    }
}
