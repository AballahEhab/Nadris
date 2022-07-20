package com.example.android.nadris.network.firebase

import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.data.models.CommentModel
import com.example.android.nadris.data.models.LessonDTO
import com.example.android.nadris.data.models.TeachersCoursesModel
import com.example.android.nadris.database.models.*
import com.example.android.nadris.network.firebase.dtos.*
import com.example.android.nadris.network.firebase.dtos.Unit

object NetworkObjectMapper {
    val TAG = "NetworkObjectMapper"
    fun userToDatabaseUser(user: User) =
        DatabaseUser(
            userID = user.id,
            Email = user.email,
            firstName = user.firstName,
            lastName = user.lastName,
            PhoneNumber = user.phoneNumber,
            IsATeacher = user.isATeacher,
            Gender = user.gender,
            GradeId = user.grade?.id,
            profileImagePath = user.image_File_Path,
        )

    fun postAsDatabaseModel(inquiry: Inquiry, name: String): DatabasePost =
        DatabasePost(
            postId = inquiry.id!!,
            imageFilePath = inquiry.image_File_Path!!,
            userImageFilePath = inquiry.userProfileImagePah,
            subject = inquiry.subjectName!!,
            content = inquiry.body!!,
            votesNum = inquiry.voted_user_ids.size,
            commentsNum = inquiry.replies_num,
            time = inquiry.time.toString(),
            userId = inquiry.userID!!,
            name = name,
            isVoted = inquiry.voted_user_ids.contains(NadrisApplication.currentDatabaseUser?.userID),
        )

    fun replyAsUIModel(reply: Reply) =
        CommentModel(
            reply.replyId!!,
            reply.replyBody!!,
            reply.userFullName!!,
            reply.time.toString()
        )

    fun NetworkCourseAsStudentCourse(course: Course) =
        DatabaseStudentCourse(
            id = course.courseId,
            name = course.subjectName,
            grade = "",
            teacherName = course.teacherName,
            section = "",
            term = "",
            progress = 0L,
            rate = 0L
        )

    fun NetworkCourseAsTeacherCourse(course: Course) =
        DatabaseTeacherCourse(
            courseId = course.courseId,
            subjectName = course.subjectName,
            gradeName = course.gradeName,
            teacherName = course.teacherName,
            numOfStudents = course.subscribedStudentsIds.size,
            teacherImagePath = course.teacherImagePath
        )

    fun NetworkCourseAsTeachersCoursesModel(course: Course) =
        TeachersCoursesModel(
            courseId = course.courseId,
            subjectName = course.subjectName,
            gradeName = course.gradeName,
            teacherName = course.teacherName,
            teacherId = course.ownerTeacherID,
            numOfStudents = course.subscribedStudentsIds.size,
            teacherImagePath = course.teacherImagePath,
            isStudentJoined = course.subscribedStudentsIds.contains(NadrisApplication.currentDatabaseUser?.userID)
        )

    fun lessonAsDataBaseLesson(lesson: LessonDTO, unitId: String) =
        DatabaseCourseLesson(
            lessonID = lesson.lessonId,
            lessonName = lesson.name,
            FKUnitId = unitId
        )

    fun unitAsDataBaseUnit(unit: Unit, lessonList: List<DatabaseCourseLesson>, iconId: Int) =
        DatabaseCourseUnitWithLessons(
            DatabaseCourseUnit(
                unitId = unit.unitId,
                name = unit.name,
                icon = iconId,
            ),
            lessonList
        )


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