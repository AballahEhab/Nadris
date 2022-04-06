package com.example.android.nadris.ui.studentActivity.units

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentSubjectBinding
import com.example.android.nadris.ui.studentActivity.subject_student.SubjectsRvFragment
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
        adapter.addFragment(SubjectsRvFragment(),"Explore")
        adapter.addFragment(UnitsFragment(),"YourSubject")
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        binding.tabs.getTabAt(0)
        binding.tabs.getTabAt(1)





    }
}