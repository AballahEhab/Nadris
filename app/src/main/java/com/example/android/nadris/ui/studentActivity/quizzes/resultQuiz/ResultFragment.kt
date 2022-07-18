package com.example.android.nadris.ui.studentActivity.quizzes.resultQuiz

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.QuizFragmentBinding
import com.example.android.nadris.databinding.ResultFragmentBinding
import com.example.android.nadris.ui.studentActivity.quizzes.QuizViewModel
import com.example.android.nadris.util.isVisible
import com.google.firebase.auth.ktx.actionCodeSettings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : Fragment() {
    val TAG = "ResultFragment"
    val viewModel:QuizViewModel  by activityViewModels()
    private lateinit var binding: ResultFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.result_fragment, container, false)
        binding=ResultFragmentBinding.inflate(inflater)
        binding.viewModel= viewModel
        val scoredPercent = (viewModel.score.toFloat() / viewModel.questionList!!.size.toFloat())*100
        Log.v(TAG,"scoredPercent ="+ scoredPercent)
        Log.v(TAG,"scored ="+ viewModel.score)
        binding.resultPercent.text= scoredPercent.toString() // percent.toString() + "%"
        binding.resultProgress.progress = scoredPercent.toInt()
        loadAnimation(scoredPercent,viewModel.score,viewModel.questionList!!.size)
        binding.resultHomeBtn.setOnClickListener {
          val action = ResultFragmentDirections.actionResultQuizToPostsFragment()
            findNavController().navigate(action)
        }
        binding.quizAnswerBtn.setOnClickListener {
            val action = ResultFragmentDirections.actionResultQuizToQuestionWithAnswer()
            findNavController().navigate(action)
        }
        binding.reportBtn.setOnClickListener {
            val action = ResultFragmentDirections.actionResultQuizToFeedbackPage()
            findNavController().navigate(action)
        }
        return binding.root
    }
    private fun loadAnimation(percent: Float, marksScored: Int, totalMarks: Int) {
        if (percent >= 60) {
            binding.fireworksAnimation.isVisible = true
            binding.resultFeedback.text = "Yay! you scored ${String.format("%.1f", marksScored.toFloat())} out of ${String.format("%.1f", totalMarks.toFloat())}"
        } else {
            binding.resultFeedback.text = "You scored ${String.format("%.1f", marksScored.toFloat())} out of ${String.format("%.1f", totalMarks.toFloat())}"
            binding.lowResultFeedback.isVisible = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.delet()
    }
}