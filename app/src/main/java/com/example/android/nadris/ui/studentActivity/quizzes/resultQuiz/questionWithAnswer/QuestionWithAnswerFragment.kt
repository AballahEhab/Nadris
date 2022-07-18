package com.example.android.nadris.ui.studentActivity.quizzes.resultQuiz.questionWithAnswer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentQuestionWithAnswerBinding
import com.example.android.nadris.ui.studentActivity.quizzes.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionWithAnswerFragment : Fragment() {
    private val viewModel: QuizViewModel by activityViewModels()
    private lateinit var binding: FragmentQuestionWithAnswerBinding
    private lateinit var adapter: QuestionWithAnswerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.fragment_question_with_answer, container, false)
        binding = FragmentQuestionWithAnswerBinding.inflate(inflater)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel
        setupRV()
        binding.BackButton.setOnClickListener {
            val action = QuestionWithAnswerFragmentDirections.actionQuestionWithAnswerToResultQuiz()
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun setupRV() {
        adapter = QuestionWithAnswerAdapter(requireContext())
        binding.RVAnswerQuiz.layoutManager =
            LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL, false)
        binding.RVAnswerQuiz.adapter = adapter

        activity?.let {

            adapter.differ.submitList(viewModel.questionList)


        }
    }


}


