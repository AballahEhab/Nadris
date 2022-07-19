package com.example.android.nadris.ui.teacherActivity.addContent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private lateinit var binding:AddContentFragmentBinding
    val developerKey =  "AIzaSyCgkE7GGrY9Op4GEr61xs3u_xPznsUaaFo"
    val VIDEO_ID = "qSWPwbzepBQ"
    val TAG = "MaintActivity"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.add_content_fragment, container, false)
        binding = AddContentFragmentBinding.inflate(inflater)
        binding.viewModel=viewModel
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
                    Log.v(TAG,youTubeInitializationResult.name)
               }
            }


 val youTubePlayerFragment =
     requireActivity().fragmentManager!!.findFragmentById(R.id.youTube_fr) as YouTubePlayerFragment?
    youTubePlayerFragment!!.initialize(developerKey, onInitializedListener)

        return binding.root
    }



}