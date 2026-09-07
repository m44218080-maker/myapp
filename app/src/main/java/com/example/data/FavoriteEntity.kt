package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_verbs")
data class FavoriteEntity(
    @PrimaryKey val verbId: Int
)
