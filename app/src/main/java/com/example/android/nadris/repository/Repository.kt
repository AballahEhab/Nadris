package com.example.android.nadris.repository

import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.database.models.DatabaseUser
import com.example.android.nadris.network.firebase.NetworkObjectMapper
import com.example.android.nadris.network.firebase.dtos.*
import com.example.android.nadris.util.Result
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {

    //users functions
    fun getCurrentFirebaseUser() =
        remoteDataSource.getCurrentUser()

    suspend fun getLocalUserData() =
        localDataSource.getUserData()

    fun signInWithEmailAndPassword(checkedEmail: String, checkedPassword: String) =
        flow {
            emit(Result.Loading())
            try {
                val userDataDoc =
                    Tasks.await(remoteDataSource.singInWithEmailAndPassword(checkedEmail,
                        checkedPassword))

                var userData = getUserDataObjFromDocSnapShot(userDataDoc)

                userData
                    ?.let { NetworkObjectMapper.userToDatabaseUser(it) }
                    ?.let { localDataSource.addUserData(it) }

                emit(Result.Success(localDataSource.getUserData()))

            } catch (exception: Exception) {
                emit(Result.Error(exception.localizedMessage!!))
            }
        }

    suspend fun createNewUser(user: User, checkedPassword: String) =

        try {
            Tasks.await(remoteDataSource.signUPWithEmailAndPassword(user, checkedPassword))

            var firebaseUser = getCurrentFirebaseUser()

            user.id = firebaseUser?.uid!!

            user.let { NetworkObjectMapper.userToDatabaseUser(it) }
                .let { localDataSource.addUserData(it) }

            Result.Success(localDataSource.getUserData())

        } catch (throwable: Throwable) {

            Result.Error(throwable.message!!)
        }

    fun getUserData(userId: String) =
        try {
            Result.Success(getUserDataObj(userId))
        } catch (exception: Exception) {
            Result.Success(exception.message)
        }

    private fun getProfileImage(imagePath: String, userID: String): File {
        try {
            val localeFile = File.createTempFile(userID, ".jpg")
            val task =
                Tasks.await(remoteDataSource.getProfileImage(localeFile, imagePath))
            return localeFile
        } catch (exception: Exception) {
            throw exception
        }
    }

    fun getUserDataObj(userID: String): User? {
        try {
            val userSnapShot = Tasks.await(remoteDataSource.getUserData(userID))
            return getUserDataObjFromDocSnapShot(userSnapShot)

        } catch (exception: Exception) {
            throw exception
        }
    }

    private fun getUserDataObjFromDocSnapShot(userSnapShot: DocumentSnapshot?): User? {
        val userdata = userSnapShot?.toObject<User>()
        userdata?.id = userSnapShot?.id!!
        if (!userdata?.image_path.isNullOrEmpty())
            userdata?.image_File_Path =
                getProfileImage(userdata?.image_path!!, userdata?.id!!).absolutePath

        return userdata
    }


    suspend fun logOut(user: DatabaseUser) =
        try {
            localDataSource.deleteUser(user)
            remoteDataSource.signOut()
            Result.Success(true)
        } catch (exception: Exception) {
            Result.Error(exception.localizedMessage)
        }


    //grades and subjects functions
    fun getGrades() =
        try {
            val gradesSnapshots = Tasks.await(remoteDataSource.getGrades())

            var gradeList: MutableList<Grade> = mutableListOf()

            for (document in gradesSnapshots) {
                val departmentsCollection = document.reference.collection("departments")
                val departmentsQuery = Tasks.await(departmentsCollection.get())

                if (!departmentsQuery.isEmpty)

                    for (department in departmentsQuery) {

                        val divisionsCollection = department.reference.collection("divisions")
                        val divisionsQuery = Tasks.await(divisionsCollection.get())

                        if (!divisionsQuery.isEmpty)
                            for (division in divisionsQuery) {
                                val grade = getGradeFromDocumentSnapShot(division)
                                gradeList.add(grade!!)

                            }
                        else {
                            val grade = getGradeFromDocumentSnapShot(department)
                            gradeList.add(grade!!)

                        }
                    }
                else {
                    val grade = getGradeFromDocumentSnapShot(document)
                    gradeList.add(grade!!)
                }
            }

            Result.Success(gradeList)

        } catch (throwable: Throwable) {

            Result.Error(throwable.message!!)
        }

    fun getGradesWithFlows() =
        flow {
            emit(Result.Loading())
            try {
                val gradesSnapshots = Tasks.await(remoteDataSource.getGrades())

                var gradeList: MutableList<Grade> = mutableListOf()

                for (document in gradesSnapshots) {
                    val departmentsCollection = document.reference.collection("departments")
                    val departmentsQuery = Tasks.await(departmentsCollection.get())

                    if (!departmentsQuery.isEmpty)

                        for (department in departmentsQuery) {

                            val divisionsCollection = department.reference.collection("divisions")
                            val divisionsQuery = Tasks.await(divisionsCollection.get())

                            if (!divisionsQuery.isEmpty)
                                for (division in divisionsQuery) {
                                    val grade = getGradeFromDocumentSnapShot(division)
                                    gradeList.add(grade!!)

                                }
                            else {
                                val grade = getGradeFromDocumentSnapShot(department)
                                gradeList.add(grade!!)

                            }
                        }
                    else {
                        val grade = getGradeFromDocumentSnapShot(document)
                        gradeList.add(grade!!)
                    }
                }

                emit(Result.Success(gradeList))

            } catch (throwable: Throwable) {
                emit(Result.Error(throwable.message!!))
            }
        }

    private fun getGradeFromDocumentSnapShot(docSnapshot: QueryDocumentSnapshot?): Grade? {
        val grade = docSnapshot?.toObject<Grade>()
        grade?.id = docSnapshot?.id
        grade?.gradeReference = docSnapshot?.reference
        return grade
    }

    fun getSubjectsWithGrade(gradeRef: DocumentReference) =
        try {
            val subjectsQuery =
                Tasks.await(remoteDataSource.getSubjectsWithGrade(gradeRef))
            val collegesList = subjectsQuery.map {
                val subject = it.toObject<Subject>()
                subject.subject_id = it.id
                subject
            }

            Result.Success(collegesList)

        } catch (e: Exception) {
            Result.Error(e.message!!)

        }

    fun getAllUniversities() =
        try {

            val universitiesQuerySnapshot = Tasks.await(remoteDataSource.getAllUniversities())

            val universitiesList = universitiesQuerySnapshot.map {
                var university = it.toObject<University>()
                university.docRef = it.reference
                university
            }

            Result.Success(universitiesList)

        } catch (exception: Exception) {

            Result.Error(exception.message!!)

        }

    fun getCollegeForAUniversity(universityDocRef: DocumentReference) =
        try {
            val collegesTask =
                Tasks.await(remoteDataSource.getCollegeForAUniversity(universityDocRef))

            val collegesList = collegesTask.map {
                val college = it.toObject<College>()
                college.docRef = it.reference
                college
            }

            Result.Success(collegesList)

        } catch (e: Exception) {
            Result.Error(e.message!!)
        }

    private fun getSubjectName(subjectId: String): String {

        val subject = remoteDataSource.getSubjectWithId(subjectId)

        return if (NadrisApplication.instance?.lang == "ar")
            subject?.name_ar!!
        else
            subject?.name_en!!
    }


    //courses functions
    fun getCurrentUserSubscribedCourses(coursesIds: List<String>) =
        flow {
            try {
                val task = Tasks.await(remoteDataSource.getCoursesWithIds(coursesIds))
                val coursesList = task.map {
                    val course = it.toObject<Course>()
                    course.courseId = it.id
                    course.subjectName = getSubjectName(course.subjectId)
                    course.teacherName =
                        getUserDataObj(course.ownerTeacherID).let { it?.firstName + "" + it?.lastName }
                    course
                }
                emit(Result.Success(coursesList))
            } catch (exception: Exception) {
                emit(Result.Error(exception.message!!))
            }
        }

    fun getCoursesWithSubjectId(subjectId: String) =
        flow {
            try {
                val task = Tasks.await(remoteDataSource.getCoursesWithGradeId(subjectId))
                val coursesList = task.map {
                    val gradesList = getGrades().data
                    val course = it.toObject<Course>()
                    course.courseId = it.id
                    course.subjectName = getSubjectName(course.subjectId)
                    course.teacherName =
                        getUserDataObj(course.ownerTeacherID).let { it?.firstName + "" + it?.lastName }
                    val grade = gradesList?.find { it.gradeReference == course.gradeRef }
                    course.gradeName =
                        if (NadrisApplication.instance?.lang == "ar") grade?.name_ar!! else grade?.name_ar!!
                    course
                }
                emit(Result.Success(coursesList))
            } catch (exception: Exception) {
                emit(Result.Error(exception.message!!))
            }
        }

    suspend fun getCourseUnit(courseId: String) =
        flow {
            try {
                val unitsList = remoteDataSource.getCourseUnits(courseId)
                emit(Result.Success(unitsList))
            } catch (exception: Exception) {
                emit(Result.Error(exception.message!!))
            }
        }


    //inquiry functions
    fun addNewInquiryWithImage(inquiry: Inquiry, imageFile: File) =
        try {
            Tasks.await(remoteDataSource.addNewInquiryWithImage(inquiry, imageFile))
            Result.Success(inquiry)
        } catch (exception: Exception) {
            Result.Error(exception.message!!)
        }

    fun addNewInquiryWithoutImage(inquiry: Inquiry) =
        try {
            Tasks.await(remoteDataSource.addNewInquiryWithoutImage(inquiry))
            Result.Success(inquiry)
        } catch (exception: Exception) {
            Result.Error(exception.message!!)
        }

    fun getAllInquiries() =
        flow {
            val localInquiries = localDataSource.getAllPosts()
            emit(Result.Loading(localInquiries))
            try {
                val querySnapshot = Tasks.await(remoteDataSource.getAllInquiries())
                val inquiriesList = querySnapshot.map {
                    getPostDataFromDocumentSnapShot(it)
                }
                emit(Result.Success(inquiriesList))
            } catch (exception: Exception) {
                emit(Result.Error(exception.message!!))
            }
        }

    private fun getPostDataFromDocumentSnapShot(docSnapshot: DocumentSnapshot?): DatabasePost {
        try {
            val inquiry = docSnapshot?.toObject<Inquiry>()
            inquiry?.id = docSnapshot?.id
            inquiry?.subjectName = getSubjectName(inquiry?.subject_id!!)
            val userData = getUserDataObj(inquiry.userID!!)

            if (!inquiry.image_path.isNullOrEmpty())
                inquiry.image_File_Path =
                    getInquiryImage(inquiry.image_path!!, inquiry.id!!).absolutePath

            if (!userData?.image_path.isNullOrEmpty())
                inquiry.userProfileImagePah =
                    getProfileImage(userData?.image_path!!, userData.id).absolutePath

            return NetworkObjectMapper.postAsDatabaseModel(inquiry,
                userData?.firstName + " " + userData?.lastName)
        } catch (exception: Exception) {
            throw exception
        }
    }

    fun getInquiryAsResultWithId(inquiryId: String) =
        flow {
            emit(Result.Loading())
            try {
                val querySnapshot = Tasks.await(remoteDataSource.getInquiryWithId(inquiryId))
                val inquiry = getPostDataFromDocumentSnapShot(querySnapshot)
                emit(Result.Success(inquiry))
            } catch (exception: Exception) {
                emit(Result.Error(exception.message!!))
            }
        }

    private fun getInquiryImage(imagePath: String, inquiryId: String): File {
        try {
            val localeFile = File.createTempFile(inquiryId, "jpg")
            Tasks.await(remoteDataSource.getInquiryImage(localeFile, imagePath))
            return localeFile
        } catch (exception: Exception) {
            throw exception
        }
    }

    private fun getInquiryAsDocSnapWithId(inquiryId: String) =
        remoteDataSource.getInquiryWithId(inquiryId)

    fun vote(userID: String?, inquiryId: String): Result<DatabasePost?> {
        var votedIdsList: MutableList<String>? = null

        try {
            val task = Tasks.await(getVotedUserIdsForInquiry(inquiryId))
            votedIdsList = task.toObject<Inquiry>()?.voted_user_ids?.toMutableList()
        } catch (exception: Exception) {
            return Result.Error(exception.message!!)
        }

        if (votedIdsList?.contains(userID)!!) {
            votedIdsList.remove(userID)
        } else {
            votedIdsList.add(userID!!)
        }

        try {
            Tasks.await(setVotedUserIdsForInquiry(inquiryId, votedIdsList))
        } catch (exception: Exception) {
            return Result.Error(exception.message!!)
        }

        return try {
            val docSnapShot = Tasks.await(getInquiryAsDocSnapWithId(inquiryId))
            val databaseInquiry = getPostDataFromDocumentSnapShot(docSnapShot)
            Result.Success(databaseInquiry)
        } catch (exception: Exception) {
            Result.Error(exception.message!!)
        }

    }

    private fun setVotedUserIdsForInquiry(inquiryId: String, votedIdsList: MutableList<String>?) =
        remoteDataSource.setVotedUserIdsForInquiry(inquiryId, votedIdsList)

    private fun getVotedUserIdsForInquiry(inquiryId: String) =
        remoteDataSource.getInquiryWithId(inquiryId)


    //replies functions
    fun getReplies(InquiryId: String) =
        flow {
            emit(Result.Loading())
            try {
                val querySnapShot = Tasks.await(remoteDataSource.getCommentsForAnInquiry(InquiryId))
                val repliesList = querySnapShot.map {
                    val reply = it.toObject<Reply>()
                    reply.replyId = it.id

                    val userData = getUserDataObj(reply.userId!!)
                    reply.userFullName = userData?.firstName + " " + userData?.lastName
                    NetworkObjectMapper.replyAsUIModel(reply)
                }
                emit(Result.Success(repliesList))
            } catch (exception: Exception) {
                emit(Result.Error(exception.message!!))
            }
        }

    fun addNewReply(reply: Reply, postId: String) =
        flow {
            emit(Result.Loading())
            try {
                Tasks.await(remoteDataSource.addNewReply(reply, postId))
                Tasks.await(remoteDataSource.incrementReplies(postId))
                emit(Result.Success(true))
            } catch (exception: Exception) {
                emit(Result.Error(exception.message!!))
            }
        }

    suspend fun bookMarkAPost(post: DatabasePost) =
        flow {
            try {
                localDataSource.insertPost(post)
                emit(Result.Success(true))
            } catch (exception: Exception) {
                emit(Result.Error(exception.message!!))
            }
        }

    fun subScribeToACourse(selectedCourseID: String, userID: String) =
        flow {
            try {
                remoteDataSource.subscribeToCourse(selectedCourseID, userID)
                emit(Result.Success(true))
            } catch (exception: Exception) {
                emit(Result.Error(exception.message!!))
            }
        }

    fun createNewCourse(course: Course) =
        flow{
            emit(Result.Loading())
            try {
                val courseId = remoteDataSource.generateCourseId()
                course.courseId = courseId
                val task = remoteDataSource.createCourse(course).continueWithTask {
                    remoteDataSource.addCourseIdToTeacherData(courseId,course.ownerTeacherID)
                }
                 Tasks.await(task)
                emit(Result.Success(true))
            } catch (exception: Exception) {
                emit(Result.Error(exception.message!!))
            }
        }

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