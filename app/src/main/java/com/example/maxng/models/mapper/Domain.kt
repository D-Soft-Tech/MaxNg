package com.example.maxng.models.mapper

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "starWars")
data class Domain(
    @PrimaryKey
    var name: String,
    var image: Int,
    var releasedDate: String,
    var category: String,
    var liked: Boolean = false
)
