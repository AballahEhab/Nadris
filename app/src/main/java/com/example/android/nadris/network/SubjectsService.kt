package com.example.android.nadris.network

import retrofit2.Response
import retrofit2.http.*

interface SubjectsService {
    @GET("api/Subject/Grade/{id}")
    suspend fun getGradeSubjects(@Path("id")id:Long , @Header("authorization") token: String): Response<List<SubjectDTO>>

}