package com.example.android.nadris

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.nadris.databinding.AddPostFragmentBinding
import com.example.android.nadris.databinding.PostPageFragmentBinding
import com.example.android.nadris.databinding.SignupStudentFragmentBinding

class addPostFragment : Fragment() {

    companion object {
        fun newInstance() = addPostFragment()
    }

    private lateinit var viewModel: AddPostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this).get(AddPostViewModel::class.java)
        inflater.inflate(R.layout.add_post_fragment, container, false)
        val bindigin =AddPostFragmentBinding.inflate(inflater)
        bindigin.addPostViewModel = viewModel
        return bindigin.root}

}