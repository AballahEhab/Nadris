package com.example.android.nadris.ui.studentActivity.followProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FollowProfileFragmentBinding
import com.example.android.nadris.ui.studentActivity.posts.CustomAdapter
import com.example.android.nadris.ui.studentActivity.posts.PostPageViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FollowProfileFragment : Fragment() {

    val viewModel: FollowProfileViewModel by viewModels ()
    val postsViewModel: PostPageViewModel by viewModels()
    private lateinit var binding:FollowProfileFragmentBinding

    val args: FollowProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.follow_profile_fragment, container, false)
        binding = FollowProfileFragmentBinding.inflate(inflater)
        binding.viewmodel = viewModel

        binding.lifecycleOwner = this


        viewModel.publicProfileId = args.userProfileId

        viewModel.getPublicProfileData()

        viewModel.getLastActivity()

        binding.rvPostsFollowProfile.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        val adapter = CustomAdapter(postsViewModel)

        binding.rvPostsFollowProfile.adapter = adapter

        viewModel.postsProfileList.observe(viewLifecycleOwner) {
            adapter.differ.submitList(it)
        }


//        /**todo: uncomment this code when needed**/
//        binding.tgbtnAddFrind.setOnCheckedChangeListener { _, isChecked ->
//
//            Log.v("toggleButotn",isChecked.toString())
//            if(isChecked){
//        viewModel.toggleFlowPublicProfile()
//            }else{
//
//            }
//        }



        return binding.root
    }



}