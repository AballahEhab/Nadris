package com.example.android.nadris.repository

import com.example.android.nadris.database.NadrisDatabase
import com.example.android.nadris.database.models.*
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val nadrisDatabase: NadrisDatabase) {

    private val userDao = nadrisDatabase.UserDao()

    private val postDao = nadrisDatabase.PostDao()
    private val subjectDao = nadrisDatabase.SubjectDao()

     suspend fun addUserData(databaseUser: DatabaseUser)= userDao.insertUser(databaseUser)

    suspend fun getUserData() = userDao.getUser()

    suspend fun updateUserData(updatedDatabaseUser: DatabaseUser) = userDao.updateUser(updatedDatabaseUser)

    suspend fun getAllPosts() = postDao.getAllPosts()

    fun getPostById(postId: String) = postDao.getPostByPostId(postId)

    suspend fun insertPosts(databasePosts: List<DatabasePost>) = postDao.insertPosts(databasePosts)

    suspend fun insertPost(databasePosts: DatabasePost) = postDao.insertPost(databasePosts)

    suspend fun updatePost(databasePosts: DatabasePost) = postDao.updatePost(databasePosts)
    suspend fun deleteDiscussion(discussionId: String) = postDao.deleteDiscussion(discussionId)

    suspend fun getSavedDiscussionsIds() = postDao.getSavedDiscussionsIds()
    suspend fun updateAllSavedDiscussions(discussions:List<DatabasePost>) = postDao.updateAllSavedDiscussions(discussions)

    suspend fun insertTeacherSubjects(list: List<DatabaseTeacherSubject>) = subjectDao.insertTeacherSubjects(list)

    suspend fun addRegisteredCoursesForAStudent(list: List<DatabaseStudentSubject>)=subjectDao.addRegisteredCoursesForAStudent(list)

    suspend fun insertSubject(item: DatabaseTeacherSubject) = subjectDao.insertSubject(item)

    suspend fun getSubjects() = subjectDao.getTeacherSubjects()
    suspend fun getRegisteredCoursesForAStudent()=subjectDao.getRegisteredCoursesForAStudent();

    suspend fun getSubjectById(subjectId: Long) = subjectDao.getSubject(subjectId)
    suspend fun deleteUser(databaseUser:DatabaseUser) = userDao.deleteUser(databaseUser)

    suspend fun insertSubjectUnits(list: List<DatabaseSubjectUnit>) = subjectDao.insertSubjectUnits(list)

    suspend fun insertUnitLessons(list: List<DatabaseLesson>) = subjectDao.insertUnitLessons(list)

    suspend fun insertSubjectUnit(unitDatabase: DatabaseSubjectUnit) = subjectDao.insertSubjectUnit(unitDatabase)

    suspend fun getSubjectUnits(id :Long) = subjectDao.getSubjectUnits(id)

    suspend fun getUnitLessons(id: Long) = subjectDao.getUnitLessons(id)

    suspend fun insertSubjects(list: List<DatabaseSubjects>) = subjectDao.insertSubjects(list)

    suspend fun getSubjectsWithGrade(id : Long) = subjectDao.getSubjects(id)

}