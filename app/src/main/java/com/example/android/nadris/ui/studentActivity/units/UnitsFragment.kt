package com.example.android.nadris.ui.studentActivity.units

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentUnitsBinding
import com.example.android.nadris.database.models.Lesson
import com.example.android.nadris.database.models.SubjectUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UnitsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UnitsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentUnitsBinding.inflate(inflater, container, false)


        val dataList = listOf(
            SubjectUnit(1,"unit1",listOf(Lesson("1.1","lesson1"),Lesson("2.1","lesson2"),Lesson("3.1","lesson3"),Lesson("4.1","lesson4")),R.drawable.ic_launcher_background),
            SubjectUnit(2,"unit2", listOf(Lesson("1.1","lesson1"),Lesson("2.1","lesson2"),Lesson("3.1","lesson3"),Lesson("4.1","lesson4")),R.drawable.ic_launcher_background),
            SubjectUnit(3,"unit3", listOf(Lesson("1.1","lesson1"),Lesson("2.1","lesson2"),Lesson("3.1","lesson3"),Lesson("4.1","lesson4")),R.drawable.ic_launcher_background),
            SubjectUnit(4,"unit4", listOf(Lesson("1.1","lesson1"),Lesson("2.1","lesson2"),Lesson("3.1","lesson3"),Lesson("4.1","lesson4")),R.drawable.ic_launcher_background),
            SubjectUnit(5,"unit5", listOf(Lesson("1.1","lesson1"),Lesson("2.1","lesson2"),Lesson("3.1","lesson3"),Lesson("4.1","lesson4")),R.drawable.ic_launcher_background),
            SubjectUnit(6,"unit6", listOf(Lesson("1.1","lesson1"),Lesson("2.1","lesson2"),Lesson("3.1","lesson3"),Lesson("4.1","lesson4")),R.drawable.ic_launcher_background),
            SubjectUnit(6,"unit7", listOf(Lesson("1.1","lesson1"),Lesson("2.1","lesson2"),Lesson("3.1","lesson3"),Lesson("4.1","lesson4")),R.drawable.ic_launcher_background),
            SubjectUnit(7,"unit8", listOf(Lesson("1.1","lesson1"),Lesson("2.1","lesson2"),Lesson("3.1","lesson3"),Lesson("4.1","lesson4")),R.drawable.ic_launcher_background),
            SubjectUnit(8,"unit9", listOf(Lesson("1.1","lesson1"),Lesson("2.1","lesson2"),Lesson("3.1","lesson3"),Lesson("4.1","lesson4")),R.drawable.ic_launcher_background),
            SubjectUnit(9,"unit10", listOf(Lesson("1.1","lesson1"),Lesson("2.1","lesson2"),Lesson("3.1","lesson3"),Lesson("4.1","lesson4")),R.drawable.ic_launcher_background),
        )
        var adapter = UnitItemAdapter(dataList)
        binding.unitsRv.adapter = adapter

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UnitsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UnitsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}