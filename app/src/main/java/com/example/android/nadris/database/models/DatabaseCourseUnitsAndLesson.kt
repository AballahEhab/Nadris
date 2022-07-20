package com.example.android.nadris.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class DatabaseCourseUnit(
    @PrimaryKey val unitId: String,
    val name: String,
    val icon: Int,
    var lessonsVisibility: Boolean = false, // true = visible = unit expanded
) {
    fun toggleExpandUnit() {
        lessonsVisibility = !lessonsVisibility
    }
}

@Entity()
data class DatabaseCourseLesson(
    @PrimaryKey val lessonID: String,
    val lessonName: String,
    val FKUnitId: String,
)

data class DatabaseCourseUnitWithLessons(
    @Embedded
    val unitDatabase: DatabaseCourseUnit,
    @Relation(parentColumn = "unitId", entityColumn = "FKUnitId")
    val databaseCourseLessons: List<DatabaseCourseLesson>,
)