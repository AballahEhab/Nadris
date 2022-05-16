package com.example.android.nadris.network.services

import com.example.android.nadris.network.dtos.*
import retrofit2.Response
import retrofit2.http.*

interface CoursesService {
    @GET("api/Courses/Teacher")
    suspend fun getTeacherCourses(@Header("authorization") token: String): Response<List<TeacherSubjectDTO>>

    @POST("api/Courses/Teacher")
    suspend fun addSubjectForATeacher(@Body addSubjectDTO: AddSubjectDTO, @Header("authorization") token: String): Response<TeacherSubjectDTO>

    @DELETE("api/Courses/{id}")
    suspend fun removeCourse(@Path("id")id: Long,@Header("authorization") token: String):Response<Boolean>
    @GET("api/Courses/Student")
    suspend fun getRegisteredCoursesForAStudent(@Header("authorization") token: String):Response<List<StudentSubjectDTO>>

    @GET("api/subjects/{id}/courses")
    suspend fun getTeachersCourses(@Path("id")id: Long, @Header("authorization") token: String):Response<List<TeachersCoursesDTO>>
//    @GET("api/Courses/Student")
//    suspend fun getRegisteredCoursesForAStudent(@Header("authorization") token: String):Response<List<Courses>>

    @POST("api/Courses/Student")
    suspend fun registerAStudentInACourse(@Header("authorization") token: String,@Body courseId:CourseID):Response<StudentSubjectDTO>

//    @POST("api/Courses/Student")
//    suspend fun addLessonForACourse(@Header("authorization") token: String,@Body lesson:AddLessonDTO):Response<>

//    @GET("api/Courses/Teacher")
//    suspend fun getCoursesForATeacher(@Header("authorization") token: String):Response<List<Courses>>

//    @GET("api/Course/{id}/units")
//    suspend fun getUnitsWithCourseId(@Path("id") id:Int,@Header("authorization") token: String):Response<>
    }
