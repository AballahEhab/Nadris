package com.example.android.nadris.ui.teacherActivity.addingSections

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.findFragment
import androidx.fragment.app.viewModels
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentAddingSubjectSectionsBinding
import com.example.android.nadris.ui.loginActivity.login.LoginViewModel
import com.example.android.nadris.ui.teacherActivity.textEditor.TextEditorFragment

class AddingSubjectSectionsFragment : Fragment() {

    val viewModel: AddingSectionsViewModel by activityViewModels()
    lateinit var binding:FragmentAddingSubjectSectionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

         /**
         Inflate the layout for this fragment\
         **/

         binding = FragmentAddingSubjectSectionsBinding.inflate(layoutInflater, container, false)

        viewModel.SectionAsHtml.observe(viewLifecycleOwner){
            Log.v("htmlFromTheparent",it)
        }
    /**
            binding.button.setOnClickListener {
                (binding.textEditorFragment. as TextEditorFragment ).mEditor?.setOnTextChangeListener {
                    Log.v("thisis",it)
                }
            }

     **/

        return binding.root
    }

    /**
    clearing the viewModel as it is attached
    to activity life cycle to make it shared across fragments
     **/
    override fun onDestroy() {
        super.onDestroy()
        activity?.viewModelStore?.clear();
    }
}