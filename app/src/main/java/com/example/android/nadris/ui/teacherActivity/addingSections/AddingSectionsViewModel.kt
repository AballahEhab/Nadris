package com.example.android.nadris.ui.teacherActivity.addingSections

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.network.dtos.LoginAccountModel
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.util.checkEmpty
import com.example.android.nadris.util.checkPassword
import com.example.android.nadris.util.getErrorMessage
import com.example.android.nadris.util.matchEmailPattern
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddingSectionsViewModel @Inject constructor() : ViewModel() {


    var SectionAsHtml: MutableLiveData<String> = MutableLiveData("")

}