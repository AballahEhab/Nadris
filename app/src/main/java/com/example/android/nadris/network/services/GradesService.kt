package com.example.android.nadris.network.services

import com.example.android.nadris.network.dtos.GradeDTO
import com.example.android.nadris.network.dtos.SectionDTO
import retrofit2.Response
import retrofit2.http.GET


interface GradesService  {

    @GET("api/Grades/Sections")
    suspend fun getSections(): Response<List<SectionDTO>>

    @GET("api/Grades")
    suspend fun getGrades(): Response<List<GradeDTO>>

}