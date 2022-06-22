package com.example.android.nadris.network.services

import com.example.android.nadris.network.dtos.CollegeDTO
import com.example.android.nadris.network.dtos.UniversityDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UniversityService {

    @GET("api/University")
    suspend fun getUniversities(): Response<List<UniversityDTO>>

    @GET("api/University/{id}/Colleges")
    suspend fun getCollegesWithUniversityId(@Path("id") id: Int): Response<List<CollegeDTO>>
}