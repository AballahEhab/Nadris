package com.example.android.nadris.repository

import com.example.android.nadris.database.models.UserData
import com.example.android.nadris.network.firebase.NetworkObjectMapper
import com.example.android.nadris.network.firebase.dtos.College
import com.example.android.nadris.network.firebase.dtos.Grade
import com.example.android.nadris.network.firebase.dtos.University
import com.example.android.nadris.network.firebase.dtos.User
import com.example.android.nadris.util.Result
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.toObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {

    fun getCurrentFirebaseUser() = remoteDataSource.getCurrentUser()

    suspend fun getLocalUserData() = localDataSource.getUserData()

    suspend fun signInWithEmailAndPassword(checkedEmail: String, checkedPassword: String, ): Result<UserData> {

        try {
            val userDataDoc = Tasks.await(remoteDataSource.singInWithEmailAndPassword(checkedEmail,
                checkedPassword))

            var userData = userDataDoc.toObject<User>()

            userData?.id = userDataDoc.id

            userData
                ?.let { NetworkObjectMapper.userToDatabaseUser(it) }
                ?.let { localDataSource.addUserData(it) }

            return Result.Success(localDataSource.getUserData())

        } catch (throwable: Throwable) {

            return Result.Error(throwable.message!!)
        }

        return Result.Error("unexpected error occur")
    }

    suspend fun getGrades(): Result<MutableList<Grade>> {
        try {
            val gradesSnapshots = Tasks.await(remoteDataSource.getGrades())

            var gradeList:MutableList<Grade> = mutableListOf()

            for (document in gradesSnapshots){
                val departmentsCollection = document.reference.collection("departments")
                val departmentsQuery = Tasks.await(departmentsCollection.get())
                if (!departmentsQuery.isEmpty)
                    for (department in departmentsQuery) {
                        val divisionsCollection = department.reference.collection("divisions")
                        val divisionsQuery = Tasks.await(divisionsCollection.get())

                        if (!divisionsQuery.isEmpty)
                            for (division in divisionsQuery) {
                                var grade = division.toObject<Grade>()
                                grade.gradeReference = division.reference
                                gradeList.add(grade)

                            }
                        else{
                            var grade = department.toObject<Grade>()
                            grade.gradeReference = department.reference
                            gradeList.add(grade)

                        }
                    }

                else {
                    var grade = document.toObject<Grade>()
                    grade.gradeReference = document.reference
                    gradeList.add(grade)
                }
            }

            return Result.Success(gradeList)

        }catch(throwable: Throwable){

            return Result.Error(throwable.message!!)
        }
        return Result.Error("unexpected error occur")
    }

    suspend fun createNewUser(user:User,checkedPassword:String): Result<UserData> {

        try {
             Tasks.await(remoteDataSource.signUPWithEmailAndPassword(user, checkedPassword))

            var firebaseUser = getCurrentFirebaseUser()

            user.id = firebaseUser?.uid!!

            user.let { NetworkObjectMapper.userToDatabaseUser(it) }
                .let { localDataSource.addUserData(it) }

            return Result.Success(localDataSource.getUserData())

        } catch (throwable: Throwable) {

            return Result.Error(throwable.message!!)
        }

        return Result.Error("unexpected error occur")
    }

    suspend fun getAllUniversities(): Result<List<University>> {

        return try {

            val universitiesQuerySnapshot = Tasks.await(remoteDataSource.getAllUniversities())

            val universitiesList = universitiesQuerySnapshot.map {
                var university = it.toObject<University>()
                university.docRef = it.reference
                university
            }

            Result.Success(universitiesList)

        }catch (e:Exception){

            Result.Error(e.message!!)

        }

        return Result.Error("unexpected error occur")
    }

    suspend fun getCollegeForAUniversity(universityDocRef: DocumentReference):Result<List<College>> {
        return try{
            val collegesQuery =
                Tasks.await(remoteDataSource.getCollegeForAUniversity(universityDocRef))

            val collegesList = collegesQuery.map {
                val college = it.toObject<College>()
                college.docRef = it.reference
                college
            }

            Result.Success(collegesList)

        }catch (e:Exception){
            Result.Error(e.message!!)

        }
        return Result.Error("unexpected error occur")


    }



//    fun getCurrentUser() = Tasks.await(remoteDataSource.currentUser())

//    fun login(loginAccountModel: LoginAccountModel) = postToApiAndSaveToDatabase(
//        request = { remoteDataSource.login(loginAccountModel) },
//        saveFetchResult = { user -> localDataSource.addUserData(user) },
//        convertToDatabaseModel = { networkModel ->
//            NetworkModelsMapper.authModelAsDataBaseModel(networkModel)
//        }
//    )
//
//    fun registerNewStudentAccount(accountDataModel: CreateStudentAccountDataModelModel) =
//        postToApiAndSaveToDatabase(
//            request = { remoteDataSource.createStudentAccount(accountDataModel) },
//            saveFetchResult = { user -> localDataSource.addUserData(user) },
//            convertToDatabaseModel = { networkModel ->
//                NetworkModelsMapper.authModelAsDataBaseModel(networkModel)
//            }
//        )
//
//    fun registerNewTeacherAccount(createTeacherAccountDataModelModel: CreateTeacherAccountDataModelModel) =
//        postToApiAndSaveToDatabase(
//            request = { remoteDataSource.createTeacherAccount(createTeacherAccountDataModelModel) },
//            saveFetchResult = { user -> localDataSource.addUserData(user) },
//            convertToDatabaseModel = { networkModel ->
//                NetworkModelsMapper.authModelAsDataBaseModel(networkModel)
//            }
//        )
//
//    suspend fun getUser() = localDataSource.getUserData()
//
//    fun getPosts(token: String) = getFromApiAndSaveToDataBase(
//        query = { localDataSource.getAllPosts() },
//        fetch = { remoteDataSource.getAllPosts(token) },
//        convertToDatabaseModel = { networkPostsList ->
//            networkPostsList.map { networkPost ->
//                NetworkModelsMapper.postAsDatabaseModel(networkPost)
//            }
//        },
//        saveFetchResult = { list_of_posts -> list_of_posts.let { localDataSource.insertPosts(it) } }
//    )
//
//    suspend fun getSubjectUnit(subjectID: Long, token: String) = getFromApiAndSaveToDataBase(
//
//        query = { localDataSource.getSubjectUnits(subjectID) },
//        fetch = { remoteDataSource.getSubjectUnit(subjectID, token) },
//        convertToDatabaseModel = { list ->
//            list.map { item ->
//                NetworkModelsMapper.subjectUnitsDTOtoModel(item)
//            }
//        },
//        saveFetchResult = { list ->
//            list.map {
//                localDataSource.insertSubjectUnit(it.unit)
//                localDataSource.insertUnitLessons(it.lessons)
//            }
//        }
//
//    )
//
//    suspend fun updatePostById(post: DatabasePost) = localDataSource.updatePost(post)
//
//    suspend fun getPostById(postId: Long) = localDataSource.getPostById(postId)
//
//    fun publishPost(post: CreatePostModel, token: String) = postToApiAndSaveToDatabase(
//        request = { remoteDataSource.publishAPost(post, token) },
//        convertToDatabaseModel = { networkPost ->
//            NetworkModelsMapper.postAsDatabaseModel(networkPost)
//        },
//        saveFetchResult = { post -> localDataSource.insertPost(post) }
//    )
//
//    fun getPostsByUserId(userID: String, token: String) = requestAPI(
//        fetch = { remoteDataSource.getPostsByUserId(userID, token) }
//    )
//
//    fun getGradeSubjects(gradeId: Long, token: String) = requestAPI(
//        fetch = { remoteDataSource.getSubjectsWithGradeId(gradeId, token) }
//    )
//
////    suspend fun getSubjectUnit(subjectID: Long, token: String) = getFromApiAndSaveToDataBase(
////
////        query = { localDataSource.getSubjectUnits(subjectID) },
////        fetch = { remoteDataSource.getSubjectUnit(subjectID, token) },
////        convertToDatabaseModel = { list ->
////            list.map { item ->
////                NetworkModelsMapper.subjectUnitsDTOtoModel(item)
////            }
////        },
////        saveFetchResult = { list ->
////            list.map {
////                localDataSource.insertSubjectUnit(it.unit)
////                localDataSource.insertUnitLessons(it.lessons)
////            }
////        }
////
////    )
//
//    fun getTeachersCourses(subjectID: Long, token: String) =
//        requestAPI(fetch = { remoteDataSource.getTeachersCourses(subjectID, token) })
//
//    fun getSubjects(gradeId: Long, token: String) =
//        requestAPI(fetch = { remoteDataSource.getGradeSubjectsWithId(gradeId, token) })
//
//    fun getAllComments(token: String, postId: Long) = requestAPI(
//        fetch = { remoteDataSource.getCommentsByPostId(postId, token) })
//
//    fun updateDiscussion(postId:Long,updatedDiscussion: EditDiscussion, token: String) = requestAPI(
//        fetch = { remoteDataSource.updateDiscussion(postId,updatedDiscussion, token) })
//
//    fun removeCourse(token: String,courseId:Long)= requestAPI(
//        fetch = {remoteDataSource.removeCourse(token,courseId)}
//    )
//
//
//    fun vote(voteModel: VoteModel, token: String) =
//        postToApiAndSaveToDatabase(
//            request = { remoteDataSource.vote(voteModel, token) },
//            saveFetchResult = { post -> localDataSource.updatePost(post) },
//            convertToDatabaseModel = { networkMPost ->
//                NetworkModelsMapper.postAsDatabaseModel(networkMPost)
//            }
//        )
//
//    suspend fun publishComment(comment: PublishCommentModel,postId:Long, token: String) = requestAPI(
//        fetch = { remoteDataSource.comment(comment,postId,token) }
//    )
//
//    suspend fun registerAStudentInACourse(CourseId: CourseID, token: String) = requestAPI(
//        fetch = { remoteDataSource.registerAStudentInACourse(CourseId, token) }
//    )
//
//    fun getUniversities() = requestAPI(
//        fetch = { remoteDataSource.getUniversities() })
//
//    fun getProfileInfo(token: String) = requestAPI(
//        fetch = { remoteDataSource.getProfileInfo(token) })
//
//    fun flowProfile(userID: String, token: String) = requestAPI(
//        fetch = { remoteDataSource.followProfile(userID, token) })
//
//    fun getPublicProfileInfo(token: String, userId: String) = requestAPI(
//        fetch = { remoteDataSource.getPublicProfileInfo(token, userId) })
//
//    fun getLastActivity(token: String) = requestAPI(
//        fetch = { remoteDataSource.getLastActivity(token) })
//
//    fun getColleges(id: Int) = requestAPI(
//        fetch = { remoteDataSource.getColleges(id) })
//
//    fun getSections() = requestAPI(
//        fetch = { remoteDataSource.getSections() })
//
//    fun getGrades() = requestAPI(
//        fetch = { remoteDataSource.getGrades() })
//
//    fun getTeacherSubject(token: String) = getFromApiAndSaveToDataBase(
//        query = { localDataSource.getSubjects() },
//        fetch = { remoteDataSource.getTeacherCourses(token) },
//        convertToDatabaseModel = { list ->
//            list.map { dto ->
//                NetworkModelsMapper.subjectDTOtoModel(dto)
//            }
//        },
//        saveFetchResult = { list -> list.let { localDataSource.insertSubjects(it as List<Subjects>) } }
//    )
//
//    fun getRegisteredCoursesForAStudent(token: String) = getFromApiAndSaveToDataBase(
//        query = { localDataSource.getRegisteredCoursesForAStudent() },
//        fetch = { remoteDataSource.getRegisteredCoursesForAStudent(token) },
//        convertToDatabaseModel = { list -> list.map { dto -> NetworkModelsMapper.studentCourseDTOtoModel(dto) } },
//        saveFetchResult = { list -> list.let { localDataSource.addRegisteredCoursesForAStudent(it as List<StudentSubject>) } }
//    )
//
//    fun addTeacherSubject(token: String, addSubjectDTO: AddSubjectDTO) = postToApiAndSaveToDatabase(
//        request = { remoteDataSource.addSubject(token, addSubjectDTO) },
//        saveFetchResult = { item -> localDataSource.insertSubject(item) },
//        convertToDatabaseModel = { item -> NetworkModelsMapper.subjectDTOtoModel(item) }
//
//
//    )
//
//    suspend fun logOut(user: UserData) = localDataSource.deleteUser(user)
//
//    fun updateProfilePic(token: String, imgStrB64: UploadPhotoDTO) = requestAPI(
//        fetch = { remoteDataSource.updateProfilePic(token,imgStrB64) })
//
//    suspend fun getUnitLessons(unitId:Long)=localDataSource.getUnitLessons(unitId)
//
//
//    suspend fun getUpdatedDiscussions(token:String): List<DatabasePost> {
//
//        //getting saved posts ids from database to update them to the database from the fetched posts
//        val savedDiscussionsIds = localDataSource.getSavedDiscussionsIds()
//
//        lateinit var allDiscussions: List<DatabasePost>
//
//        //getting all discussions from the api
//        val allDiscussionsResponse = remoteDataSource.getAllPosts(token)
//
//        // check if the request is successful
//        if(allDiscussionsResponse.isSuccessful){
//
//            //mapping network posts to database posts to be ready to be stored or shown for the user
//             allDiscussions = allDiscussionsResponse.body()?.map { discussion ->
//                 NetworkModelsMapper.postAsDatabaseModel(discussion)  }!!
//
//            // setting the saved discussions parameter isBookmarked to true for the ids fetched from the database
//            allDiscussions.map {discussion->
//                if(savedDiscussionsIds.contains(discussion.postId))
//                    discussion.isBookMarked = true
//            }
//
//            // filtering the saved discussions
//            val savedDiscussions = allDiscussions.filter { discussion->  discussion.isBookMarked }
//
//
//            // updating the database with the saved discussions data fetched from the api
//             localDataSource.updateAllSavedDiscussions(savedDiscussions)
//        }else{
//            return localDataSource.getAllPosts()
//        }
//
//        return allDiscussions
//    }
//
//    suspend fun bookmarkDiscussion(discussion:DatabasePost){
//        if(discussion.isBookMarked)
//        localDataSource.insertPost(discussion)
//        else
//            localDataSource.deleteDiscussion(discussion.postId)
//    }
//
//    suspend fun deleteDiscussion(discussionId:Long,token:String): Boolean {
//        val responce = remoteDataSource.deleteDiscussion(discussionId,token)
//        if(responce.isSuccessful && responce.body()!!) {
//            localDataSource.deleteDiscussion(discussionId)
//            return true
//        }
//            return false
//    }


}