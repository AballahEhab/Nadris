package com.example.android.nadris.network

import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.R
import com.example.android.nadris.database.models.*
import com.example.android.nadris.network.dtos.*
import com.example.android.nadris.services.Converter

object NetworkModelsMapper {
    fun postAsDatabaseModel(networkPost: NetworkPost): DatabasePost {
        var hasImage = false
        if (!networkPost.imgStrB64.isNullOrEmpty()) {
            NadrisApplication.instance?.let {
              Converter(it.applicationContext).convertFromBase64ToBitmap(networkPost.imgStrB64,
                    networkPost.id.toString())
            }
            hasImage = true
        }
        if (!networkPost.profilePicBase64.isNullOrEmpty()) {
            NadrisApplication.instance?.let {
                Converter(it.applicationContext).convertFromBase64ToBitmap(networkPost.profilePicBase64,
                    networkPost.userId)
            }
        }
        return DatabasePost(
            networkPost.id,
            hasImage,
            networkPost.subject,
            networkPost.content,
            networkPost.votes,
            networkPost.numOfComments,
            networkPost.time,
            networkPost.userId,
            networkPost.name,
            networkPost.isVoted,
        )
    }

    fun authModelAsDataBaseModel(model: AuthModel) = UserData(

        Email = model.email,
        firstName = model.firstName,
        lastName = model.lastName,
        PhoneNumber = model.phoneNumber,
        Type = model.type,
        Gender = model.gender,
        Exp = model.exp,
        GradeId = model.gradeId,
        University = model.university,
        College = model.college,
        Token = model.token
    )

    fun subjectDTOtoModel(dto: TeacherSubjectDTO) = TeacherSubject(
        dto.id,
        dto.name,
        dto.section,
        dto.term,
        dto.grade,
        dto.teacherName
    )

    fun studentCourseDTOtoModel(dto: StudentSubjectDTO) = StudentSubject(
        dto.id,
        dto.name,
        dto.grade,
        dto.section,
        dto.term,
        dto.teacherName,
        dto.progress,
        dto.rate,
    )

    fun subjectUnitsDTOtoModel(dto: UnitDTO): UnitLessons {

        var unit = SubjectUnit(
            dto.unitId,
            dto.name,
            R.drawable.ic_launcher_background,
            dto.subjectId,
            false,
        )
        var list = dto.lessons.map {
            Lesson(it.lessonId, it.name, dto.unitId)
        }
        return UnitLessons(unit, list);
    }
}