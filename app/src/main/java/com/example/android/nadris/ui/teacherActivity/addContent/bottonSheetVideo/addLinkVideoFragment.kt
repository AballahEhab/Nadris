package com.example.android.nadris.ui.teacherActivity.addContent.bottonSheetVideo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentAddLinkVideoBinding
import com.example.android.nadris.ui.teacherActivity.addContent.AddContentViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.internal.TextWatcherAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class addLinkVideoFragment : BottomSheetDialogFragment() {
    private val viewModel: AddContentViewModel by activityViewModels()
    private lateinit var binding:FragmentAddLinkVideoBinding




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.fragment_add_link_video, container, false)
        binding= FragmentAddLinkVideoBinding.inflate(inflater)
        binding.viewModel=viewModel
//binding.edtVideoLink.doOnTextChanged { text, start, before, count ->
//    viewModel.VIDEO_ID = text.toString()
//}



        binding.savedButton.setOnClickListener {
            viewModel.VIDEO_ID= binding.edtVideoLink.text.toString()
            val action = addLinkVideoFragmentDirections.actionAddLinkVideoFragmentToAddContentFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }


}