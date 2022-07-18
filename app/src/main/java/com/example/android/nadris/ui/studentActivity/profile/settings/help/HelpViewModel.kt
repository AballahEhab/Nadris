package com.example.android.nadris.ui.studentActivity.profile.settings.help

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HelpViewModel  @Inject constructor(val repository: Repository) : ViewModel() {

    var discription : MutableLiveData<String> =
        MutableLiveData<String>("ندرس هوا برنامج تعليمي يساعدك علي التعليم عن بعد ")

    fun getHelpText(){

        viewModelScope.launch {
//            var result = repository.getHelpText()
//
//            result.collect {
//                it.handleRepoResponse(
//                    onLoading = {
//
//                    }, onError = {
//
//                    }, onSuccess = {
//                        discription.value = it.data!!.discription
//
//                    }
//                )
//            }
        }
    }
}