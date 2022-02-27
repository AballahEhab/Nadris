package com.example.android.nadris.ui.studentActivity.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.databinding.ItemPostCardCellBinding
import com.example.android.nadris.util.getSubjectName

class customAdapter (val postList:List<DatabasePost>)
    :RecyclerView.Adapter<customAdapter.Viewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = ItemPostCardCellBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        
        val data: DatabasePost =postList[position]

        holder.imageStudent.setImageResource(data.imageStudent)
        holder.studentName.text=data.name
        holder.subjectName.text= getSubjectName(data.subjectId)
        holder.postText.text=data.content
        holder.commentIcon.setOnClickListener {
            it.findNavController().navigate(PostPageFragmentDirections.actionNavigationPostsToAddCommentFragment(data.postId))
        }
        holder.commentText.text = holder.binding.root.context.getString(R.string.vote,data.votesNum);



        // TODO:activate bookmark

    }

    override fun getItemCount(): Int {
        return postList.size
    }

    class Viewholder(val binding:ItemPostCardCellBinding):RecyclerView.ViewHolder(binding.root){


        val imageStudent=binding.profileImage
        val studentName=binding.textViewAccountName
        val subjectName=binding.textSubjectName
        val postText=binding.textViewPost
        val commentIcon=binding.imgReply
        val commentText=binding.textreply
        val voteUpIcon=binding.imgVote
        val voteText = binding.textvote
        val bookMarkIcon = binding.bookmark



    }


}
