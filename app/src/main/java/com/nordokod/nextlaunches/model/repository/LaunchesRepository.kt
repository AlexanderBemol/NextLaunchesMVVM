package com.nordokod.nextlaunches.model.repository

import android.content.Context
import com.nordokod.nextlaunches.model.entity.Launches
import com.nordokod.nextlaunches.model.source.LaunchesAPI
import com.nordokod.nextlaunches.model.source.LaunchesDAO
import com.nordokod.nextlaunches.utils.NetworkManager.Companion.isOnline
import com.nordokod.nextlaunches.utils.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class LaunchesRepository (
    private val api : LaunchesAPI,
    private val dao : LaunchesDAO,
    private val context: Context
) : ILaunchesRepository {
    override suspend fun getAllLaunches(): RequestResult<List<Launches>> {
        if(isOnline(context)){
            return try{
                val response = api.getNextLaunches()
                if(response.isSuccessful){
                    response.body().let {
                        withContext(Dispatchers.IO){
                            it?.launches?.let {
                                    it1 -> dao.add(it1)
                                    RequestResult.Success(it1)
                            } ?: RequestResult.Error(Exception("API ERROR"))
                        }
                    }
                }else{
                    RequestResult.Error(Exception("API ERROR"))
                }
            }catch (e : Exception){
                RequestResult.Error(e)
            }
        }else{
            val data = withContext(Dispatchers.IO){
                dao.findAll()
            }
            return if (data.isNotEmpty()) RequestResult.Success(data) else RequestResult.Error(
                Exception("NO DATA")
            )
        }
    }

}