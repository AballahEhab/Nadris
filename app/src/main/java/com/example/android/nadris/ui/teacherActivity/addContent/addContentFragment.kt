package com.example.android.nadris.ui.teacherActivity.addContent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.AddContentFragmentBinding
import com.example.android.nadris.ui.studentActivity.quizzes.resultQuiz.ResultFragmentDirections
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class addContentFragment : Fragment() {
    val viewModel: AddContentViewModel by activityViewModels()
    private lateinit var binding: AddContentFragmentBinding
    val TAG = "MaintActivity"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = AddContentFragmentBinding.inflate(layoutInflater, container, false)

        binding.viewModel = viewModel
        binding.video.setOnClickListener {
            val action = addContentFragmentDirections.actionAddContentFragmentToAddAudioFragment()
            findNavController().navigate(action)
        }
        binding.audio.setOnClickListener {
            val action = addContentFragmentDirections.actionAddContentFragmentToAddLinkVideoFragment()
            findNavController().navigate(action)
        }

            youtubePlay()


        return binding.root
    }

    fun youtubePlay() {
        val onInitializedListener: YouTubePlayer.OnInitializedListener =
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer,
                    b: Boolean,
                ) {
                    youTubePlayer.loadVideo(viewModel.VIDEO_ID)
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult,
                ) {
                }
            }

        val youTubePlayerFragment =
            requireActivity().fragmentManager!!.findFragmentById(R.id.youTube_fr) as YouTubePlayerFragment?
        youTubePlayerFragment!!.initialize(viewModel.developerKey, onInitializedListener)
    }


}