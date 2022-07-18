package com.example.android.nadris.ui.studentActivity.quizzes.resultQuiz.feedbackSheet

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentFeedbackBinding
import com.example.android.nadris.ui.studentActivity.quizzes.QuizViewModel
import com.example.android.nadris.util.isVisible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FeedbackFragment : Fragment() {
    private val viewModel: QuizViewModel by activityViewModels()
    private lateinit var binding:FragmentFeedbackBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.fragment_feedback, container, false)
        binding=FragmentFeedbackBinding.inflate(inflater)
        binding.viewModel= viewModel
        if(viewModel.wrongQuestion.size==0){
            binding.wrongListQuestion.isVisible(false)
        }else{
            var indexs = 0
            var num1=0
            for(num in viewModel.wrongQuestion){
                binding.wrongListQuestion.append(viewModel.questionList!![num-1].answer_location+"\n")
            }

        }
        binding.resultCorrectText.text = viewModel.score.toString()
        binding.resultWrongText.text = viewModel.wrongQuestion.size.toString()
        binding.messageText.movementMethod= ScrollingMovementMethod()
        binding.BackButton.setOnClickListener {
            val action = FeedbackFragmentDirections.actionFeedbackPageToResultQuiz()
            findNavController().navigate(action)
        }
        return binding.root
    }


}