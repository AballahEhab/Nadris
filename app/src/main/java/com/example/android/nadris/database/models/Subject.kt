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
data class DatabaseTeacherCourse(
    @PrimaryKey val courseId: String,
    val subjectName: String,
    val gradeName: String,
    val numOfStudents:Int,
    )
@Entity
data class DatabaseStudentCourse(
    @PrimaryKey  val id: String,
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
    @PrimaryKey  val id: String,
    val name: String,
    val numOfTeachers:Long,
    val icon: Int,
)