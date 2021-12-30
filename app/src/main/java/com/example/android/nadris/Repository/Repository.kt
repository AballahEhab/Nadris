package com.example.android.nadris.repository

import com.example.android.nadris.network.NadrisAPIService
import javax.inject.Inject


class Repository @Inject constructor(val localDataSource: LocalDataSource,val remoteDataSource: RemoteDataSource) {


}