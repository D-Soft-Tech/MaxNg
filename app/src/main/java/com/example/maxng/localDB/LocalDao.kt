package com.example.maxng.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.maxng.models.mapper.Domain
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDao {

    @Query("SELECT * FROM starWars WHERE category = :category")
    fun getData(category: String): Flow<List<Domain>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(myFav: Domain)

    @Query("SELECT * FROM starWars WHERE liked = 'true'")
    fun getLiked(): Flow<List<Domain>>
}
