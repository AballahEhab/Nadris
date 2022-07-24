package com.example.android.nadris.ui.teacherActivity.addingQuiz.newQuiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.R
import com.example.android.nadris.databinding.TeacherQuizFragmentBinding
import com.google.android.material.snackbar.Snackbar
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

        binding = TeacherQuizFragmentBinding.inflate(layoutInflater, container, false)

        binding.lifecycleOwner = this.viewLifecycleOwner

        binding.viewModel = viewModel

        viewModel.getGrades()

        setPageVisibility(false)

        setUpTermSpinner()

        disableSubjectsSpinner()

        adapter = QuizAdapterTeacher(viewModel)

        binding.RVTeacherQuiz.layoutManager =
            LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL, false)

        viewModel.listChanged.observe(viewLifecycleOwner) {
            adapter.differ.submitList(viewModel.quizQuestionsList.value)
            binding.RVTeacherQuiz.adapter = adapter
        }

        binding.fabAddQuestion.setOnClickListener {
            viewModel.addQuestion()
        }

        (binding.chooseClass.editText as AutoCompleteTextView).setOnItemClickListener { parent, view, position, id ->
            viewModel.setSelectedGradeIndex(position)
        }
        (binding.chooseSubjects.editText as AutoCompleteTextView).setOnItemClickListener { parent, view, position, id ->
            viewModel.setSubject(position)
        }
        (binding.termMenu.editText as AutoCompleteTextView).setOnItemClickListener { parent, view, position, id ->
            viewModel.selectedTerm.value = (position == 1)
        }

        viewModel.gradesResult.observe(viewLifecycleOwner) { result ->
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
                    setPageVisibility(true)
                    viewModel.gradesList.value = result.data
                }
            )
        }

        viewModel.subjectResult.observe(viewLifecycleOwner) { result ->
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
                    enableSubjectsSpinner()
                    viewModel.subjectsList.value = result.data
                }
            )
        }

        viewModel.addingQuizResult.observe(viewLifecycleOwner) { result ->
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
                    Snackbar.make(binding.root,
                        resources.getString(R.string.quiz_Added_Successfully),
                        Snackbar.LENGTH_LONG)
                        .show()
                    val action = TeacherQuizFragmentDirections.actionAddQuizToQuizzesFragment()
                    findNavController().navigate(action)
                }
            )
        }
        viewModel.gradesList.observe(viewLifecycleOwner) { list ->
            list?.let {
                val adapter = ArrayAdapter(requireContext(), R.layout.item_gender_list, list.map { it.name_ar })
                (binding.chooseClass.editText as? AutoCompleteTextView)?.setAdapter(adapter)
            }
        }

        viewModel.subjectsList.observe(viewLifecycleOwner) { list ->
            val adapter =
                ArrayAdapter(requireContext(), R.layout.item_gender_list, list.map { if(NadrisApplication.instance?.lang == "en") it.name_en else it.name_ar})
            (binding.chooseSubjects.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        }
        return binding.root
    }

    private fun setUpTermSpinner() {
        viewModel.termList.addAll(resources.getStringArray(R.array.term_list))
        val adapter = ArrayAdapter(requireContext(), R.layout.item_gender_list, viewModel.termList)
        (binding.termMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun enableSubjectsSpinner() {
        binding.chooseSubjects.isEnabled = true
    }

    private fun disableSubjectsSpinner() {
        binding.chooseSubjects.isEnabled = false
    }

    private fun setPageVisibility(visibility: Boolean) {
        binding.pageContentParentView.isVisible = visibility
    }
}