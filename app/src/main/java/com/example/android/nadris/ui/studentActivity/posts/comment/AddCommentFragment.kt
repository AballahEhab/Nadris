package com.example.android.nadris.ui.studentActivity.posts.comment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentAddCommentBinding
import com.example.android.nadris.ui.teacherActivity.subjects_teacher.sub_teacher_rv_FragmentArgs
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
        inflater.inflate(R.layout.fragment_add_comment, container, false)
        binding = FragmentAddCommentBinding.inflate(inflater)
      binding.viewmodel=viewModel

        //tv.text = arguments?.getString("amount")

        viewModel.post_id=args.postId

        setupRV()


        return binding.root
    }
    public fun setupRV(){
        binding.RVComment.layoutManager= LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        val adapter = CustomAdapterComment()


        activity?.let{
            viewModel.getData().observe(
                viewLifecycleOwner
            ){
                adapter.differ.submitList(it)
            }

        }
        binding.RVComment.adapter=adapter

    }



}