package com.example.android.nadris.ui.studentActivity.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentProfileBinding
import com.example.android.nadris.ui.SplashActivity
import com.example.android.nadris.ui.loginActivity.MainActivity
import com.example.android.nadris.ui.studentActivity.posts.PostPageViewModel
import com.example.android.nadris.ui.studentActivity.posts.CustomAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    val viewModel: ProfileViewModel by viewModels()
    val postsViewModel: PostPageViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.fragment_profile, container, false)
        binding = FragmentProfileBinding.inflate(layoutInflater)
        binding.viewmodel = viewModel

        binding.lifecycleOwner = this
        viewModel.getProfileInfo_from_api()
        viewModel.getLastActivity()

        binding.rvPostsProfile.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        val adapter = CustomAdapter(postsViewModel)

        binding.rvPostsProfile.adapter = adapter

        viewModel.postsProfileList.observe(viewLifecycleOwner) {
            adapter.differ.submitList(it.toList())
            Log.i("length", it.size.toString())
        }

        binding.imagSetting.setOnClickListener {
            this.findNavController()
                .navigate(ProfileFragmentDirections.actionPrivateProfileFragmentToSettingsFragment())
        }


        viewModel.navigateToLoginPage.observe(viewLifecycleOwner) {
            if(it){
                requireActivity().startActivity(Intent(requireContext(), SplashActivity::class.java))
                NadrisApplication.userData = null
            }
        }

        return binding.root
    }


}