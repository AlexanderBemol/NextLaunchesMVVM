package com.nordokod.nextlaunches.model.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nordokod.nextlaunches.model.entity.Launches

@Dao
interface LaunchesDAO {
    @Query("SELECT * FROM Launches")
    fun findAll(): List<Launches>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(launches: List<Launches>)
}