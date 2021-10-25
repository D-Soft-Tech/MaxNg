package com.example.maxng.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.maxng.models.mapper.Domain
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDao {

    @Query("SELECT * FROM starWars WHERE category = :category ORDER BY name ASC")
    fun getData(category: String): Flow<List<Domain>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myFav: Domain)

    @Query("SELECT * FROM starWars WHERE liked = :liked ORDER BY category ASC")
    fun getLiked(liked: Int): Flow<List<Domain>>

    @Update
    suspend fun update(myFav: Domain)
}
