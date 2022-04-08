package com.example.android.nadris.network.services

import com.example.android.nadris.network.dtos.GradeDTO
import com.example.android.nadris.network.dtos.GradeWithSectionDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header


interface GradesService {

    @GET("api/Grades")
    suspend fun getAllGrades(): Response<List<GradeDTO>>

    @GET("api/Grades/Sections")
    suspend fun getAllGradesWithSections(): Response<List<GradeDTO>>

    @GET("api/Grades/{id}/Sections")
    suspend fun getAllGradesWithSections(@Header("id") id:Int ): Response<List<GradeWithSectionDTO>>

}