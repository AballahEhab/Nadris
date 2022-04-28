package com.example.android.nadris.repository

import com.example.android.nadris.database.NadrisDatabase
import com.example.android.nadris.database.models.*
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val nadrisDatabase: NadrisDatabase) {

    private val userDao = nadrisDatabase.UserDao()

    private val postDao = nadrisDatabase.PostDao()
    private val subjectDao = nadrisDatabase.SubjectDao()

     suspend fun addUserData(userData: UserData)= userDao.insertUser(userData)

    suspend fun getUserData() = userDao.getUser()

    suspend fun updateUserData(updatedUserData: UserData) = userDao.updateUser(updatedUserData)

    suspend fun getAllPosts() = postDao.getAllPosts()

    fun getPostById(postId: Long) = postDao.getPostByPostId(postId)

    suspend fun insertPosts(databasePosts: List<DatabasePost>) = postDao.insertPosts(databasePosts)

    suspend fun insertPost(databasePosts: DatabasePost) = postDao.insertPost(databasePosts)

    suspend fun updatePost(databasePosts: DatabasePost) = postDao.updatePost(databasePosts)
    suspend fun deleteDiscussion(discussionId: Long) = postDao.deleteDiscussion(discussionId)

    suspend fun getSavedDiscussionsIds() = postDao.getSavedDiscussionsIds()
    suspend fun updateAllSavedDiscussions(discussions:List<DatabasePost>) = postDao.updateAllSavedDiscussions(discussions)

    suspend fun insertSubjects(list: List<TeacherSubject>) = subjectDao.insertSubjects(list)

    suspend fun addRegisteredCoursesForAStudent(list: List<StudentSubject>)=subjectDao.addRegisteredCoursesForAStudent(list)

    suspend fun insertSubject(item: TeacherSubject) = subjectDao.insertSubject(item)

    suspend fun getSubjects() = subjectDao.getSubjects()
    suspend fun getRegisteredCoursesForAStudent()=subjectDao.getRegisteredCoursesForAStudent();

    suspend fun getSubjectById(subjectId: Long) = subjectDao.getSubject(subjectId)
    suspend fun deleteUser(user:UserData) = userDao.deleteUser(user)

    suspend fun insertSubjectUnits(list: List<SubjectUnit>) = subjectDao.insertSubjectUnits(list)

    suspend fun insertUnitLessons(list: List<Lesson>) = subjectDao.insertUnitLessons(list)

    suspend fun insertSubjectUnit(unit: SubjectUnit) = subjectDao.insertSubjectUnit(unit)

    suspend fun getSubjectUnits(id :Long) = subjectDao.getSubjectUnits(id)

    suspend fun getUnitLessons(id: Long) = subjectDao.getUnitLessons(id)
}