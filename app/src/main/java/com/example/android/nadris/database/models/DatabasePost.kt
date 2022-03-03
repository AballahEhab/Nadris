package com.example.android.nadris.database.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity
data class DatabasePost(
    @PrimaryKey val postId: Long,
    var hasImage :Boolean,
    var subject: String,
    var content: String,
    var votesNum:Long,
    var commentsNum:Long,
    var time:String,
    var email:String,
    var name:String,
    var isVoted:Boolean
) {
     var isBookMarked = false

    fun toggleVote(){
        isVoted = !isVoted
    }

    fun getBookMarkStatus() = isBookMarked


    fun toggleBookMark(){
        isBookMarked = !isBookMarked
    }

    fun getVoteStatus() = isVoted

    fun updatePost(updatedPost:DatabasePost){
        this.hasImage =updatedPost.hasImage
        this.subject =updatedPost.subject
        this.content =updatedPost.content
        this.votesNum =updatedPost.votesNum
        this.commentsNum =updatedPost.commentsNum
        this.time =updatedPost.time
        this.email =updatedPost.email
        this.name =updatedPost.name
        this.isVoted =updatedPost.isVoted

    }
}
