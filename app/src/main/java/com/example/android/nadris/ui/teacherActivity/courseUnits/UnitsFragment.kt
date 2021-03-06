package com.example.android.nadris.ui.teacherActivity.courseUnits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentUnitsBinding
import com.example.android.nadris.network.firebase.NetworkObjectMapper
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UnitsFragment : Fragment() {
    val args: UnitsFragmentArgs by navArgs()
    val viewModel: UnitsViewModel by viewModels()
    private lateinit var binding: FragmentUnitsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentUnitsBinding.inflate(layoutInflater, container, false)


        binding.lifecycleOwner = this.viewLifecycleOwner
        viewModel.courseId = args.courseId
        binding.viewmodel = viewModel

        viewModel.getCourseUnits()

        binding.adapter = UnitItemAdapter(viewModel)

        viewModel.unitsList.observe(viewLifecycleOwner) {
            binding.adapter!!.differ.submitList(it)
        }

        viewModel.unitsListResult.observe(viewLifecycleOwner) { result ->
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
                    viewModel.unitsList.value = result.data?.map { unit ->
                        val lessonList = unit.lessons.map { lesson ->
                            val lesson =
                                NetworkObjectMapper.lessonAsDataBaseLesson(lesson, unit.unitId)
                            NetworkObjectMapper.lassonAsDatabaseLessonWithSections(lesson, listOf())
                        }
                        NetworkObjectMapper.unitAsDataBaseUnit(unit,
                            lessonList,
                            R.drawable.ic_google)

                    }
                }
            )
        }


        return binding.root
    }

}