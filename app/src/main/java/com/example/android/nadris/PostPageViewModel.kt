package com.example.android.nadris

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class PostPageViewModel : ViewModel() {


    private var _navigate_to_add_post: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val navigate_to_add_post: MutableLiveData<Boolean> get() = _navigate_to_add_post


    fun navigate_to_add_post(){
        _navigate_to_add_post.value = true
    }

    fun navigate_to_add_post_done(){
        _navigate_to_add_post.value = false
    }
}