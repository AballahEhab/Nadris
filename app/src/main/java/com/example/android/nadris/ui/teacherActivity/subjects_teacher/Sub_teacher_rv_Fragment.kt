package com.example.android.nadris.ui.teacherActivity.subjects_teacher
/**
 * @author mohammed M sarhan
 * **/

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentSubTeacherRvBinding
import com.example.android.nadris.ui.teacherActivity.choosingNewSubjects.customAdapterRVsubTeacher
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubTeacherRVFragment : Fragment() {

    private val viewModel: SubTeacherRvViewModel by viewModels()
    private lateinit var adapter: customAdapterRVsubTeacher
    private lateinit var binding:FragmentSubTeacherRvBinding
    private  var   numOfSections:Int  = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inflater.inflate(R.layout.fragment_sub_teacher_rv, container, false)
        binding =FragmentSubTeacherRvBinding.inflate(inflater)

        binding.viewmodel = viewModel
        viewModel.getdata()
        setupRV()
        binding.fabAddSubject.setOnClickListener {
            val action = SubTeacherRVFragmentDirections.actionSubTeacherRvFragmentToChoosingNewSubjects()
            findNavController().navigate(action)
        }

        binding.fabAddSubject.setOnClickListener {

            val inputView = inflater.inflate(R.layout.layout_input,container,false)
            val inputLayout = (inputView.findViewById(R.id.youtube_link_input) as TextInputLayout)
            inputLayout.editText?.inputType = InputType.TYPE_CLASS_NUMBER
            inputLayout.editText?.hint = getString(R.string.please_enter_num_of_sections_hin)

            MaterialAlertDialogBuilder(requireContext())
                .setMessage("please set The number of sections")
                .setPositiveButton("add"
                ) { dialogInterface, i ->
                    try{
                        numOfSections = inputLayout.editText?.text.toString().toInt()
                    }catch (e:Throwable) {
                        e.message?.let { it1 -> Log.e("error", it1) }
                    }
                }
                .setNegativeButton("cancel"
                ) { dialogInterface, i ->
                    viewModel.navigateToAddingSectionFragment()

                }.setNeutralButton("neural"
                ) { dialogInterface, i ->
                    viewModel.navigateToAddingSectionFragment()

                }

                .setView(inputView)
                .show()
        }

        viewModel.navigateToAddingSectionFragmentEvent.observe(viewLifecycleOwner){
            if(it) {
                findNavController().navigate(SubTeacherRVFragmentDirections.actionSubTeacherRvFragmetnToAddingSectionsFrgament())
                viewModel.navigateToAddingSectionFragmentDone()
            }
        }

        return binding.root
    }


    private fun setupRV(){
        adapter= customAdapterRVsubTeacher()
        binding.rvSubjectTeacher.layoutManager=
            LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,false)
        binding.rvSubjectTeacher.adapter= adapter

        activity?.let {
            viewModel.list.observe(viewLifecycleOwner) {
                adapter.differ.submitList(it)
            }

        }

    }


}