package com.example.android.nadris.ui.teacherActivity.choosingNewSubjects

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentChoosingNewSubjectsBinding

class choosing_new_subjects : Fragment() {

    private lateinit var viewModel: ChoosingNewSubjectsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        inflater.inflate(R.layout.fragment_choosing_new_subjects, container,false)
        val binding =FragmentChoosingNewSubjectsBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(ChoosingNewSubjectsViewModel::class.java)
       binding.viewmodel = viewModel

        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        val adapter_class = ArrayAdapter(requireContext(), R.layout.item_dropdown_choose_class,
            R.id.textView, viewModel.class_)
        // get reference to the autocomplete text view
        (binding.autoCompleteTVChooseClass as? AutoCompleteTextView)?.setAdapter(adapter_class)

        val adapter_subject=ArrayAdapter(requireContext(), R.layout.item_dropdown_choose_class,
            R.id.textView,viewModel.subject_)
        (binding.autoCompleteTVChooseSubjects as? AutoCompleteTextView)?.setAdapter(adapter_subject)

        val adapter_semester=ArrayAdapter(requireContext(), R.layout.item_dropdown_choose_class,
            R.id.textView,viewModel.semester_)
        (binding.autoCompleteTVChooseSemester as? AutoCompleteTextView)?.setAdapter(adapter_semester)

        binding.btnAddNewSubject.setOnClickListener {
            val action=choosing_new_subjectsDirections.
               actionChoosingNewSubjectsToSubTeacherRvFragment(selectClass = viewModel.select_class,
                    selectSubject = viewModel.select_subject, selectSemester = viewModel.select_semester
               )
            findNavController().navigate(action)

        }

        return binding.root
    }

}