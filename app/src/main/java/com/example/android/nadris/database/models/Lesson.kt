package com.example.android.nadris.database.models

import androidx.room.*

@Entity()
data class Lesson (
    @PrimaryKey val lessonID: Long,
    val lessonName:String,
    val unitId: Long
    )

data class UnitLessons(
    @Embedded
    val unit:SubjectUnit,
    @Relation(
        parentColumn = "unitId",entityColumn = "unitId"
    )
    val lessons: List<Lesson>
)