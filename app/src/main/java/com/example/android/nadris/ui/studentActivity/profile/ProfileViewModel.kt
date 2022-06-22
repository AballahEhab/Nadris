package com.example.android.nadris.ui.studentActivity.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.TOKEN_PREFIX
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.network.NetworkModelsMapper.postAsDatabaseModel
import com.example.android.nadris.network.dtos.NetworkPost
import com.example.android.nadris.network.dtos.ProfileInfoDTO
import com.example.android.nadris.network.dtos.UploadPhotoDTO
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    var imgProfile: MutableLiveData<String?> = MutableLiveData<String?>(null);
    var nameProfile: MutableLiveData<String> = MutableLiveData<String>(NadrisApplication.userData?.getFullName());
    var profileType: MutableLiveData<String> = MutableLiveData(NadrisApplication.userData?.Type);
    var numFollowers: MutableLiveData<Long> = MutableLiveData(0);
    var numFolling: MutableLiveData<Long> = MutableLiveData(0);



    var postsProfileList = MutableLiveData(mutableListOf<DatabasePost>())
    var profileData = MutableLiveData<ProfileInfoDTO>()

    val navigateToLoginPage = MutableLiveData(false)
    val profileImageClicked = MutableLiveData(false)

    fun getProfileInfo_from_api() {
        val token = NadrisApplication.userData?.Token
        viewModelScope.launch {
            var result = repository.getProfileInfo(TOKEN_PREFIX + token)

            result.collect {
                it.handleRepoResponse(
                    onLoading = {

                    }, onError = {
//                        nameProfile.value = NadrisApplication.userData?.getFullName()
//                        profileType.value = NadrisApplication.userData?.Type
                        //  numFollowers.value=NadrisApplication.userData?.
                        // numFolling.value = NadrisApplication.userData?.
                    }, onSuccess = {
                        imgProfile.value = it.data?.profilePicStrB64
                        numFollowers.value = it.data?.numOfFollowers
                        numFolling.value = it.data?.numOfFollowing

//                        profileData.value = it.data!!

//                        nameProfile.value = it.data!!.firstName + " " + it.data.lastName
//                        //numPosts.value = (it.data)?.numOfPosts
//                        numFollowers.value = it.data?.numOfFollowers
//                        numFolling.value = it.data?.numOfFollowing
//                        profileType.value = it.data?.type
                    }
                )
            }
        }
    }

    fun getLastActivity() {
        viewModelScope.launch {
            val token = NadrisApplication.userData?.Token
            var result = repository.getLastActivity(TOKEN_PREFIX + token)
            result.collect{
                it.handleRepoResponse(
                    onLoading = {

                    }, onError = {
                        Log.i("post",it.error.toString())

                    }, onSuccess = {
                        postsProfileList.value = it.data?.map { postAsDatabaseModel(it) }?.toMutableList()
//                        it.data!!.forEach {  post ->
////                            postsProfileList.value!!.add(NetworkModelsMapper.postAsDatabaseModel(post) )
//                            Log.i("post",postsProfileList.toString())
//                        }
                    }
                )
            }
        }
    }

    fun logOut() {
        viewModelScope.launch{
        NadrisApplication.userData?.let { repository.logOut(it) }
    }
        navigateToLoginPage.value = true
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
        viewModelScope.launch {
            repository.updateProfilePic(TOKEN_PREFIX+NadrisApplication.userData?.Token!!, UploadPhotoDTO(imgProfile.value!!)).collect()
        }
    }


}