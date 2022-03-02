package com.example.android.nadris.database.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize // TODO: replace parcelize with id
@Entity
data class DatabasePost(
    @PrimaryKey val postId: Long,
    var hasImage :Boolean,
    val subject: String,
    val content: String,
    var votesNum:Long,
    val commentsNum:Long,
    val time:String,
    val email:String,
    val name:String,
    var isVoted:Boolean
) : Parcelable {
     var isBookMarked = false

    fun toggleVote(){
        isVoted = !isVoted
    }

    fun getVoteBookMark() = isBookMarked


    fun toggleBookMark(){
        isBookMarked = !isBookMarked
    }

    fun getVoteStatus() = isVoted
}
