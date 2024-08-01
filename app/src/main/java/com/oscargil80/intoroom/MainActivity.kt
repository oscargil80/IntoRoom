package com.oscargil80.intoroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.oscargil80.intoroom.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), AddEditPersonFragment.AddEditPersonListener,
    PersonDetailsAdapter.PersonDetailsClickListener {
    private lateinit var binding: ActivityMainBinding
    private var dao: PersonDao? = null
    private lateinit var adapter: PersonDetailsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVars()
        attachUiListener()
        subscribeDataStreams()
    }

    private fun subscribeDataStreams() {
        lifecycleScope.launch {

            dao?.getAllData()?.collect { mList ->
                adapter.submitList(mList)
            }
        }
    }

    private fun attachUiListener() {
        binding.floatingActionButton.setOnClickListener {
            showBottonSheet()
        }
    }

    private fun showBottonSheet(person: Person? = null) {
        val bottonSheet = AddEditPersonFragment(this, person)
        bottonSheet.show(supportFragmentManager, AddEditPersonFragment.TAG)
    }

    private fun initVars() {
        dao = AppDatabase.gatDatabase(this).personDao()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PersonDetailsAdapter(this)
        binding.recyclerView.adapter = adapter
    }

    override fun onSaveBtnClicked(isUpdate: Boolean, person: Person) {
        lifecycleScope.launch(Dispatchers.IO) {

            if (isUpdate)
                dao?.savePerson(person)
            else
                dao?.savePerson(person)

        }
    }

    override fun onEditPersonClick(person: Person) {
        showBottonSheet(person)

    }

    override fun onDeletePersonClick(person: Person) {
        lifecycleScope.launch(Dispatchers.IO){
            dao?.deletepersonById(person.pId)
            //dao?.deletePerson(person)

        }
    }
}