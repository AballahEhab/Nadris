package com.example.android.nadris.ui.studentActivity.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.databinding.ItemPostCardCellBinding

class customAdapter (val viewModel:PostPageViewModel)
    :RecyclerView.Adapter<customAdapter.PostViewHolder>() {
    private lateinit var postList:MutableList<DatabasePost>
    fun setPostsList(postList:List<DatabasePost>){
        this.postList = postList.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostCardCellBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding)

    }
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        
        val data: DatabasePost =postList[position]

        val context = holder.itemView.context
//        holder.imageStudent.setImageResource(data.imageStudent)
//        holder.studentName.text=data.name
//        holder.subjectName.text= data.subjectName
//        holder.postText.text=data.content
//        holder.voteText.textWithFormat(R.string.vote, data.votesNum)
//        holder.commentText.text = String.format(context.getString(R.string.reply), data.commentsNum)


        holder.setDataBindingObj(data)
        holder.commentIcon.setOnClickListener {
            it.findNavController().navigate(PostPageFragmentDirections.actionNavigationPostsToAddCommentFragment(data))
        }
        holder.voteUpIcon.setOnClickListener {
            holder.toggleVoteIconStatus(data.getVoteStatus())
            if(data.getVoteStatus()) data.votesNum-- else data.votesNum++
            data.toggleVote()
            viewModel.vote(position,data.getVoteStatus())
            .let {
                postList[position] = it!!
            }
            notifyItemChanged(position)
        }
        holder.bookMarkIcon.setOnClickListener {
            holder.toggleBookMerkleIconStatus(data.getVoteBookMark())
            data.toggleBookMark()
             viewModel.BookMark(data.postId,data.getVoteBookMark())
            notifyItemChanged(position)
        }

    }

    override fun getItemCount(): Int {
        return postList.size
    }

    class PostViewHolder(val binding:ItemPostCardCellBinding):RecyclerView.ViewHolder(binding.root){


//        val imageStudent=binding.profileImage
//        val studentName=binding.textViewAccountName
//        val subjectName=binding.textSubjectName
//        val postText=binding.textViewPost
//        val commentText=binding.textreply
//        val voteText = binding.textvote
//        val voteClicekd = false

        val voteUpIcon=binding.imgVote
        val commentIcon=binding.imgReply
        val bookMarkIcon = binding.bookmark

        fun setDataBindingObj(post:DatabasePost){
            binding.postData = post
        }


        fun toggleVoteIconStatus(state:Boolean) {
            if (state){
                // TODO: add voted style
            }else{
                // TODO: add unvoted style
            }
        }
        fun toggleBookMerkleIconStatus(state:Boolean) {
            if (state){
                // TODO: add voted style
            }else{
                // TODO: add unvoted style
            }
        }



    }


}
