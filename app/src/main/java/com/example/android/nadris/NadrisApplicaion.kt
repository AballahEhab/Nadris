package com.example.android.nadris

import android.app.Application
import android.content.res.Configuration
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.DisplayMetrics
import com.example.android.nadris.database.models.UserData
import com.example.android.nadris.repository.Repository
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.auth.User
import dagger.hilt.android.HiltAndroidApp
import java.util.*
import javax.inject.Inject

@HiltAndroidApp
class NadrisApplication : Application() {

    companion object {
        var instance: NadrisApplication? = null
        var currentUserLocalData : UserData? = null
        var currentUserData : User? = null
    }

    @Inject
    lateinit var repo: Repository


    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        if (instance == null) instance = this

//        setLocale("ar")
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

    private fun setLocale(lang: String?) {
        val myLocale = Locale(lang)
        val res: Resources = resources
        val dm: DisplayMetrics = res.displayMetrics
        val conf: Configuration = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
//        val refresh = Intent(this, MainActivity::class.java)
//        finish()
//        startActivity(refresh)
    }
}