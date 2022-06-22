package com.example.android.nadris.ui.studentActivity.select_teacher


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.CustomAdapterCourses
import com.example.android.nadris.databinding.FragmentTeachersCoursesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeachersCoursesFragment : Fragment() {
    val args: TeachersCoursesFragmentArgs by navArgs()
    val viewModel: TeachersCoursesViewModel by viewModels()
    private lateinit var adapter: CustomAdapterCourses
    private lateinit var binding: FragmentTeachersCoursesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        inflater.inflate(R.layout.fragment_teachers_courses, container, false)
        binding = FragmentTeachersCoursesBinding.inflate(inflater)
        viewModel.subjectId = args.subjectId
        viewModel.getdata()
        setupRV()
        return binding.root
    }


    private fun setupRV(){
        adapter= CustomAdapterCourses(viewModel)
        binding.RVTeachers.layoutManager=
            LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,false)
        binding.RVTeachers.adapter= adapter

        activity?.let {
            viewModel.list.observe(viewLifecycleOwner) {
                adapter.differ.submitList(it)
            }
        }


    }


}