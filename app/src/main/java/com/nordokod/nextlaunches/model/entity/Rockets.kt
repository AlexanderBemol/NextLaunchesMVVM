package com.nordokod.nextlaunches.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Rockets")
class Rockets(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String?,
    val imageSizes: List<Int>?,
    val imageURL: String?
)