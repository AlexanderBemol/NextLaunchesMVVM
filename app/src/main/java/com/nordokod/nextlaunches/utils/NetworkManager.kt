package com.nordokod.nextlaunches.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkManager {
    companion object {
        fun isOnline(context: Context) : Boolean{
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            connectivityManager?.run {
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                    return  when{
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ->true
                        hasTransport(NetworkCapabilities.TRANSPORT_VPN) ->true
                        else -> false
                    }
                }
            }
            return false
        }
    }
}