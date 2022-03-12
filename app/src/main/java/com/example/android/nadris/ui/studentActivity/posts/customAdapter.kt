package com.example.android.nadris.ui.studentActivity.posts

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.R
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.databinding.ItemPostCardCellBinding
import java.io.File

class customAdapter(val viewModel: PostPageViewModel) : RecyclerView.Adapter<customAdapter.PostViewHolder>() {


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
            val file = File(NadrisApplication.instance?.applicationContext?.cacheDir, data.postId.toString())
            if (file.exists()) {
                img = BitmapFactory.decodeFile(file.absolutePath)
                holder.binding.imgPost.setImageBitmap(img!!)
            }
        }
        holder.binding.postData=data
        holder.binding.profileImage.setImageResource(R.drawable.ic_google)
        holder.binding.accountName.text = data.name
        holder.binding.subjectName.text = data.subject
        holder.binding.postContent.text = data.content
        holder.binding.imgReply.setOnClickListener {
            holder.itemView.findNavController()
                .navigate(PostPageFragmentDirections.actionNavigationPostsToAddCommentFragment(data))
        }
        holder.binding.imgVote.setOnClickListener {
            holder.toggleVoteIconStatus(data.getVoteStatus())
            if (data.getVoteStatus()) data.votesNum-- else data.votesNum++
            data.toggleVote()
//            viewModel.vote(position, data.getVoteStatus())
//                .let {
//                    differ.currentList[position] = it!!
//                }
            notifyItemChanged(position)
        }
        holder.binding.bookmark.setOnClickListener {
            holder.toggleBookMerkleIconStatus(data.getVoteBookMark())
            data.toggleBookMark()
            viewModel.BookMark(data.postId, data.getVoteBookMark())
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class PostViewHolder(var binding: ItemPostCardCellBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setDataBindingObj(post: DatabasePost) {
            binding.postData = post
        }

        fun toggleVoteIconStatus(state: Boolean) {
            if (state) {
                // TODO: add voted style
            } else {
                // TODO: add unvoted style
            }
        }

        fun toggleBookMerkleIconStatus(state: Boolean) {
            if (state) {
                // TODO: add voted style
            } else {
                // TODO: add unvoted style
            }
        }
    }

}
