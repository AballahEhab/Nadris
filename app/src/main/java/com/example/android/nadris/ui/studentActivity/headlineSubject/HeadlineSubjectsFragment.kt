package com.example.android.nadris.ui.studentActivity.headlineSubject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentHeadlineSubjectsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HeadlineSubjectsFragment : Fragment() {
    val args: HeadlineSubjectsFragmentArgs by navArgs()
    val viewModel: HeadlineSubjectsViewModel by viewModels()
    private lateinit var binding: FragmentHeadlineSubjectsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.fragment_headline_subjects, container, false)
        binding = FragmentHeadlineSubjectsBinding.inflate(inflater)
        viewModel.subjectId = args.subjectId
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewmodel = viewModel
        viewModel.getData()
//        binding.adapter = CustomAdapterHeadline(viewModel)
//
//        viewModel.units.observe(viewLifecycleOwner) {
//            binding.adapter!!.differ.submitList(it)
//        }

        return binding.root
    }


}