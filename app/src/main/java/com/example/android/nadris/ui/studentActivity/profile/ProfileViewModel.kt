package com.example.android.nadris.ui.studentActivity.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.TOKEN_PREFIX
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    var imgProfile: MutableLiveData<Int> = MutableLiveData<Int>();
    var nameProfile: MutableLiveData<String> = MutableLiveData<String>("Name Profile");

    var profileType: MutableLiveData<String> = MutableLiveData("Student");

    var numPosts: MutableLiveData<Long>  = MutableLiveData();
    var numFollowers:MutableLiveData<Long>  = MutableLiveData();
    var numFolling: MutableLiveData<Long>  = MutableLiveData();

    var postsProfileList: MutableLiveData<List<DatabasePost>> =
        MutableLiveData<List<DatabasePost>>()


    fun getProfileInfo_from_api() {
        val token = NadrisApplication.userData?.Token
        viewModelScope.launch {
            var result = repository.getProfileInfo(TOKEN_PREFIX + token)


            result.collect {
                it.handleRepoResponse(
                    onLoading = {
                        //Log.i("profile_api", "onLoading")
                    }, onError = {
                          nameProfile.value = NadrisApplication.userData?.getFullName()
                          profileType.value = NadrisApplication.userData?.Type
                        //  numFollowers.value=NadrisApplication.userData?.
                         // numFolling.value = NadrisApplication.userData?.

                    }, onSuccess = {
                        //Log.i("profile_api", it.data?.firstName.toString())

                        nameProfile.value = it.data!!.firstName + " " + it.data.lastName
                        //numPosts.value = (it.data)?.numOfPosts
                        numFollowers.value = it.data?.numOfFollowers
                        numFolling.value = it.data?.numOfFollowing
                        profileType.value = it.data?.type


                    }
                )
            }


        }
    }


}