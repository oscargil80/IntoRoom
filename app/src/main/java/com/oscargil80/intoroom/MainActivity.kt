package com.oscargil80.intoroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.oscargil80.intoroom.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), AddEditPersonFragment.AddEditPersonListener {
    private lateinit var binding: ActivityMainBinding
    private var dao: PersonDao? = null
    private lateinit var adapter: PersonDetailsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponent() // Iniciar Componentes
        InitBottonFloating()// Iniciar Boton Floating
        cargarDatosSearchView() // Cragar adapter
    }

    private fun cargarDatosSearchView() {
        lifecycleScope.launch {
            dao?.getAllData()?.collect { mList ->
                adapter.submitList(mList)
                binding.searchcView.setQuery("", false)
                binding.searchcView.clearFocus()
            }
        }
    }

    private fun InitBottonFloating() {
        binding.floatingActionButton.setOnClickListener {
            showBottonSheet()
        }

        // En el video no funciona debido a que debo ponerle aqui SearchView.OnQueryTextListener y en el video es OnQueryTextListener
        binding.searchcView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null)
                    onQueryChange(newText)// Cuando metemos un valor
                return true
            }
        })
    }

    private fun onQueryChange(query: String) {
        lifecycleScope.launch {
            adapter.submitList(dao?.getSearchedData(query)?.first())
        }
    }

    private fun showBottonSheet(person: Person? = null) {
        val bottonSheet = AddEditPersonFragment(this, person)
        bottonSheet.show(supportFragmentManager, AddEditPersonFragment.TAG)
    }

    private fun initComponent() {
        dao = AppDatabase.gatDatabase(this).personDao()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PersonDetailsAdapter(
            editClickListener = {Person -> editClickListener(Person)},
            deleteClickListener = {Person -> deleteClickListener(Person)}
        )
        binding.recyclerView.adapter = adapter
    }

    private fun editClickListener(person: Person) {
        showBottonSheet(person)
    }

    private fun deleteClickListener(person: Person) {
        lifecycleScope.launch(Dispatchers.IO) {
            dao?.deletepersonById(person.pId)
        }
    }

    override fun onSaveBtnClicked(isUpdate: Boolean, person: Person) {
        lifecycleScope.launch(Dispatchers.IO) {
            if (isUpdate)
                dao?.savePerson(person)
            else
                dao?.savePerson(person)

        }
    }

/*    override fun onEditPersonClick(person: Person) {
        showBottonSheet(person)
    }

    override fun onDeletePersonClick(person: Person) {
        lifecycleScope.launch(Dispatchers.IO) {
            dao?.deletepersonById(person.pId)
            //dao?.deletePerson(person)

        }
    }*/
}