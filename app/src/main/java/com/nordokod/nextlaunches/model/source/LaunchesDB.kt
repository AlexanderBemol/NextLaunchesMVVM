package com.nordokod.nextlaunches.model.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nordokod.nextlaunches.model.entity.Launches
import com.nordokod.nextlaunches.model.entity.Rockets
import com.nordokod.nextlaunches.model.source.LaunchesDAO
import com.nordokod.nextlaunches.model.source.converters.Converters

@Database(
    entities = [Launches::class],
    version = 1,exportSchema = false
)

@TypeConverters(Converters::class)
abstract class LaunchesDB : RoomDatabase() {
    abstract val launchesDAO: LaunchesDAO
}