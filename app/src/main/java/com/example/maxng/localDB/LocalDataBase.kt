package com.example.maxng.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.maxng.models.mapper.Domain

@Database(entities = [Domain::class], version = 1, exportSchema = false)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun getLocalDao(): LocalDao
}
