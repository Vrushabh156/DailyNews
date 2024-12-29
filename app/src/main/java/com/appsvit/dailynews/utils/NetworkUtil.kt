package com.appsvit.dailynews.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkUtil @Inject constructor(
    @ApplicationContext private val context: Context
) : LiveData<Status>() {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun onActive() {
        super.onActive()
        checkConnectivity()
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(Status.AVAILABLE)
        }

        override fun onLosing(network: Network, maxMsToLive: Int) {
            super.onLosing(network, maxMsToLive)
            postValue(Status.LOSING)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            postValue(Status.UNAVAILABLE)
        }
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }


    private fun checkConnectivity() {
        val network = connectivityManager.activeNetwork
        if (network == null) {
            postValue(Status.UNAVAILABLE)
        }
        val requestBuilder = NetworkRequest.Builder().apply {
            addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)

        }.build()
        connectivityManager.registerNetworkCallback(
            requestBuilder,
            networkCallback
        )
    }
}
