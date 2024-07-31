package com.oscargil80.intoroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.oscargil80.intoroom.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), AddEditPersonFragment.AddEditPersonListener {
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

    private fun showBottonSheet() {
        val bottonSheet = AddEditPersonFragment(this)
        bottonSheet.show(supportFragmentManager, AddEditPersonFragment.TAG)
    }

    private fun initVars() {
        dao = AppDatabase.gatDatabase(this).personDao()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PersonDetailsAdapter()
        binding.recyclerView.adapter = adapter
    }

    override fun onSaveBtnClicked(person: Person) {
        lifecycleScope.launch(Dispatchers.IO) {
            dao?.savePerson(person)
        }


    }
}