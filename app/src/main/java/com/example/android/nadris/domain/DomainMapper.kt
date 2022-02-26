package com.example.android.nadris.domain

import com.example.android.nadris.network.models.PublishCommentModel

object DomainMapper {

    fun commentDataAsNetworkComment(comment:CommentData) = PublishCommentModel(
        comment.email ,
        comment.content ,
        comment.postId ,
    )
}