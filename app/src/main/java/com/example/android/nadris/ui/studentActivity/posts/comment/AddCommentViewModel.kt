package com.example.android.nadris.ui.studentActivity.posts.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.domain.CommentData

class AddCommentViewModel : ViewModel() {
    private var list=MutableLiveData<List<CommentData>>()

    fun getData():MutableLiveData<List<CommentModel>>{
        val comment = mutableListOf<CommentModel>()
        comment.add(CommentModel("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",1))
        comment.add(CommentModel("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",2))
        comment.add(CommentModel("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",3))
        comment.add(CommentModel("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",4))
        comment.add(CommentModel("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",5))
        comment.add(CommentModel("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",6))
        comment.add(CommentModel("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",7))
        comment.add(CommentModel("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",8))
        comment.add(CommentModel("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",9))
        comment.add(CommentModel("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",10))

        list.value=comment
        return list
    }

    //val mm = MutableLiveData<String>()
    var post_id:Int=-1
    var comment = MutableLiveData<String>()

    var visabilty =MutableLiveData<Boolean> (false)

    fun onClikVisable(){
    visabilty.value = comment.value?.isNotBlank()
    }


    fun setComment(){
        viewModelScope.launch{
            repo.publishComment(CommentData(NadrisApplication.userData?.Email!!,
                NadrisApplication.userData?.getFullName()!!, comment.value!!,
                post_id), NadrisApplication.userData?.Token!!)
        }
    }

//    val name:String,
//    val content: String,
//    val postId:Int

}