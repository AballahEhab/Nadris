package com.example.android.nadris.database

import androidx.room.PrimaryKey
import com.example.android.nadris.R
import com.example.android.nadris.domain.dataRvPost
import com.example.android.nadris.network.PostModel

object DatabaseModelsMapper {
    fun postAsDomainModel(post: PostData) =
        dataRvPost(
            R.drawable.ic_google ,
            "not stored in database" ,
            post.subject,
            post.body ,
        )
}