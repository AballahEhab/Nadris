package com.example.android.nadris.database.dao

import androidx.room.*
import com.example.android.nadris.database.models.*

@Dao
interface SubjectDao {
    @Query("SELECT * FROM TeacherSubject")
    suspend fun getTeacherSubjects(): List<TeacherSubject>
    @Query("SELECT * FROM StudentSubject")
    suspend fun getRegisteredCoursesForAStudent():List<StudentSubject>

    @Query("SELECT * FROM TeacherSubject where id=:id")
    suspend fun getSubject(id: Long): TeacherSubject

    @Query("SELECT * FROM Subjects where id=:id")
    suspend fun getSubjects(id: Long): Subjects


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRegisteredCoursesForAStudent(list: List<StudentSubject>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeacherSubjects(list: List<TeacherSubject>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(item: TeacherSubject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjects(list: List<Subjects>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnitLessons(list: List<Lesson>)

    @Query("SELECT * FROM lesson where FKUnitId=:id")
    suspend fun getUnitLessons(id: Long): List<Lesson>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjectUnits(list: List<SubjectUnit>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjectUnit(unit: SubjectUnit)

    @Transaction
    @Query("SELECT * FROM SubjectUnit where subjectId=:subjectId")
    suspend fun getSubjectUnits(subjectId: Long): List<UnitLessons>
}