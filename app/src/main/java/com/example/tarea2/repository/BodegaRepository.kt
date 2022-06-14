package com.example.tarea2.repository

import androidx.lifecycle.LiveData
import com.example.tarea2.data.BodegaDao
import com.example.tarea2.model.Bodega

class BodegaRepository(private val bodegaDao: BodegaDao ) {
    val getAll: LiveData<List<Bodega>> = bodegaDao.getAll()

    suspend fun addItem(lugar: Bodega) {
        bodegaDao.addLugar(lugar)
    }

    suspend fun updateItem(lugar: Bodega) {
        bodegaDao.updateLugar(lugar)
    }

    suspend fun deleteItem(lugar: Bodega) {
        bodegaDao.deleteLugar(lugar)
    }
}