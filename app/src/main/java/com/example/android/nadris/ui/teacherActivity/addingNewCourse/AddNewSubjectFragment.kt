package com.example.android.nadris.ui.teacherActivity.addingNewCourse

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
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentAddNewSubjectBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewSubjectFragment : Fragment() {

    private val viewModel: AddNewSubjectViewModel by viewModels()
    lateinit var binding: FragmentAddNewSubjectBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentAddNewSubjectBinding.inflate(layoutInflater, container, false)

        binding.lifecycleOwner = this.viewLifecycleOwner

        binding.viewmodel = viewModel

        setPageVisibility(false)

        setUpTermSpinner()

        disableSubjectsSpinner()


        (binding.chooseClass.editText as AutoCompleteTextView).setOnItemClickListener { parent, view, position, id ->
            viewModel.setSelectedGradeIndex(position)
            viewModel.validateInputFields()
        }
        (binding.chooseSubjects.editText as AutoCompleteTextView).setOnItemClickListener { parent, view, position, id ->
            viewModel.setSubject(position)
            viewModel.validateInputFields()
        }
        (binding.termMenu.editText as AutoCompleteTextView).setOnItemClickListener { parent, view, position, id ->
            viewModel.selectedTerm.value = (position == 1)
            viewModel.validateInputFields()
        }

        viewModel.getGrades()

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

        viewModel.addingCourseResult.observe(viewLifecycleOwner) { result ->
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
                    Snackbar.make(binding.root, resources.getString(R.string.course_added_successfully), Snackbar.LENGTH_LONG)
                        .show()
                    val action =
                        AddNewSubjectFragmentDirections.actionTeacherAddNewSubjectFragmentToTeacherMySubjectsFragment()
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

    private fun enableSubjectsSpinner(){
        binding.chooseSubjects.isEnabled = true
    }

    private fun disableSubjectsSpinner(){
        binding.chooseSubjects.isEnabled = false
    }

    private fun setPageVisibility(visibility: Boolean) {
        binding.pageContentParentView.isVisible=visibility
    }

}