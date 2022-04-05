package com.example.android.nadris.ui.studentActivity.followProfile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.TOKEN_PREFIX
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.network.NetworkModelsMapper
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowProfileViewModel @Inject constructor(val repository:Repository) : ViewModel() {
    var imgProfile: MutableLiveData<Int> = MutableLiveData<Int>();
    //change retrive data from database to retrive data from api
    var nameProfile: MutableLiveData<String> = MutableLiveData<String>(NadrisApplication.userData?.getFullName());
    var profileType: MutableLiveData<String> = MutableLiveData(NadrisApplication.userData?.Type);

    var numPosts: MutableLiveData<Long> = MutableLiveData(0);
    var numFollowers: MutableLiveData<Long> = MutableLiveData(0);
    var numFolling: MutableLiveData<Long> = MutableLiveData(0);

    var postsProfileList = MutableLiveData(mutableListOf<DatabasePost>())



    lateinit var profileEmail : String

    fun getLastActivity() {
        viewModelScope.launch {
            val token = NadrisApplication.userData?.Token //replace by id
            var result = repository.getLastActivity(TOKEN_PREFIX + token)// replace by id or change mehtod==> repository.getLastActivity
            result.collect {
                it.handleRepoResponse(
                    onLoading = {

                    }, onError = {
                        Log.i("post",it.error.toString())

                    }, onSuccess = {
                        postsProfileList.value = it.data?.map {
                            NetworkModelsMapper.postAsDatabaseModel(it)
                        }?.toMutableList()

                    }
                )
            }
        }
    }

}