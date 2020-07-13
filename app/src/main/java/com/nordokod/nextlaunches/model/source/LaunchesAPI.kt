package com.nordokod.nextlaunches.model.source

import com.nordokod.nextlaunches.model.entity.LaunchesResponse
import retrofit2.Response
import retrofit2.http.GET

interface LaunchesAPI {
    @GET("/1.3/launch?mode=verbose")
    suspend fun getNextLaunches(): Response<LaunchesResponse>
}