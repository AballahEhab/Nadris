package com.example.android.nadris.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.nadris.database.models.TeacherSubject

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
}