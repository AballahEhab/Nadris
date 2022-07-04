package com.example.android.nadris.ui.studentActivity.posts

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.databinding.ItemPostCardCellBinding
import com.example.android.nadris.network.firebase.dtos.Inquiry
import java.io.File

class CustomAdapter(val postPageViewModel: PostPageViewModel) : RecyclerView.Adapter<CustomAdapter.PostViewHolder>(),Filterable {

    var fullPostsList = listOf<Inquiry>()

    private val differCallback = object : DiffUtil.ItemCallback<Inquiry>() {
        override fun areItemsTheSame(oldItem: Inquiry, newItem: Inquiry): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Inquiry, newItem: Inquiry): Boolean {
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

        /**if (postData.image_path !=null ) {
            val file = File(NadrisApplication.instance?.applicationContext?.cacheDir,
                postData.id.toString())
            if (file.exists()) {
                img = BitmapFactory.decodeFile(file.absolutePath)
                holder.binding.imgPost.setImageBitmap(img!!)
                holder.binding.imgPost.visibility = View.VISIBLE
            } else {
                holder.binding.imgPost.visibility = View.GONE
            }
        }else {
            holder.binding.imgPost.visibility = View.GONE
        }*/

        holder.setDataBindingObj(postData)


        /**holder.binding.imgReply.setOnClickListener {
            holder.itemView.findNavController()
                .navigate(PostPageFragmentDirections.actionPostsFragmentToAddCommentFragment(postData.postId))
        }*/

        /**
        holder.binding.imgVote.setOnClickListener {

            postData.toggleVote()
            notifyItemChanged(position)

            postPageViewModel.vote(postData.postId)
                .let {
                    postData.updatePost(it!!)
                }
        }
*/
        /**holder.binding.bookmark.setOnClickListener {
            postData.toggleBookMark()

            postPageViewModel.bookMark(postData)
            notifyItemChanged(position)
        }*/

        /**holder.binding.profileImage.setOnClickListener {
            postPageViewModel.navigateToPublicProfilePage(postData.id)
        }*/

            try{
                val file = File(NadrisApplication.instance?.applicationContext?.cacheDir,
                    postData.id.toString())
                val profileImageBitMap = BitmapFactory.decodeFile(file.absolutePath)
                holder.binding.profileImage.setImageBitmap(profileImageBitMap)
            }catch (e:Throwable) {
                Log.e("PostsCustomAdapter", e.message.toString())
            }

        // todo: this features should be added after testing
        //  after testing so that user cannot edit or delete but his discussions
/**        if (NadrisApplication.userData?.id?.equals(postData.userId)!!){
            holder.binding.discussionMoreOption.isVisible(true)
            holder.binding.discussionMoreOption.setOnClickListener {
                val menu = android.widget.PopupMenu(it.context, it)

                menu.inflate(R.menu.discussion_more_options_menu)
                menu.show()

                menu.setOnMenuItemClickListener { menu_item ->

                    when (menu_item.itemId) {
                        R.id.delete_discussion_menu_item -> postPageViewModel.deletepost(postData.postId)
                        R.id.edit_discussion_menu_item -> postPageViewModel.navigate_to_edit_post(postData)
                    }

                    false
                }

            }
        }*/
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class PostViewHolder(var binding: ItemPostCardCellBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setDataBindingObj(post: Inquiry) {
            binding.postData = post
        }
    }

    override fun getFilter(): Filter  = searchFilter

        private val searchFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            fullPostsList = postPageViewModel.postsList.value!!

            val filteredList = mutableListOf<Inquiry>()

            if(constraint.isNullOrEmpty()){

                filteredList.addAll(fullPostsList)

            }else{
                val filterKeyWord = constraint.toString().toLowerCase().trim()
                for(post in fullPostsList){
                    if(post.body?.toLowerCase()?.contains(filterKeyWord)!!)
                        filteredList.add(post)
                }

            }

            val filterResult = FilterResults()
            filterResult.values =  filteredList
            return filterResult
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            differ.submitList(results?.values as MutableList<Inquiry>?)
        }

    }


}