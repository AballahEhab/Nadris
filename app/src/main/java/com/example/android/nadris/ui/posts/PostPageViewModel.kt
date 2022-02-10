package com.example.android.nadris.ui.posts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.database.PostData
import com.example.android.nadris.network.PostModel
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostPageViewModel @Inject constructor(): ViewModel() {

    private var _navigate_to_add_post: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val navigate_to_add_post: MutableLiveData<Boolean> get() = _navigate_to_add_post

     var postsList = mutableListOf<PostData>()

    fun navigate_to_add_post(){
        _navigate_to_add_post.value = true
    }

    fun navigate_to_add_post_done(){
        _navigate_to_add_post.value = false
    }

//    fun getPosts(token:String) =
//        viewModelScope.launch{
//            val postsFlow = repository.getPosts(token)
//            postsFlow.collect {
////                postsList.addAll(it.data as Collection<PostData>)
//                Log.v("poss", it.data.toString())
//            }
//        }

}