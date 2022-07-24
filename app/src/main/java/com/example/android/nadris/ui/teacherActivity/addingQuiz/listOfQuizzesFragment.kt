package com.example.android.nadris.ui.teacherActivity.addingQuiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.databinding.ListOfQuizzesFragmentBinding
import com.example.android.nadris.network.firebase.dtos.QuizData
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class listOfQuizzesFragment : Fragment() {

    private val viewModel: ListOfQuizzesViewModel by viewModels()
    private lateinit var binding: ListOfQuizzesFragmentBinding
    lateinit var adapter: TeacherMyQuizzesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = ListOfQuizzesFragmentBinding.inflate(layoutInflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        viewModel.getTeacherQuizzes()

        setupRV()

        viewModel.quizzesResultList.observe(viewLifecycleOwner) { result ->
            result.handleRepoResponse(
                onPreExecute = {
                    viewModel.disableLoading()
                },
                onLoading = {
                    viewModel.enableLoading()
                },
                onError = {
                    Snackbar.make(binding.root, result.error!!, Snackbar.LENGTH_LONG)
                        .show()
                },
                onSuccess = {
                    result.data?.let {
                        viewModel.quizzesList.value = result.data as List<QuizData>?
                    } ?: Snackbar.make(binding.root,
                        resources.getString(R.string.my_coures_error),
                        Snackbar.LENGTH_LONG)
                        .show()
                }
            )
        }


        binding.fabAddQuiz.setOnClickListener {
            val action = listOfQuizzesFragmentDirections.actionQuizzesFragmentToAddQuiz()
            findNavController().navigate(action)
        }
        return binding.root
    }
    private fun setupRV() {
        adapter = TeacherMyQuizzesAdapter()
        binding.rvQuizTeacher.layoutManager =
            LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL, false)
        binding.rvQuizTeacher.adapter = adapter

        activity?.let {
            viewModel.quizzesList.observe(viewLifecycleOwner) {
                adapter.differ.submitList(it)
            }

        }

    }


}