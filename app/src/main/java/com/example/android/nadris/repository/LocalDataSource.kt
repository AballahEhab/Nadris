package com.example.android.nadris.repository

import com.example.android.nadris.database.NadrisDatabase
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.database.models.DatabaseUser
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val nadrisDatabase: NadrisDatabase) {

    private val userDao = nadrisDatabase.UserDao()

    private val postDao = nadrisDatabase.PostDao()
    private val subjectDao = nadrisDatabase.SubjectDao()

     suspend fun addUserData(databaseUser: DatabaseUser)= userDao.insertUser(databaseUser)

    suspend fun getUserData() = userDao.getUser()

    suspend fun updateUserData(updatedDatabaseUser: DatabaseUser) = userDao.updateUser(updatedDatabaseUser)

    suspend fun getAllPosts() = postDao.getAllPosts()

    suspend fun insertPost(databasePosts: DatabasePost) = postDao.insertPost(databasePosts)

    suspend fun deleteUser(databaseUser:DatabaseUser) = userDao.deleteUser(databaseUser)

//    fun getPostById(postId: String) = postDao.getPostByPostId(postId)
//
//    suspend fun insertPosts(databasePosts: List<DatabasePost>) = postDao.insertPosts(databasePosts)
//
//    suspend fun updatePost(databasePosts: DatabasePost) = postDao.updatePost(databasePosts)
//
//    suspend fun deleteDiscussion(discussionId: String) = postDao.deleteDiscussion(discussionId)
//
//    suspend fun getSavedDiscussionsIds() = postDao.getSavedDiscussionsIds()
//
//    suspend fun updateAllSavedDiscussions(discussions:List<DatabasePost>) = postDao.updateAllSavedDiscussions(discussions)
//
//    suspend fun insertTeacherSubjects(list: List<DatabaseTeacherCourse>) = subjectDao.insertTeacherSubjects(list)
//
//    suspend fun addRegisteredCoursesForAStudent(list: List<DatabaseStudentCourse>)=subjectDao.addRegisteredCoursesForAStudent(list)
//
//    suspend fun insertSubject(item: DatabaseTeacherCourse) = subjectDao.insertSubject(item)
//
//    suspend fun getSubjects() = subjectDao.getTeacherCourses()
//
//    suspend fun getRegisteredCoursesForAStudent()=subjectDao.getRegisteredCoursesForAStudent();
//
//    suspend fun getSubjectById(subjectId: Long) = subjectDao.getCourse(subjectId)
//
//    suspend fun insertSubjectUnits(list: List<DatabaseCourseUnit>) = subjectDao.insertSubjectUnits(list)
//
//    suspend fun insertUnitLessons(list: List<DatabaseCourseLesson>) = subjectDao.insertUnitLessons(list)
//
//    suspend fun insertSubjectUnit(unitDatabase: DatabaseCourseUnit) = subjectDao.insertSubjectUnit(unitDatabase)
//
//    suspend fun getSubjectUnits(id :Long) = subjectDao.getSubjectUnits(id)
//
//    suspend fun getUnitLessons(id: Long) = subjectDao.getUnitLessons(id)
//
//    suspend fun insertSubjects(list: List<DatabaseSubjects>) = subjectDao.insertSubjects(list)
//
//    suspend fun getSubjectsWithGrade(id : Long) = subjectDao.getSubjects(id)

}