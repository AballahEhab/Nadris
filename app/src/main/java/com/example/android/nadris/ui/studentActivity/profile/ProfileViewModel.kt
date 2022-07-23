package com.example.android.nadris.ui.studentActivity.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.R
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    var imgProfile : MutableLiveData<String?> = MutableLiveData<String?>(null);
    var nameProfile: MutableLiveData<String> = MutableLiveData<String>(NadrisApplication.currentDatabaseUser?.getFullName());
    var profileType: MutableLiveData<String> = MutableLiveData(if(NadrisApplication.currentDatabaseUser?.IsATeacher == false) "Student" else "Teacher");


    var numFollowers: MutableLiveData<Long> = MutableLiveData(0);


    private var _numProgressBarValue: MutableLiveData<Int> = MutableLiveData<Int>(50);
    val numProgressBarValue get() = _numProgressBarValue

    private var _numProgressBarMaxValue: MutableLiveData<Int> = MutableLiveData(150);
    val numProgressBarMaxValue get() = _numProgressBarMaxValue

    private var _numLevelStudent: MutableLiveData<Int> = MutableLiveData(4);
    val numLevelStudent get()=_numLevelStudent

    var numFolling: MutableLiveData<Long> = MutableLiveData(0);

    private var _CheckStudentOrTeacher :MutableLiveData<Boolean> = MutableLiveData(true)
    val CheckStudentOrTeacher get() = _CheckStudentOrTeacher

    fun getnumProgressBar_api(){
        //todo please get value of pint in solve quize and set value to ProgressBar in variable = _numProgressBarValue
    }
    fun getnumProgressBarMaxValue_api(){
        //todo please get value of max value ProgressBar and set value to variable = _ProgressBarMaxValue

    }
    fun getnumLevelStudent_api(){
        //todo please get value of level student and set value to  variable = _numLevelStudent

    }

    fun getProfileTybe(){
        if (profileType.value=="Student"){
            //Student
            _CheckStudentOrTeacher.value=true
        }else{
            //Teacher
            _CheckStudentOrTeacher.value=false
        }
    }



    var postsProfileList = MutableLiveData(mutableListOf<DatabasePost>())
//    var profileData = MutableLiveData<ProfileInfoDTO>()

    val navigateToLoginPage = MutableLiveData(false)
    val profileImageClicked = MutableLiveData(false)

    fun getProfileInfo_from_api() {
//        val token = NadrisApplication.currentUserLocalData?.Token
//        viewModelScope.launch {
//            var result = repository.getProfileInfo(TOKEN_PREFIX + token)
//
//            result.collect {
//                it.handleRepoResponse(
//                    onLoading = {
//
//                    }, onError = {
////                        nameProfile.value = NadrisApplication.userData?.getFullName()
////                        profileType.value = NadrisApplication.userData?.Type
//                        //  numFollowers.value=NadrisApplication.userData?.
//                        // numFolling.value = NadrisApplication.userData?.
//                    }, onSuccess = {
//                        imgProfile.value = it.data?.profilePicStrB64
//                        numFollowers.value = it.data?.numOfFollowers
//                        numFolling.value = it.data?.numOfFollowing
//
////                        profileData.value = it.data!!
//
////                        nameProfile.value = it.data!!.firstName + " " + it.data.lastName
////                        //numPosts.value = (it.data)?.numOfPosts
////                        numFollowers.value = it.data?.numOfFollowers
////                        numFolling.value = it.data?.numOfFollowing
////                        profileType.value = it.data?.type
//                    }
//                )
//            }
//        }
    }

    fun getLastActivity() {
//        viewModelScope.launch {
//            val token = NadrisApplication.userData?.Token
//            var result = repository.getLastActivity(TOKEN_PREFIX + token)
//            result.collect{
//                it.handleRepoResponse(
//                    onLoading = {
//
//                    }, onError = {
//                        Log.i("post",it.error.toString())
//
//                    }, onSuccess = {
//                        postsProfileList.value = it.data?.map { postAsDatabaseModel(it) }?.toMutableList()
////                        it.data!!.forEach {  post ->
//////                            postsProfileList.value!!.add(NetworkModelsMapper.postAsDatabaseModel(post) )
////                            Log.i("post",postsProfileList.toString())
////                        }
//                    }
//                )
//            }
//        }
    }

    fun logOut() {
        viewModelScope.launch{
        NadrisApplication.currentDatabaseUser?.let {
            val result = repository.logOut(it)
            if(result is Result.Success)
                navigateToLoginPage.postValue(true)
        }
            NadrisApplication.currentDatabaseUser = null
    }
    }

    fun onProfileImageClicked() {

            profileImageClicked.value = true
           }
    fun profileImageClickDone() {
        profileImageClicked.value = false
    }

    fun removePhoto() {
        TODO("Not yet implemented")
    }

    fun uploadPhoto() {
//        viewModelScope.launch {
//            repository.updateProfilePic(TOKEN_PREFIX+NadrisApplication.userData?.Token!!, UploadPhotoDTO(imgProfile.value!!)).collect()
//        }
    }


}