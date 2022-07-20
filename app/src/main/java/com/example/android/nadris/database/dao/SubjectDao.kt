package com.example.android.nadris.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.nadris.database.models.DatabaseCourseLesson
import com.example.android.nadris.database.models.DatabaseSubjects
import com.example.android.nadris.database.models.DatabaseTeacherCourse

@Dao
interface SubjectDao {

    @Query("SELECT * FROM DatabaseSubjects where id=:id")
    suspend fun getSubjects(id: Long): DatabaseSubjects

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjects(list: List<DatabaseSubjects>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(item: DatabaseTeacherCourse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnitLessons(list: List<DatabaseCourseLesson>)

}