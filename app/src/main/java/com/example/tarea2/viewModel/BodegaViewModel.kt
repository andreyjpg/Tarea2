package com.example.tarea2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tarea2.data.BodegaDatabase
import com.example.tarea2.model.Bodega
import com.example.tarea2.repository.BodegaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BodegaViewModel(application: Application): AndroidViewModel(application) {
    val getAllData: LiveData<List<Bodega>>

    private val repository: BodegaRepository
    init {
        val bodegaDao = BodegaDatabase.getDatabase(application).BodegaDao()
        repository = BodegaRepository(bodegaDao)
        getAllData = repository.getAll
    }

    fun addItem (item: Bodega) {
        viewModelScope.launch(Dispatchers.IO) { repository.addItem(item) }
    }

    fun updateItem (item: Bodega) {
        viewModelScope.launch(Dispatchers.IO) { repository.updateItem(item) }
    }

    fun deleteItem (item: Bodega) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteItem(item) }
    }
}