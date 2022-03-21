package com.example.android.nadris.ui.studentActivity.posts.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        viewModel.postLiveData=args.post

        binding.includedPost.postData = viewModel.postLiveData

        viewModel.getAllComments()


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
}