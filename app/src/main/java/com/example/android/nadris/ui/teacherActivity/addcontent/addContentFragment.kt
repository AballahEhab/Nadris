package com.example.android.nadris.ui.teacherActivity.addcontent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.nadris.R
import com.example.utils.YouTubeConfig
import com.example.android.nadris.databinding.AddContentFragmentBinding
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class addContentFragment : Fragment() {
    private val viewModel: AddContentViewModel by viewModels()
    private lateinit var binding:AddContentFragmentBinding
    private lateinit var mYouTubeFragment : YouTubePlayerSupportFragment
    private lateinit var mInInitializedlistener:YouTubePlayer.OnInitializedListener
    private lateinit var youTubeConfig: YouTubeConfig

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.add_content_fragment, container, false)
        binding= AddContentFragmentBinding.inflate(inflater)
        mYouTubeFragment= YouTubePlayerSupportFragment.newInstance()
        requireActivity().supportFragmentManager!!.beginTransaction().add(R.id.youTube_fr, mYouTubeFragment as Fragment).commit()
        return binding.root
    }





}