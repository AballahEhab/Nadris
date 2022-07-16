package com.example.android.nadris.ui.studentActivity.followProfile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.database.models.DatabaseUser
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FollowProfileViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    val profileDatabase: MutableLiveData<DatabaseUser> = MutableLiveData()

    var postsProfileList = MutableLiveData<List<DatabasePost>>()


    val isLoading = MutableLiveData(true)

    lateinit var publicProfileId: String

    fun getLastActivity() {
//        viewModelScope.launch {
//            val token = NadrisApplication.userData?.Token
//            var result = repository.getPostsByUserId(publicProfileId,TOKEN_PREFIX + token)
//            result.collect{
//                it.handleRepoResponse(
//                    onLoading = {
//
//                    }, onError = {
//                        Log.i("post",it.error.toString())
//
//                    }, onSuccess = {
//                        postsProfileList.value = it.data?.map {
//                            NetworkModelsMapper.postAsDatabaseModel(it)
//                        }
////                        it.data!!.forEach {  post ->
//////                            postsProfileList.value!!.add(NetworkModelsMapper.postAsDatabaseModel(post) )
////                            Log.i("post",postsProfileList.toString())
////                        }
//                    }
//                )
//            }
//        }
    }
    fun getPublicProfileData() {
        //todo: get data assotiated with email from api and set it to
//        val resultFlow = repository.getPublicProfileInfo(
//            (TOKEN_PREFIX + NadrisApplication.userData?.Token),
//            publicProfileId)
//        viewModelScope.launch {
//            resultFlow.collect { result ->
//                result.handleRepoResponse(
//                    onLoading = {
//
//
//                    }, onError = {
//
//                        isLoading.value = false
//
//                    }, onSuccess = {
//                        profileData.value  = result.data
//                        isLoading.value = false
//                    }
//                )
//            }
//        }
    }

    fun followPublicProfile(){
//        val resultFlow =
//            repository.flowProfile((TOKEN_PREFIX + NadrisApplication.userData?.Token),
//                publicProfileId)
//        viewModelScope.launch {
//            resultFlow.collect { result ->
//                result.handleRepoResponse(
//                    onLoading = {
//
//                    }, onError = {
//
//                    }, onSuccess = {
//                        getPublicProfileData()
//                    }
//                )
//
//            }
//        }
    }

}