package com.example.android.nadris.ui.studentActivity.posts.viewPosts.replies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.data.models.CommentModel
import com.example.android.nadris.databinding.ItemAddComentBinding

class CustomAdapterComment:RecyclerView.Adapter<CustomAdapterComment.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<CommentModel>() {
        override fun areItemsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemAddComentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var data = differ.currentList[position]

        holder.setBindingObj(data)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class ViewHolder(var binding: ItemAddComentBinding) : RecyclerView.ViewHolder(binding.root){

        fun setBindingObj(comment:CommentModel){
            binding.commentData = comment
        }
    }

}

