package com.example.android.nadris.network.firebase

import com.example.android.nadris.database.models.UserData
import com.example.android.nadris.network.firebase.dtos.User

object NetworkObjectMapper {
    fun userToDatabaseUser(user: User) =
        UserData(
            userID = user.id,
            Email= user.email,
            firstName = user.firstName,
            lastName = user.lastName,
            PhoneNumber = user.phoneNumber,
            Type = user.type,
            Gender = user.gender,
            GradeId = user.grade?.id,
        )

//    fun postAsDatabaseModel(inquiry: Inquiry): DatabasePost {
//        var hasImage = false
//        if (!inquiry.image_path.isNullOrEmpty()) {
//            NadrisApplication.instance?.let {
//                Converter(it.applicationContext).convertFromBase64ToBitmap(networkPost.imgStrB64,
//                    networkPost.id.toString())
//            }
//            hasImage = true
//        }
//        if (!networkPost.profilePicBase64.isNullOrEmpty()) {
//            NadrisApplication.instance?.let {
//                Converter(it.applicationContext).convertFromBase64ToBitmap(networkPost.profilePicBase64,
//                    networkPost.userId)
//            }
//        }
//        return DatabasePost(
//            inquiry.id,
//            hasImage,
//            inquiry.subjectName,
//            inquiry.content,
//            inquiry.votes,
//            inquiry.numOfComments,
//            inquiry.time,
//            inquiry.userId,
//            inquiry.name,
//            inquiry.isVoted,
//        )
//    }

    /**
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
*/

}