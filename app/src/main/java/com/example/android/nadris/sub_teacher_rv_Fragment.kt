package com.example.android.nadris
/**
 * @author mohammed M sarhan
 * **/

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.databinding.SubTeacherRvFragmentBinding
import com.example.android.nadris.databinding.SubjectsRvFragmentBinding

class sub_teacher_rv_Fragment : Fragment() {



    private lateinit var viewModel: SubTeacherRvViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        inflater.inflate(R.layout.sub_teacher_rv__fragment, container, false)
        var binding =SubTeacherRvFragmentBinding.inflate(inflater)

        viewModel = ViewModelProvider(this).get(SubTeacherRvViewModel::class.java)
        binding.viewmodel = viewModel

        val subjects =ArrayList<dataRvsubTeach>()
        subjects.add(dataRvsubTeach("الفيزياء",160,"الثالث الثانوي","الفصل الدراسي الاول",R.drawable.icon_physics))
        subjects.add(dataRvsubTeach("الكيمياء",210,"الثاني الثانوي","الفصل الدراسي الثاني",R.drawable.icon_physics))

        binding.fabAddSubject.setOnClickListener {
            //todo Respond to Extended FAB click
        }
       binding.rvSubjectTeacher.layoutManager=
          LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
        binding.rvSubjectTeacher.adapter= customAdapterRVsubTeacher(subjects)

        return binding.root
    }


}