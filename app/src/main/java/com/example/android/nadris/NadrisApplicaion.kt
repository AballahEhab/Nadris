package com.example.android.nadris

import android.app.Application
import com.example.android.nadris.database.models.UserData
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.services.ConnectivityMonitor
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.auth.User
import dagger.hilt.android.HiltAndroidApp
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

    lateinit var connectivityMonitor:ConnectivityMonitor

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        if (instance == null) instance = this

        connectivityMonitor = ConnectivityMonitor(this)


    }
}