package com.example.android.nadris.services

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress

class ConnectivityMonitor(val context: Context) {

    private val _isConnectedToNetwork = MutableLiveData(false)
    val isConnectedToNetwork get() = _isConnectedToNetwork

    private val _isOnline = MutableLiveData(false)
    val isOnline get() = _isOnline

    private val connectivityStatusReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
           checkConnectivity()
        }
    }

     fun checkConnectivity() {
        _isConnectedToNetwork.value = checkIfDeviceIsConnectedToANetwork(context)
        GlobalScope.launch(Dispatchers.IO) {
            _isOnline.postValue(checkIfDeviceIsOnline())
        }
    }

    init {
        registerConnectivityStatusReceiver()
    }

    private fun registerConnectivityStatusReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        context.registerReceiver(connectivityStatusReceiver,intentFilter)
    }

    private fun checkIfDeviceIsConnectedToANetwork(context:Context): Boolean {
        val connectivityManager =
            context.getSystemService(Application.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }

    }

    private fun checkIfDeviceIsOnline(): Boolean {
        return try {
            val timeoutMs = 1500
            val sock = Socket()
            val sockAddr: SocketAddress = InetSocketAddress("8.8.8.8", 53)
            sock.connect(sockAddr, timeoutMs)
            sock.close()
            true
        } catch (e: IOException) {
            false
        }
    }

}