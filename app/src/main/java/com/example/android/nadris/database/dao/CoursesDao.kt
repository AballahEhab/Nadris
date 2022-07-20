package com.example.android.nadris.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.nadris.database.models.DatabaseCourseLesson
import com.example.android.nadris.database.models.DatabaseCourseUnit
import com.example.android.nadris.database.models.DatabaseStudentCourse
import com.example.android.nadris.database.models.DatabaseTeacherCourse


@Dao
interface CoursesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRegisteredCoursesForAStudent(list: List<DatabaseStudentCourse>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeacherCourses(list: List<DatabaseTeacherCourse>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourseUnit(unitDatabase: DatabaseCourseUnit)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourseUnitsList(list: List<DatabaseCourseUnit>)

    @Query("SELECT * FROM DatabaseTeacherCourse")
    suspend fun getTeacherCourses(): List<DatabaseTeacherCourse>

    @Query("SELECT * FROM DatabaseStudentCourse")
    suspend fun getStudentSubscribedCourses(): List<DatabaseStudentCourse>

    @Query("SELECT * FROM DatabaseTeacherCourse where courseId=:id")
    suspend fun getTeacherCourseWithId(id: Long): DatabaseTeacherCourse

//    @Transaction
//    @Query("SELECT * FROM DatabaseCourseUnit where subjectId = :subjectId")
//    suspend fun getCourseUnits(subjectId: Long): List<DatabaseCourseUnitWithLessons>

    @Query("SELECT * FROM DatabaseCourseLesson where FKUnitId = :unitId")
    suspend fun getCourseLessons(unitId: Long): List<DatabaseCourseLesson>
}