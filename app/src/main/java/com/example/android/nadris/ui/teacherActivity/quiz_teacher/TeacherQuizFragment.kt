package com.example.android.nadris.ui.teacherActivity.quiz_teacher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.databinding.TeacherQuizFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeacherQuizFragment : Fragment() {
    private val viewModel: TeacherQuizViewModel by viewModels()
    private lateinit var adapter: QuizAdapterTeacher
    private lateinit var binding: TeacherQuizFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.teacher_quiz_fragment, container, false)
        binding = TeacherQuizFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel
        adapter = QuizAdapterTeacher(viewModel)
        binding.RVTeacherQuiz.layoutManager =
            LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL, false)
        viewModel.listChanged.observe(viewLifecycleOwner) {
            adapter.differ.submitList(viewModel.list.value)
            binding.RVTeacherQuiz.adapter=adapter
        }
        binding.fabAddQuestion.setOnClickListener {
            viewModel.addQuestion()
        }
        binding.containedButton.setOnClickListener{
//            binding.questionText1.text= viewModel.list.value!![0].question
//            for(position in viewModel.list.value!![0].answer)
//            binding.questionText2.text = " "+position
        }
        return binding.root
    }

    private fun setupRV() {

    }


}