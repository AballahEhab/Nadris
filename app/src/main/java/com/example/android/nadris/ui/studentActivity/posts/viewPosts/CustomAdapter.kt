package com.example.android.nadris.ui.studentActivity.posts.viewPosts

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.R
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.databinding.ItemPostCardCellBinding
import com.example.android.nadris.util.isVisible
import com.google.android.material.snackbar.Snackbar
import java.io.File

class CustomAdapter(val postPageViewModel: PostPageViewModel) :
    RecyclerView.Adapter<CustomAdapter.PostViewHolder>(), Filterable {

    var fullPostsList = listOf<DatabasePost>()

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

        val postData = differ.currentList[position]
        var img: Bitmap?

        if (!postData.imageFilePath.isNullOrEmpty()) {
            val file = File(postData.imageFilePath)
            if (file.exists()) {
                img = BitmapFactory.decodeFile(file.absolutePath)
                holder.binding.imgPost.setImageBitmap(img!!)
                holder.binding.imgPost.visibility = View.VISIBLE
            } else {
                holder.binding.imgPost.visibility = View.GONE
            }
        } else {
            holder.binding.imgPost.visibility = View.GONE
        }

        holder.setDataBindingObj(postData)

        holder.binding.imgReply.setOnClickListener {
            holder.itemView.findNavController()
                .navigate(PostPageFragmentDirections.actionPostsFragmentToAddCommentFragment(postData.postId))
        }

        holder.binding.imgVote.setOnClickListener {
            postData.toggleVote()
                postPageViewModel.voteToPost(postData.postId)
        }

        holder.binding.bookmark.setOnClickListener {
            postData.toggleBookMark()
            postPageViewModel.bookMark(postData)
            notifyItemChanged(position)
        }

        holder.binding.profileImage.setOnClickListener {
            postPageViewModel.navigateToPublicProfilePage(postData.userId)
        }

        try {
            if (!postData.userImageFilePath.isNullOrEmpty()) {
                val file = File(postData.userImageFilePath)
                if (file.exists()) {
                    img = BitmapFactory.decodeFile(file.absolutePath)
                    holder.binding.profileImage.setImageBitmap(img!!)
                }
            }
        } catch (e: Throwable) {
            Snackbar.make(holder.binding.root, e.message.toString(), Snackbar.LENGTH_LONG)
                .show()
        }

        NadrisApplication.currentDatabaseUser?.userID?.let {

            if (it.equals(postData.userId)!!) {

                holder.binding.discussionMoreOption.isVisible(true)
                holder.binding.discussionMoreOption.setOnClickListener {
                    val menu = android.widget.PopupMenu(it.context, it)

                    menu.inflate(R.menu.discussion_more_options_menu)
                    menu.show()

                    menu.setOnMenuItemClickListener { menu_item ->

                        when (menu_item.itemId) {
                            R.id.delete_discussion_menu_item -> postPageViewModel.deletePost(
                                postData.postId)
                            R.id.edit_discussion_menu_item -> postPageViewModel.navigateToEditPost(
                                postData)
                        }
                        false
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class PostViewHolder(var binding: ItemPostCardCellBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setDataBindingObj(post: DatabasePost) {
            binding.postData = post
        }
    }

    override fun getFilter(): Filter = searchFilter

    private val searchFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            fullPostsList = postPageViewModel.postsList.value!!

            val filteredList = mutableListOf<DatabasePost>()

            if (constraint.isNullOrEmpty()) {

                filteredList.addAll(fullPostsList)

            } else {
                val filterKeyWord = constraint.toString().toLowerCase().trim()
                for (post in fullPostsList) {
                    if (post.content.toLowerCase().contains(filterKeyWord))
                        filteredList.add(post)
                }

            }

            val filterResult = FilterResults()
            filterResult.values = filteredList
            return filterResult
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            differ.submitList(results?.values as MutableList<DatabasePost>?)
        }

    }


}