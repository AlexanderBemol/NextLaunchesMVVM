package com.nordokod.nextlaunches.ui.launches

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nordokod.nextlaunches.model.entity.Launches
import com.nordokod.nextlaunches.model.repository.ILaunchesRepository
import com.nordokod.nextlaunches.utils.RequestResult
import kotlinx.coroutines.launch
import java.lang.Exception

class LaunchesViewModel(private val repository: ILaunchesRepository) : ViewModel() {
    val launchesList = MutableLiveData<List<Launches>>()
    val error = MutableLiveData<Exception>()

    fun getLaunches(){
        viewModelScope.launch {
            val result = repository.getAllLaunches()
            if(result is RequestResult.Success){
                launchesList.value = result.data
            }else if(result is RequestResult.Error){
                error.value = result.exception
            }
        }
    }
}