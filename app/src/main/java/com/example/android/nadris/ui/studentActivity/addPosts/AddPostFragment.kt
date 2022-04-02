package com.example.android.nadris.ui.studentActivity.addPosts

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.android.nadris.*
import com.example.android.nadris.databinding.FragmentAddPosBinding
import com.example.android.nadris.services.Converter
import com.example.android.nadris.util.LoadImageFromDevice
import com.example.android.nadris.util.getResizedBitmap
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddPostFragment : Fragment() {

    val viewModel: AddPostViewModel by viewModels()
    lateinit var binding: FragmentAddPosBinding
    private lateinit var converter: Converter
    private var image: Bitmap? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        inflater.inflate(R.layout.fragment_add_pos, container, false)
        binding = FragmentAddPosBinding.inflate(inflater)
        binding.addPostViewModel = viewModel
        //initialize converter
        converter = Converter(requireContext().applicationContext)
        if (viewModel.isTeacher)
            viewModel.getGrades()
        else viewModel.getSubjects()


        viewModel.grades.observe(viewLifecycleOwner) { list ->
            val adapter = ArrayAdapter(requireContext(), R.layout.item_gender_list, list.map { it.name })
            (binding.spGrades.editText as? AutoCompleteTextView)?.setAdapter(adapter)!!
        }
        viewModel.subjects.observe(viewLifecycleOwner) { list ->
            val adapter = ArrayAdapter(requireContext(), R.layout.item_gender_list, list.map { it.name })
            (binding.spAddSubject.editText as? AutoCompleteTextView)?.setAdapter(adapter)!!
        }

        binding.imageButton.setOnClickListener {
            LoadImageFromDevice.selectImage(requireActivity(),requireContext(),this)
        }

        binding.publishButton.setOnClickListener {
            if (binding.textViewAddQesition.text.toString().isNotEmpty() && viewModel.selectedSubject.value != null) {
                viewModel.addPost()
                it.findNavController().navigate(AddPostFragmentDirections.actionAddPostFragmentToNavigationPosts())
            } else {
                Toast.makeText(requireContext().applicationContext,
                    getString(R.string.add_post_requirments),
                    Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray,
    ) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), getString(R.string.permission_granted), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), getString(R.string.permission_granted), Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), getString(R.string.permission_granted), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), getString(R.string.permission_granted), Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) {
            // Handle error
            return
        }
        when (requestCode) {
            REQUEST_IMAGE_CAPTURE -> {
                image = data?.extras?.get("data") as Bitmap
                try {
                    viewModel.imageStrB64 = converter.convertBitmapToBase64(image!!)
                    binding.pickedImage.setImageBitmap(image)
                } catch (e: Exception) {
                    Log.e("b64", e.message.toString())
                }
            }
            PHOTO_PICKER_REQUEST_CODE -> {
                val selectedImage = data?.data
                val filePath = arrayOf(MediaStore.Images.Media.DATA)
                val c = context?.contentResolver?.query(
                    selectedImage!!, filePath, null, null, null)
                c!!.moveToFirst()
                val columnIndex = c.getColumnIndex(filePath[0])
                val picturePath = c.getString(columnIndex)
                c.close()
                image = BitmapFactory.decodeFile(picturePath)
                viewModel.imageStrB64 = converter.convertBitmapToBase64(image!!)
                binding.pickedImage.setImageBitmap(getResizedBitmap(image!!, 100))
                return
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }



}