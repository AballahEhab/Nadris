package com.example.android.nadris.ui.teacherActivity.addContent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.nadris.R
import com.example.android.nadris.databinding.AddContentFragmentBinding
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class addContentFragment : Fragment() {
    val viewModel: AddContentViewModel by viewModels()
    private lateinit var binding: AddContentFragmentBinding
    val developerKey = "AIzaSyBSpymPuRHcpD8_NPTRbEVkM4_ik7mwUiY"
    val VIDEO_ID = "ih0oeJLA1Pg"
    val TAG = "MaintActivity"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = AddContentFragmentBinding.inflate(layoutInflater, container, false)

        binding.viewModel = viewModel

        val onInitializedListener: YouTubePlayer.OnInitializedListener =
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer,
                    b: Boolean,
                ) {
                    youTubePlayer.loadVideo(VIDEO_ID)
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult,
                ) {
                }
            }

        val youTubePlayerFragment =
            requireActivity().fragmentManager!!.findFragmentById(R.id.youTube_fr) as YouTubePlayerFragment?
        youTubePlayerFragment!!.initialize(developerKey, onInitializedListener)

        return binding.root
    }


}