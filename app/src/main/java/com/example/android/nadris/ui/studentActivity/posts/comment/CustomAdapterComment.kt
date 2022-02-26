package com.example.android.nadris.ui.studentActivity.posts.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.domain.CommentData
import com.example.android.nadris.databinding.ItemAddComentBinding

class CustomAdapterComment:RecyclerView.Adapter<CustomAdapterComment.ViewHolder>() {

    private  val diferrCallback =object : DiffUtil.ItemCallback<CommentData>() {
        override fun areItemsTheSame(oldItem: CommentData, newItem: CommentData): Boolean {
            return oldItem.email==newItem.email
        }
        override fun areContentsTheSame(oldItem: CommentData, newItem: CommentData): Boolean {
            return oldItem==newItem
        }
    }
    val differ =AsyncListDiffer(this,diferrCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=ItemAddComentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data=differ.currentList[position]

        holder.binding.nameAcount.text=data.name
        holder.binding.contentComment.text=data.content

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class ViewHolder(var binding: ItemAddComentBinding):RecyclerView.ViewHolder(binding.root)

}