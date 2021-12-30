package com.example.android.nadris
/**
 * @author mohammed M sarhan
 * **/
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.databinding.SubjectsRvFragmentBinding
import com.example.android.nadris.login.LoginViewModel

class SubjectsRvFragment : Fragment() {

    private lateinit var viewModel: SubjectsRvFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

     inflater.inflate(R.layout.subjects_rv_fragment, container, false)
        var binding=SubjectsRvFragmentBinding.inflate(inflater);

       viewModel =ViewModelProvider(this).get(SubjectsRvFragmentViewModel::class.java)
       binding.viewmodel = viewModel

        val subjects =ArrayList<dataRVsubITEM>()
        subjects.add(dataRVsubITEM("الفيزياء",160,15,R.drawable.icon_physics))
        subjects.add(dataRVsubITEM("الكيمياء",210,20,R.drawable.icon_physics))
        subjects.add(dataRVsubITEM("اللغه العربيه",400,118,R.drawable.icon_physics))
        subjects.add(dataRVsubITEM("الأحياء",300,75,R.drawable.icon_physics))
        subjects.add(dataRVsubITEM("الجبر",94,36,R.drawable.icon_physics))
        subjects.add(dataRVsubITEM("الفيزياء",160,15,R.drawable.icon_physics))
        subjects.add(dataRVsubITEM("الكيمياء",210,20,R.drawable.icon_physics))
        subjects.add(dataRVsubITEM("اللغه العربيه",400,118,R.drawable.icon_physics))
        subjects.add(dataRVsubITEM("الأحياء",300,75,R.drawable.icon_physics))
        subjects.add(dataRVsubITEM("الجبر",94,36,R.drawable.icon_physics))

        binding.RVSubjects.layoutManager=LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        binding.RVSubjects.adapter= customAdapterRVsub(subjects)


        return binding.root
    }



}