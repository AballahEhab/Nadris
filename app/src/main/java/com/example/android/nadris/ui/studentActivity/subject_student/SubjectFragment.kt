package com.example.android.nadris.ui.studentActivity.subject_student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentSubjectBinding
import com.example.android.nadris.ui.studentActivity.subject_student.exploreCourses.SubjectsRvFragment
import com.example.android.nadris.ui.studentActivity.subject_student.myCourses.MySubjectStudentFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectFragment :Fragment() {
    private lateinit var binding: FragmentSubjectBinding
    private lateinit var adapter: ViewPageAdapter
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        inflater.inflate(R.layout.fragment_subject, container, false)
        binding = FragmentSubjectBinding.inflate(inflater)
        setUpTabs()
        return binding.root
    }
    private fun setUpTabs(){
        val adapter = ViewPageAdapter(childFragmentManager)
        adapter.addFragment(SubjectsRvFragment(),resources.getString(R.string.explore_courses_title))
        adapter.addFragment(MySubjectStudentFragment(),resources.getString(R.string.my_courses_title))
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        binding.tabs.getTabAt(0)
        binding.tabs.getTabAt(1)





    }
}