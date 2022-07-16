package com.example.android.nadris.database.dao

import androidx.room.*
import com.example.android.nadris.database.models.*

@Dao
interface SubjectDao {
    @Query("SELECT * FROM DatabaseTeacherSubject")
    suspend fun getTeacherSubjects(): List<DatabaseTeacherSubject>
    @Query("SELECT * FROM DatabaseStudentSubject")
    suspend fun getRegisteredCoursesForAStudent():List<DatabaseStudentSubject>

    @Query("SELECT * FROM DatabaseTeacherSubject where id=:id")
    suspend fun getSubject(id: Long): DatabaseTeacherSubject

    @Query("SELECT * FROM DatabaseSubjects where id=:id")
    suspend fun getSubjects(id: Long): DatabaseSubjects


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRegisteredCoursesForAStudent(list: List<DatabaseStudentSubject>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeacherSubjects(list: List<DatabaseTeacherSubject>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(item: DatabaseTeacherSubject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjects(list: List<DatabaseSubjects>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnitLessons(list: List<DatabaseLesson>)

    @Query("SELECT * FROM databaselesson where FKUnitId=:id")
    suspend fun getUnitLessons(id: Long): List<DatabaseLesson>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjectUnits(list: List<DatabaseSubjectUnit>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjectUnit(unitDatabase: DatabaseSubjectUnit)

    @Transaction
    @Query("SELECT * FROM DatabaseSubjectUnit where subjectId=:subjectId")
    suspend fun getSubjectUnits(subjectId: Long): List<DatabaseUnitLessons>
}