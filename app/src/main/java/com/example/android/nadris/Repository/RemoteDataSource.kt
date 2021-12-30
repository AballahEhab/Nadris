package com.example.android.nadris.repository

import com.example.android.nadris.network.NadrisAPIService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val nadrisAPIService:NadrisAPIService) {
}