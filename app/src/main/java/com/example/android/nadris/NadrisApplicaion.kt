package com.example.android.nadris

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDexApplication
import com.example.android.nadris.dagger.AppGraph
import com.example.android.nadris.dagger.DaggerAppGraph
import com.example.android.nadris.daggerModules.DataBaseModule

class NadrisApplication: Application()
{

    lateinit var appGraph:AppGraph

    override fun onCreate() {
        super.onCreate()
        appGraph = DaggerAppGraph.builder().dataBaseModule(DataBaseModule(applicationContext)).build()

    }

}