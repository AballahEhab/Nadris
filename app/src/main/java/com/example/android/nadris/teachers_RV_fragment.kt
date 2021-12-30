package com.example.android.nadris
/**
 * @author mohammed sarhan
 * **/
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.databinding.SubjectsRvFragmentBinding
import com.example.android.nadris.databinding.TeachersRVFragmentBinding

class teachers_RV_fragment : Fragment() {


    private lateinit var viewModel: TeachersRVFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflater.inflate(R.layout.teachers__r_v_fragment, container, false)

        var binding= TeachersRVFragmentBinding.inflate(inflater);

        viewModel = ViewModelProvider(this).get(TeachersRVFragmentViewModel::class.java)
        binding.viewmodel = viewModel


        var teachers=ArrayList<dataRvTeach>()
         teachers.add(dataRvTeach("محمد مصطفي", "شرح ومراجعه", 8, R.drawable.icon_parent))
         teachers.add(dataRvTeach("شكري فضل", "شرح ", 8, R.drawable.icon_parent))
         teachers.add(dataRvTeach("عبدالله إيهاب", "ومراجعه", 8, R.drawable.icon_parent))
         teachers.add(dataRvTeach("عبدالله صلاح", "شرح ومراجعه", 8, R.drawable.icon_parent))
         teachers.add(dataRvTeach("محمد مصطفي", "شرح ", 8, R.drawable.icon_parent))
         teachers.add(dataRvTeach("شكري فضل", "مراجعه", 8, R.drawable.icon_parent))


//         RV_teachers.layoutmanager=linearlayoutmanager(this,linearlayout.VERTICAL,false)
//        RV_teachers.adapter= customAdapterRVTeach(teachers)
//        RV_teachers
        binding.RVTeachers.layoutManager=LinearLayoutManager(requireContext(),
            RecyclerView.VERTICAL,false)
        binding.RVTeachers.adapter= customAdapterRVTeach(teachers)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}