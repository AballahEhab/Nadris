package com.example.android.nadris.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseSubjectUnit(
    @PrimaryKey val unitId: Long,
    val name: String,
    val icon: Int,
    val subjectId: Long,
    var lessonsVisibility: Boolean = false, // true = visible = unit expanded
) {
    fun toggleExpandUnit() {
        lessonsVisibility = !lessonsVisibility
    }
}


@Entity
data class DatabaseTeacherSubject(
    @PrimaryKey val id: Long,
    val name: String,
    val section: String,
    val term: String,
    val grade: String,
    val teacherName: String,
)
@Entity
data class DatabaseStudentSubject(
    @PrimaryKey  val id: Long,
    val name: String,
    val grade: String,
    val section: String,
    val term: String,
    val teacherName: String,
    val progress: Long,
    val rate: Long,
)
@Entity
data class DatabaseSubjects(
    @PrimaryKey  val id: Long,
    val name: String,
    val numOfTeachers:Long,
    val icon: Int,
)