package com.example.android.nadris.network.services

import com.example.android.nadris.network.dtos.*
import retrofit2.Response
import retrofit2.http.*

interface SubjectsService {
    @GET("api/Subject/Grade/{id}")
    suspend fun getSubjectsWithGradeId(@Path("id")id:Long, @Header("authorization") token: String): Response<List<SubjectDTO>>

    @GET("api/Subject/{id}/units")
    suspend fun  getSubjectUnit(@Path("id")id: Long , @Header("authorization") token: String):Response<List<SubjectUnitDTO>>


    @GET("api/Subject/Section/{id}")
    suspend fun getSubjectsWithSectionId(@Path("id")id:Long, @Header("authorization") token: String): Response<List<SubjectDTO>>

    @GET("api/Subject/{id}")
    suspend fun getSubjectWithId(@Path("id")id:Long, @Header("authorization") token: String): Response<SubjectDTO>

    }