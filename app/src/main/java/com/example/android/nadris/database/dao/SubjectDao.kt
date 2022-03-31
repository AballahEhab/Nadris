package com.example.android.nadris.database.dao

import androidx.room.*
import com.example.android.nadris.database.models.Lesson
import com.example.android.nadris.database.models.SubjectUnit
import com.example.android.nadris.database.models.TeacherSubject
import com.example.android.nadris.database.models.UnitLessons

@Dao
interface SubjectDao {
    @Query("SELECT * FROM TeacherSubject")
    suspend fun getSubjects(): List<TeacherSubject>

    @Query("SELECT * FROM TeacherSubject where id=:id")
    suspend fun getSubject(id: Long): TeacherSubject

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjects(list: List<TeacherSubject>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(item: TeacherSubject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnitLessons(list: List<Lesson>)

    @Query("SELECT * FROM lesson where unitId=:id")
     fun getUnitLessons(id:Long): List<Lesson>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjectUnits(list: List<SubjectUnit>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjectUnit(unit: SubjectUnit)

    @Transaction
    @Query("SELECT * FROM SubjectUnit")
    suspend fun getSubjectUnits(): List<UnitLessons>
}