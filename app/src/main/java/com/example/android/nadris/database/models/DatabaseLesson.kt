package com.example.android.nadris.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity()
data class DatabaseLesson (
    @PrimaryKey val lessonID: Long,
    val lessonName:String,
    val FKUnitId: Long
    )

data class DatabaseUnitLessons(
    @Embedded
    val unitDatabase:DatabaseSubjectUnit,
    @Relation(
        parentColumn = "unitId",entityColumn = "FKUnitId"
    )
    val databaseLessons: List<DatabaseLesson>
)