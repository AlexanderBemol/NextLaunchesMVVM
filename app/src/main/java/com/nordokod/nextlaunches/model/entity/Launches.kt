package com.nordokod.nextlaunches.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Launches")

class Launches(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String?,
    val windowstart: Date?,
    val windowend: Date?,
    val status: Int
    )