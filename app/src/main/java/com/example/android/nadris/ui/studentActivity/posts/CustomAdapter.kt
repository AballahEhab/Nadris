package com.example.android.nadris.ui.studentActivity.posts

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
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
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.databinding.ItemPostCardCellBinding
import java.io.File

class CustomAdapter(val postPageViewModel: PostPageViewModel) : RecyclerView.Adapter<CustomAdapter.PostViewHolder>(),Filterable {

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
        if (postData.hasImage) {
            val file = File(NadrisApplication.instance?.applicationContext?.cacheDir,
                postData.postId.toString())
            if (file.exists()) {
                img = BitmapFactory.decodeFile(file.absolutePath)
                holder.binding.imgPost.setImageBitmap(img!!)
                holder.binding.imgPost.visibility = View.VISIBLE
            } else {
                holder.binding.imgPost.visibility = View.GONE
            }
        }else {
            holder.binding.imgPost.visibility = View.GONE
        }

        holder.setDataBindingObj(postData)

        holder.binding.imgReply.setOnClickListener {
            holder.itemView.findNavController()
                .navigate(PostPageFragmentDirections.actionPostsFragmentToAddCommentFragment(postData.postId))
        }

        holder.binding.imgVote.setOnClickListener {
            postData.toggleVote()
            notifyItemChanged(position)

            postPageViewModel.vote(postData.postId)
                .let {
                    postData.updatePost(it!!)
                }
        }

        holder.binding.bookmark.setOnClickListener {
            postData.toggleBookMark()
            postPageViewModel.BookMark(postData)
            notifyItemChanged(position)
        }

        holder.binding.profileImage.setOnClickListener {
            postPageViewModel.navigateToPublicProfilePage(postData.userId)
        }

            try{
                val file = File(NadrisApplication.instance?.applicationContext?.cacheDir,
                    postData.postId.toString())
                val profileImageBitMap = BitmapFactory.decodeFile(file.absolutePath)
                holder.binding.profileImage.setImageBitmap(profileImageBitMap)
            }catch (e:Throwable) {
                Log.e("PostsCustomAdapter", e.message.toString())
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

    override fun getFilter(): Filter  = searchFilter

    val searchFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            fullPostsList = postPageViewModel.postsList.value!!

            val filteredList = mutableListOf<DatabasePost>()

            if(constraint.isNullOrEmpty()){

                filteredList.addAll(fullPostsList)

            }else{
                val filterKeyWord = constraint.toString().toLowerCase().trim()
                for(post in fullPostsList){
                    if(post.content.toLowerCase().contains(filterKeyWord))
                        filteredList.add(post)
                }

            }

            val filterResult = FilterResults()
            filterResult.values =  filteredList
            return filterResult
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            differ.submitList(results?.values as MutableList<DatabasePost>?)
        }

    }


}