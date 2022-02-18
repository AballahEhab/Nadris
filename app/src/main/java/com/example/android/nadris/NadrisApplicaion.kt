package com.example.android.nadris

import android.app.Application
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.android.nadris.database.UserData
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NadrisApplication: Application()
{

    companion object {
        var  instance:NadrisApplication? = null
        var userData: UserData? = null
    }

     var userData: UserData? = null

    override fun onCreate() {
        super.onCreate()

        if (instance == null)
            instance = this

    }


    fun hasNetwork(): Boolean {
        return isNetworkConnected()
    }


    fun isNetworkConnected(): Boolean {
        val connectivityManager =
            getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
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
}