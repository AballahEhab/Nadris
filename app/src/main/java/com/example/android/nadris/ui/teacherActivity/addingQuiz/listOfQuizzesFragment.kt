package com.example.android.nadris.ui.teacherActivity.addingQuiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.ListOfQuizzesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class listOfQuizzesFragment : Fragment() {

    private val viewModel: ListOfQuizzesViewModel by viewModels()
    private lateinit var binding: ListOfQuizzesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.list_of_quizzes_fragment, container, false)
        binding = ListOfQuizzesFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.fabAddQuiz.setOnClickListener {
            val action = listOfQuizzesFragmentDirections.actionQuizzesFragmentToAddQuiz()
            findNavController().navigate(action)
        }
        return binding.root
    }


}