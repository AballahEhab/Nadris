package com.example.android.nadris.ui.studentActivity.subject_student

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.nadris.R

class headline_subjects : Fragment() {

    companion object {
        fun newInstance() = headline_subjects()
    }

    private lateinit var viewModel: HeadlineSubjectsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.headline_subjects_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HeadlineSubjectsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}