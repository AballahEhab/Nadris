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

@Entity
data class DatabaseSection(
    val lessonId:String,
    val videoUrl:String,
    val audioFilePath:String,
    val formattedText:String,
    val FKLessonId:String
)


data class DatabaseCourseLessonWithSections(
    @Embedded val Lesson:DatabaseCourseLesson,
    @Relation(parentColumn = "lessonID", entityColumn = "FKLessonId")
    val sections: List<DatabaseSection>,
)



data class DatabaseCourseUnitWithLessonsAndSections(
    @Embedded
    val unitDatabase: DatabaseCourseUnit,
    @Relation( entity = DatabaseCourseLesson::class, parentColumn = "unitId", entityColumn = "FKUnitId")
    val databaseCourseLessons: List<DatabaseCourseLessonWithSections>,
)