package com.example.android.nadris.database.models

import androidx.fragment.app.FragmentStateManagerControl
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import javax.security.auth.Subject

@Entity()
data class Lesson (
    @PrimaryKey val lessonID: Long,
    val lessonName:String,
    val FKUnitId: Long
    )

data class UnitLessons(
    @Embedded
    val unit:SubjectUnit,
    @Relation(
        parentColumn = "unitId",entityColumn = "FKUnitId"
    )
    val lessons: List<Lesson>
)