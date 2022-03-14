package com.example.android.nadris.network.services

import com.example.android.nadris.network.dtos.AddSubjectDTO
import com.example.android.nadris.network.dtos.SubjectDTO
import com.example.android.nadris.network.dtos.TeacherSubjectDTO
import retrofit2.Response
import retrofit2.http.*

interface SubjectsService {
    @GET("api/Subject/Grade/{id}")
    suspend fun getGradeSubjects(@Path("id")id:Long , @Header("authorization") token: String): Response<List<SubjectDTO>>

    @GET("api/Subject/Teacher")
    suspend fun getTeacherSubjects(@Header("authorization") token: String):Response<List<TeacherSubjectDTO>>
    @POST("api/Subject/Teacher")
    suspend fun addTeacherSubject(@Body addSubjectDTO: AddSubjectDTO ,@Header("authorization") token: String):Response<TeacherSubjectDTO>

}