package com.example.android.nadris.ui.studentActivity.subject_student.selectTeacherForASubject


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentTeachersCoursesBinding
import com.example.android.nadris.network.firebase.NetworkObjectMapper
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeachersCoursesFragment : Fragment() {
    val args: TeachersCoursesFragmentArgs by navArgs()
    val viewModel: TeachersCoursesViewModel by viewModels()
    private lateinit var adapter: CustomAdapterCourses
    private lateinit var binding: FragmentTeachersCoursesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        inflater.inflate(R.layout.fragment_teachers_courses, container, false)
        binding = FragmentTeachersCoursesBinding.inflate(inflater)
        viewModel.subjectId = args.subjectId
        viewModel.getCoursesWithSubject()
        setupRV()

        viewModel.coursesResultList.observe(viewLifecycleOwner) { result ->
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

                        viewModel.list.value = result.data.map {
                            NetworkObjectMapper.NetworkCourseAsTeachersCoursesModel(it)
                        }
                    } ?: Snackbar.make(binding.root,
                        resources.getString(R.string.my_coures_error),
                        Snackbar.LENGTH_LONG)
                        .show()
                }
            )
        }

        viewModel.subscribeAStudentToACourseResult.observe(viewLifecycleOwner) { result ->
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

                    } ?: Snackbar.make(binding.root,
                        resources.getString(R.string.my_coures_error),
                        Snackbar.LENGTH_LONG)
                        .show()
                }
            )
        }

        viewModel.navigateToUnitsPageForCourse.observe(viewLifecycleOwner) { courseId ->
            courseId?.let {
                findNavController().navigate(TeachersCoursesFragmentDirections.actionStudentTeachersForASubjectFragmentToStudentSubjectUnitsFragment(courseId))
            viewModel.navigateToUnitsPageForCourse.value = null
            }

        }
        return binding.root
    }

    private fun setupRV() {
        adapter = CustomAdapterCourses(viewModel)
        binding.RVTeachers.layoutManager =
            LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL, false)
        binding.RVTeachers.adapter = adapter

        activity?.let {
            viewModel.list.observe(viewLifecycleOwner) {
                adapter.differ.submitList(it)
            }
        }

    }

}