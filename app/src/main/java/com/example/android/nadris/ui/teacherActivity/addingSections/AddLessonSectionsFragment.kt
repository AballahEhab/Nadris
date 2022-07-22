package com.example.android.nadris.ui.teacherActivity.addingSections

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentAddingSubjectSectionsBinding

class AddLessonSectionsFragment : Fragment() {

    val viewModel: AddingSectionsViewModel by activityViewModels()
    val args: AddLessonSectionsFragmentArgs by navArgs()
    lateinit var binding:FragmentAddingSubjectSectionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

         /**
         Inflate the layout for this fragment\
         **/

         binding = FragmentAddingSubjectSectionsBinding.inflate(layoutInflater, container, false)

//        val numOfSections = args.numOfSections
//        val unitNum = args.unitNum
//        val lessonNum = args.lessonNum
//        val lessonName = args.lessonName
//
        val numOfSections = 5
        val unitNum = 4
        val lessonNum = 3
        val lessonName = "lesson title"

        binding.sectionUnitAndLessonTitle.text = String.format(resources.getString(R.string.adding_sections_title), unitNum, lessonNum,lessonName)


        viewModel.setNumOfSections(numOfSections)

        viewModel.SectionAsHtml.observe(viewLifecycleOwner){
            Log.v("htmlFromTheparent",it)
        }

        val adapter = SectionNumAdapter (numOfSections,viewModel)
        binding.recyclerView.adapter = adapter

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
        activity?.viewModelStore?.clear()
    }
}