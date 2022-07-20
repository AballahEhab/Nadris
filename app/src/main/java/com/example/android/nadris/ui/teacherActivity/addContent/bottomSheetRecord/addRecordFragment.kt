package com.example.android.nadris.ui.teacherActivity.addContent.bottomSheetRecord

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.android.nadris.R
import com.example.android.nadris.databinding.AddContentFragmentBinding
import com.example.android.nadris.ui.teacherActivity.addContent.AddContentViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class addRecordFragment : Fragment() {
    private val viewModel: AddContentViewModel by activityViewModels()
    private lateinit var binding: AddContentFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.fragment_add_record, container, false)
        binding = AddContentFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        return binding.root
    }


}