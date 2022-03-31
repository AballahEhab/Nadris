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
import com.example.android.nadris.databinding.FragmentAddNewSubjectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewSubjectFragment : Fragment() {

    private val viewModel: AddNewSubjectViewModel by viewModels()
lateinit var binding:FragmentAddNewSubjectBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        inflater.inflate(R.layout.fragment_add_new_subject, container, false)
        val binding = FragmentAddNewSubjectBinding.inflate(inflater)
        binding.viewmodel = viewModel

        viewModel.termList.addAll(resources.getStringArray(R.array.term_list))

        viewModel.getGrades()

        viewModel.grades.observe(viewLifecycleOwner) { list ->
            val adapter = ArrayAdapter(requireContext(), R.layout.item_gender_list, list.map { it.name })

            (binding.chooseClass.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        }
        viewModel.subjects.observe(viewLifecycleOwner) { list ->
            val adapter = ArrayAdapter(requireContext(), R.layout.item_gender_list, list.map { it.name })
            (binding.chooseSubjects.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        }

        val adapter = ArrayAdapter(requireContext(), R.layout.item_gender_list, viewModel.termList)
        (binding.termMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.btnAddNewSubject.setOnClickListener {
            viewModel.addSubject()

            val action =
                AddNewSubjectFragmentDirections.actionAddNewSubjectsToSubjectFragment()
            findNavController().navigate(action)

        }

        return binding.root
    }

}