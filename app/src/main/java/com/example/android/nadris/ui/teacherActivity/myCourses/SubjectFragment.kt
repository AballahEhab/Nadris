package com.example.android.nadris.ui.teacherActivity.myCourses

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
import com.example.android.nadris.databinding.FragmentSubTeacherRvBinding
import com.example.android.nadris.network.firebase.NetworkObjectMapper
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectFragment : Fragment() {

    private val viewModel: SubjectViewModel by viewModels()
    private lateinit var adapter: SubjectAdapter
    private lateinit var binding: FragmentSubTeacherRvBinding
    private var numOfSections: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        inflater.inflate(R.layout.fragment_sub_teacher_rv, container, false)

        binding = FragmentSubTeacherRvBinding.inflate(inflater)

        binding.lifecycleOwner = this.viewLifecycleOwner

        binding.viewmodel = viewModel

        viewModel.getCoursesCurrentUserSubscribedTo()

        setupRV()

        binding.fabAddSubject.setOnClickListener {
            val action =
                SubjectFragmentDirections.actionTeacherMySubjectsFragmentToTeacherAddNewSubjectFragment()
            findNavController().navigate(action)
        }

        viewModel.coursesResultList.observe(viewLifecycleOwner) { result ->
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
                        viewModel.list.value = result.data.map {
                            NetworkObjectMapper.NetworkCourseAsTeacherCourse(it)
                        }
                    } ?: Snackbar.make(binding.root,
                        resources.getString(R.string.my_coures_error),
                        Snackbar.LENGTH_LONG)
                        .show()
                }
            )
        }

        return binding.root
    }


    private fun setupRV() {
        adapter = SubjectAdapter()
        binding.rvSubjectTeacher.layoutManager =
            LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL, false)
        binding.rvSubjectTeacher.adapter = adapter

        activity?.let {
            viewModel.list.observe(viewLifecycleOwner) {
                adapter.differ.submitList(it)
            }

        }

    }


}