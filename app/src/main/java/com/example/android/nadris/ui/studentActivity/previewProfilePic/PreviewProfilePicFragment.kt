package com.example.android.nadris.ui.studentActivity.previewProfilePic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentPreviewProfilePicBinding
import com.example.android.nadris.services.Converter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class PreviewProfilePicFragment : Fragment() {

//    var lateinit args: FragmentPreviewProfilePicBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding =  FragmentPreviewProfilePicBinding.inflate(layoutInflater, container, false)

//        binding.imageButton2.setImageResource(Converter(requireContext().applicationContext).convertFromBase64ToBitmap(null,"profile_photo"))




        return binding.root


    }


}