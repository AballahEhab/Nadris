package com.example.android.nadris.ui.studentActivity.posts.previewProfilePic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.databinding.FragmentPreviewProfilePicBinding

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
        binding.textView2.text = NadrisApplication.currentDatabaseUser?.getFullName()




        return binding.root


    }


}