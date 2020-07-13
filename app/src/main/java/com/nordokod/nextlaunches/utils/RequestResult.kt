package com.nordokod.nextlaunches.utils

import java.lang.Exception

sealed class RequestResult<out T>{
    data class Success<out T>(val data : T) : RequestResult<T>()
    data class Error(val exception: Exception) : RequestResult<Nothing>()
}