package com.example.android.nadris.ui.studentActivity.posts.comment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.databinding.FragmentAddCommentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCommentFragment : Fragment() {

    val args:AddCommentFragmentArgs by navArgs()
      val viewModel: AddCommentViewModel by viewModels()
    private lateinit var binding:FragmentAddCommentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentAddCommentBinding.inflate(inflater, container, false)

        binding.viewmodel=viewModel

        binding.lifecycleOwner = this

        viewModel.postId=args.postId

        viewModel.getCurrentPost()
        viewModel. getAllComments()

        setupRV()

        viewModel.comment.observe(viewLifecycleOwner){
            viewModel.sendButtonVisabilty.value = it.isNotBlank()  // TODO:
        }

        return binding.root
    }

    fun setupRV(){

        binding.RVComment.layoutManager= LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)

        val adapter = CustomAdapterComment()

            viewModel.commentsList.observe(viewLifecycleOwner){
                adapter.differ.submitList(it)
            }

        binding.RVComment.adapter=adapter

    }

    //TODO: delete of the data binding succeed
//    fun bindPost(){
//        binding.include.profileImage.setImageResource(data.imageStudent)
//        binding.include.textViewAccountName.text=data.name
//        binding.include.textSubjectName.text= data.subjectName
//        binding.include.textViewPost.text=data.content
//
//        binding.include.textvote.text = String.format(context.getString(R.string.vote), data.votesNum)
//        binding.include.textreply.text = String.format(context.getString(R.string.reply), data.commentsNum)
//        binding.include.imgVote.setOnClickListener {
//            binding.include.toggleVoteIconStatus(data.getVoteStatus())
//            if(data.getVoteStatus()) data.votesNum-- else data.votesNum++
//            data.toggleVote()
//            viewModel.vote(position,data.getVoteStatus())
//                .let {
//                    postList[position] = it!!
//                }
//            notifyItemChanged(position)
//        }
//
//        binding.include.bookMarkIcon.setOnClickListener {
//            binding.include.toggleBookMerkleIconStatus(data.getVoteBookMark())
//            data.toggleBookMark()
//            viewModel.BookMark(data.postId,data.getVoteBookMark())
//            notifyItemChanged(position)
//        }
//    }



}