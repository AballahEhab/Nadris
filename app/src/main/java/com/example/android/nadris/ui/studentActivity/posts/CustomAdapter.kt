package com.example.android.nadris.ui.studentActivity.posts

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.databinding.ItemPostCardCellBinding
import java.io.File

class CustomAdapter(val viewModel: PostPageViewModel) : RecyclerView.Adapter<CustomAdapter.PostViewHolder>() {


    private val differCallback = object : DiffUtil.ItemCallback<DatabasePost>() {
        override fun areItemsTheSame(oldItem: DatabasePost, newItem: DatabasePost): Boolean {
            return oldItem.postId == newItem.postId
        }

        override fun areContentsTheSame(oldItem: DatabasePost, newItem: DatabasePost): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemPostCardCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val data = differ.currentList[position]
        var img: Bitmap? = null
        if (data.hasImage) {
            val file = File(NadrisApplication.instance?.applicationContext?.cacheDir,
                data.postId.toString())
            if (file.exists()) {
                img = BitmapFactory.decodeFile(file.absolutePath)
                holder.binding.imgPost.setImageBitmap(img!!)
                holder.binding.imgPost.visibility = View.VISIBLE
            } else {
                holder.binding.imgPost.visibility = View.GONE
            }
        }
            holder.setDataBindingObj(data)
            holder.binding.imgReply.setOnClickListener {
                holder.itemView.findNavController()
                    .navigate(PostPageFragmentDirections.actionPostsFragmentToAddCommentFragment(data.postId))
            }
            holder.binding.imgVote.setOnClickListener {
                data.toggleVote()
                notifyItemChanged(position)

                viewModel.vote(data.postId)
                    .let {
                        data.updatePost(it!!)
                    }
            }
            holder.binding.bookmark.setOnClickListener {
                data.toggleBookMark()
                viewModel.BookMark(data)
                notifyItemChanged(position)
            }

            holder.binding.profileImage.setOnClickListener {
                viewModel.navigateToPublicProfilePage(data.userId)
            }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    class PostViewHolder(var binding: ItemPostCardCellBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setDataBindingObj(post: DatabasePost) {
            binding.postData = post
        }
    }


}