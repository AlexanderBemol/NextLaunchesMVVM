package com.nordokod.nextlaunches.model.repository

import com.nordokod.nextlaunches.model.entity.Launches
import com.nordokod.nextlaunches.utils.RequestResult

interface ILaunchesRepository{
    suspend fun getAllLaunches() :RequestResult<List<Launches>>
}
