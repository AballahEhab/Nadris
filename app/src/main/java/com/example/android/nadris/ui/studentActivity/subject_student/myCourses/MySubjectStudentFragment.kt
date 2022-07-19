package com.example.android.nadris.ui.studentActivity.subject_student.myCourses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.databinding.MySubjectStudentFragmentBinding
import com.example.android.nadris.network.firebase.NetworkObjectMapper
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MySubjectStudentFragment : Fragment() {

    private val viewModel: MySubjectStudentViewModel by viewModels()
    private lateinit var adapter: MySubjectAdapter
    private lateinit var binding: MySubjectStudentFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

         inflater.inflate(R.layout.my_subject_student_fragment, container, false)

        binding = MySubjectStudentFragmentBinding.inflate(inflater)

        binding.viewmodel = viewModel

        viewModel.getCoursesCurrentUserSubscribedTo()

        setupRV()

        viewModel.coursesResultList.observe(viewLifecycleOwner){result->
            result.handleRepoResponse(
                onPreExecute = {

                },
                onLoading = {
                },
                onError = {
                    Snackbar.make(binding.root, result.error!!, Snackbar.LENGTH_LONG)
                        .show()
                },
                onSuccess = {
                    result.data?.let {
                        it?.let {
                            viewModel.list.value = result.data.map {
                                NetworkObjectMapper.NetworkCourseAsStudentCourse(it)
                            }
                        }
                    } ?: Snackbar.make( binding.root,
                        resources.getString(R.string.my_coures_error),
                        Snackbar.LENGTH_LONG)
                        .show()
                }
            )
        }

        return binding.root
    }

    private fun setupRV(){
        adapter = MySubjectAdapter()
        binding.rvSubjectStudent.layoutManager =
            LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL, false)
        binding.rvSubjectStudent.adapter= adapter

        activity?.let {
            viewModel.list.observe(viewLifecycleOwner) {
                adapter.differ.submitList(it)
            }

        }

    }


}