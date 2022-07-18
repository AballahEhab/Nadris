package com.example.android.nadris.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class DatabasePost(
    @PrimaryKey val postId: String,
    var imageFilePath :String? ,
    var userImageFilePath :String? ,
    var subject: String,
    var content: String,
    var votesNum:Int,
    var commentsNum:Int,
    var time:String,
    var userId:String,
    var name:String,
    var isVoted:Boolean,
    var isBookMarked:Boolean = false
) {

    fun toggleVote(){
        isVoted = !isVoted
    }

    fun toggleBookMark(){
        isBookMarked = !isBookMarked
    }

    fun updatePost(updatedPost:DatabasePost){
        this.imageFilePath =updatedPost.imageFilePath
        this.subject =updatedPost.subject
        this.content =updatedPost.content
        this.votesNum =updatedPost.votesNum
        this.commentsNum =updatedPost.commentsNum
        this.time =updatedPost.time
        this.userId =updatedPost.userId
        this.name =updatedPost.name
        this.isVoted =updatedPost.isVoted

    }
}
