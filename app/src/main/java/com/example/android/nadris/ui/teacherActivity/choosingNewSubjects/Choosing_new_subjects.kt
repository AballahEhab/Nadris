package com.example.android.nadris.ui.teacherActivity.choosingNewSubjects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentChoosingNewSubjectsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class choosing_new_subjects : Fragment() {

    private val viewModel: ChoosingNewSubjectsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        inflater.inflate(R.layout.fragment_choosing_new_subjects, container, false)
        val binding = FragmentChoosingNewSubjectsBinding.inflate(inflater)
        binding.viewmodel = viewModel

        viewModel.termList.addAll(resources.getStringArray(R.array.term_list))

        viewModel.getGrades()

        viewModel.grades.observe(viewLifecycleOwner) { list ->
            val adapter = ArrayAdapter(requireContext(), R.layout.item_gender_list, list.map { it.name })
            // get reference to the autocomplete text view
            (binding.chooseClass.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        }
        viewModel.subjects.observe(viewLifecycleOwner) { list ->
            val adapter = ArrayAdapter(requireContext(), R.layout.item_gender_list, list.map { it.name })
            // get reference to the autocomplete text view
            (binding.chooseSubjects.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        }

        val adapter = ArrayAdapter(requireContext(), R.layout.item_gender_list, viewModel.termList)
        (binding.termMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.btnAddNewSubject.setOnClickListener {
            viewModel.addSubject()

            val action =
                choosing_new_subjectsDirections.actionTeacherAddNewSubjectFragmentToTeacherMySubjectsFragment()
            findNavController().navigate(action)

        }

        return binding.root
    }

}