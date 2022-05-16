package com.example.android.nadris.network.services

import com.example.android.nadris.network.dtos.AddSubjectDTO
import com.example.android.nadris.network.dtos.SubjectDTO
import com.example.android.nadris.network.dtos.TeacherSubjectDTO
import com.example.android.nadris.network.dtos.UnitDTO
import retrofit2.Response
import retrofit2.http.*

interface SubjectsService {
    @GET("api/Grade/{id}/subjects")
    suspend fun getSubjectsWithGradeId(@Path("id")id:Long, @Header("authorization") token: String): Response<List<SubjectDTO>>

    @GET("api/Subject/Section/{id}")
    suspend fun getSubjectsWithSectionId(@Path("id")id:Long, @Header("authorization") token: String): Response<List<SubjectDTO>>

    @GET("api/Subject/{id}")
    suspend fun getSubjectWithId(@Path("id")id:Long, @Header("authorization") token: String): Response<SubjectDTO>

    @GET("api/Subject/{id}/Units")
    suspend fun getUnitsWithSubjectId(@Path("id")id:Long, @Header("authorization") token: String): Response<List<UnitDTO>>

    @GET("api/Grade/{id}/subjects")
    suspend fun getGradeSubjectsWithId(@Path("id")id: Long,@Header("authorization") token: String):Response<List<SubjectDTO>>
}