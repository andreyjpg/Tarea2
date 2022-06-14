package com.example.tarea2.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tarea2.model.Bodega

@Dao
interface BodegaDao {

    @Query("SELECT * FROM Bodega")
    fun getAll(): LiveData<List<Bodega>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLugar(lugar: Bodega)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateLugar(lugar: Bodega)

    @Delete
    suspend fun deleteLugar(lugar: Bodega)
}