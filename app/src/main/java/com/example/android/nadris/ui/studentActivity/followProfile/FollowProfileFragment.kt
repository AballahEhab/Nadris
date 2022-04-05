package com.example.android.nadris.ui.studentActivity.followProfile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FollowProfileFragmentBinding
import com.example.android.nadris.ui.studentActivity.posts.PostPageViewModel
import com.example.android.nadris.ui.studentActivity.posts.customAdapter

class FollowProfileFragment : Fragment() {

    val viewModel: FollowProfileViewModel by viewModels ()
    val postsViewModel: PostPageViewModel by viewModels()
    private lateinit var binding:FollowProfileFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.follow_profile_fragment, container, false)
        binding = FollowProfileFragmentBinding.inflate(inflater)
        binding.viewmodel = viewModel

        binding.lifecycleOwner = this
        viewModel.getLastActivity()

        binding.rvPostsFollowProfile.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        val adapter = customAdapter(postsViewModel)

        binding.rvPostsFollowProfile.adapter = adapter

        viewModel.postsProfileList.observe(viewLifecycleOwner) {
            adapter.differ.submitList(it.toList())
        }

        binding.tgbtnAddFrind.setOnCheckedChangeListener { _, isChecked ->

            if(isChecked){
                

            }else{


            }
        }

        return binding.root
    }



}