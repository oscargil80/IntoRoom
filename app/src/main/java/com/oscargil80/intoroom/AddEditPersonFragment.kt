package com.oscargil80.intoroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.oscargil80.intoroom.databinding.FragmentAddEditPersonalBinding


class AddEditPersonFragment(private val listener: AddEditPersonListener) :
    BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddEditPersonalBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddEditPersonalBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attachUiListener()

    }

    private fun attachUiListener() {
        binding.saveBtn.setOnClickListener {
            val name = binding.personNameEt.text.toString()
            val age = binding.personAgeEt.text.toString()
            val city = binding.personCityEt.text.toString()

            if (name.isNotEmpty() && age.isNotEmpty() && city.isNotEmpty()) {
                val person = Person(0, name, age.toInt(), city)
                listener.onSaveBtnClicked((person))
            }
            dismiss()


        }
    }

    companion object {
        const val TAG = "AddEditPersonalFragment"
    }

    interface AddEditPersonListener {
        fun onSaveBtnClicked(person: Person)

    }
}