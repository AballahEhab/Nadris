package com.example.android.nadris.ui.loginActivity.phoneVerification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentPhoneBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PhoneFragment : Fragment() {


    //initiating viewModel variable
    val viewModel: PhoneViewModel by viewModels()

    // initiating binding object for phone verification fragment
    lateinit var binding : FragmentPhoneBinding

    private var OTB:String =""
    private var OTBdigitsText = mutableListOf<TextView>()
    private var selected:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {


        // inflating the fragment to show it in the run time
        inflater.inflate(R.layout.fragment_phone,container,false)

        //getting the binding object to interact with views in this layout by the binding obj
        binding = FragmentPhoneBinding.inflate(inflater)
//        val args: PhoneFragmentArgs by navArgs<PhoneFragmentArgs>()

// Todo  1: uncomment this when navigating to this page to use the receved argument
//        val args =PhoneFragmentArgs.fromBundle(requireArguments())
//                val viewModelFactory = PhoneViewModelFactory(args.receivedOTBarg)
//        val args = "1234"
//        val viewModelFactory = PhoneViewModelFactory(args)
//
//        val viewModel = ViewModelProvider(this,viewModelFactory).get(PhoneViewModel::class.java)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel


        viewModel.isOTBMatched.observe(this.viewLifecycleOwner) {
            if (it) {
                //Todo2: set navigation code here
                Toast.makeText(context, "OTB confirmed", Toast.LENGTH_LONG).show()
            } else {
                val dialog = MaterialAlertDialogBuilder(requireContext())
                    .setCancelable(false)
                    .setTitle(R.string.wrong_OTB_text_dialog)

                    .setPositiveButton("ok") { dialog, _ ->
                        dialog.cancel()
                    }.show()

                val button = dialog.findViewById<Button>(android.R.id.button1)
                val layoutParams: LinearLayout.LayoutParams =
                    button!!.layoutParams as LinearLayout.LayoutParams
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                button.layoutParams = layoutParams

                //another solution to center the button
//                val parent = button.parent as LinearLayout
//                parent.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
//                val leftSpacer :View= parent. getChildAt(1);
//                leftSpacer.setVisibility(View.GONE);


            }
        }





        return binding.root
    }


}

