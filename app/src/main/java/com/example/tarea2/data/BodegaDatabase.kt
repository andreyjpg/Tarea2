package com.example.tarea2.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tarea2.model.Bodega

@Database(entities = [Bodega::class], version = 1, exportSchema = false )
abstract class BodegaDatabase: RoomDatabase() {
    abstract fun BodegaDao(): BodegaDao

    companion object {
        @Volatile
        private var INSTANCE: BodegaDatabase? = null

        fun getDatabase(context: android.content.Context): BodegaDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BodegaDatabase::class.java,
                    "bodega_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}