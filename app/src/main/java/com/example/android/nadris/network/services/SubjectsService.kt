package com.example.android.nadris.network.services

import com.example.android.nadris.network.dtos.*
import retrofit2.Response
import retrofit2.http.*

interface SubjectsService {
    @GET("api/Subject/Grade/{id}")
    suspend fun getGradeSubjects(@Path("id")id:Long , @Header("authorization") token: String): Response<List<SubjectDTO>>

    @GET("api/Courses/Teacher")
    suspend fun getTeacherSubjects(@Header("authorization") token: String):Response<List<TeacherSubjectDTO>>

    @POST("api/Courses/Teacher")
    suspend fun addTeacherSubject(@Body addSubjectDTO: AddSubjectDTO ,@Header("authorization") token: String):Response<TeacherSubjectDTO>


    @GET("api/Subject/{id}/units")
    suspend fun  getSubjectUnit(@Path("id")id: Long , @Header("authorization") token: String):Response<List<SubjectUnitDTO>>
}