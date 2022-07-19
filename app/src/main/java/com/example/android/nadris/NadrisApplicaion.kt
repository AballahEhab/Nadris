package com.example.android.nadris

import android.app.Application
import com.example.android.nadris.database.models.DatabaseUser
import com.example.android.nadris.network.firebase.dtos.User
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.services.ConnectivityMonitor
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class NadrisApplication : Application() {

    companion object {
        var instance: NadrisApplication? = null
        var currentDatabaseUser : DatabaseUser? = null
        var currentUserData : User? = null
    }

    @Inject
    lateinit var repo: Repository

    lateinit var connectivityMonitor:ConnectivityMonitor

    var lang : String? = null

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        if (instance == null) instance = this

        connectivityMonitor = ConnectivityMonitor(this)

    }
}