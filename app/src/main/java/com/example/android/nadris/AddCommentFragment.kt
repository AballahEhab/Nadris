package com.example.android.nadris

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class AddCommentFragment : Fragment() {



    private lateinit var viewModel: AddCommentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        viewModel = ViewModelProvider(this).get(AddCommentViewModel::class.java)



        return inflater.inflate(R.layout.fragment_add_comment, container, false)
    }



}