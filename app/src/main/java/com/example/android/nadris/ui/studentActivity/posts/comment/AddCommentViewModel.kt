package com.example.android.nadris.ui.studentActivity.posts.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.domain.CommentData

class AddCommentViewModel : ViewModel() {
    private var list=MutableLiveData<List<CommentData>>()

    fun getData():MutableLiveData<List<CommentData>>{
        val comment = mutableListOf<CommentData>()
        comment.add(CommentData("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",1))
        comment.add(CommentData("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",2))
        comment.add(CommentData("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",3))
        comment.add(CommentData("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",4))
        comment.add(CommentData("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",5))
        comment.add(CommentData("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",6))
        comment.add(CommentData("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",7))
        comment.add(CommentData("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",8))
        comment.add(CommentData("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",9))
        comment.add(CommentData("email@nadris.com","محمدمصطفي","المدرس شاطر جدا ",10))

        list.value=comment
        return list
    }

}