package com.example.android.nadris.ui.studentActivity.addPosts

import android.app.Activity
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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.nadris.*
import com.example.android.nadris.databinding.FragmentAddPosBinding
import com.example.android.nadris.services.Converter
import com.example.android.nadris.util.LoadImageFromDevice
import com.example.android.nadris.util.getResizedBitmap
import dagger.hilt.android.AndroidEntryPoint
import java.io.File


@AndroidEntryPoint
class AddPostFragment : Fragment() {

    val viewModel: AddPostViewModel by viewModels()
    lateinit var binding: FragmentAddPosBinding
    private val converter: Converter by lazy {
        Converter(requireContext().applicationContext)
    }
    private var image: Bitmap? = null

     private val args:AddPostFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding =FragmentAddPosBinding.inflate(layoutInflater, container, false)

        binding.addPostViewModel = viewModel

        binding.lifecycleOwner = this

        if (viewModel.isTeacher)
            viewModel.getGrades()
        else
            viewModel.getSubjects()

//        viewModel.grades.observe(viewLifecycleOwner) { list ->
//            val adapter = ArrayAdapter(requireContext(), R.layout.item_gender_list, list.map { it.name })
//            (binding.spGrades.editText as? AutoCompleteTextView)?.setAdapter(adapter)!!
//        }
//
//        viewModel.subjects.observe(viewLifecycleOwner) { list ->
//            val adapter = ArrayAdapter(requireContext(), R.layout.item_gender_list, list.map { it.name })
//            (binding.spAddSubject.editText as? AutoCompleteTextView)?.setAdapter(adapter)!!
//        }

        viewModel.navigateBackToHomeScreen.observe(viewLifecycleOwner) { navigate ->
            if(navigate) {
                findNavController().navigate(AddPostFragmentDirections.actionAddPostFragmentToPostsFragment())
                viewModel.navigationBackToHomeScreenDone()

            }

        }

        binding.imageButton.setOnClickListener {
            LoadImageFromDevice.selectImage(requireActivity(),requireContext(),this)
        }

        binding.publishButton.setOnClickListener {

            if (binding.textViewAddQesition.text.toString().isNotEmpty() && viewModel.selectedSubject.value != null) {
                viewModel.addPost()
                viewModel.navigateBackToHomeScreen()
            } else {
                Toast.makeText(requireContext().applicationContext,
                    getString(R.string.add_post_requirments),
                    Toast.LENGTH_SHORT).show()
            }
        }

        if(args.mode.isInEditMode()){
            viewModel.selectedSubject.value = args.subject
            viewModel.question.value = args.discussionContent
            viewModel.editedDiscussionId = args.postId
            binding.publishButton.apply {
                this.setOnClickListener {

                    if (binding.textViewAddQesition.text.toString().isNotEmpty() && viewModel.selectedSubject.value != null) {
                        viewModel.updatePostAfterEdit()
                    } else {
                        Toast.makeText(requireContext().applicationContext,
                            getString(R.string.add_post_requirments),
                            Toast.LENGTH_SHORT).show()
                    }
                }
                this.text = context.getString(R.string.save_edits)
            }
            binding.imageButton.text = "edit image"

            if (args.hasImage){
                    val file = File(NadrisApplication.instance?.applicationContext?.cacheDir,
                        args.postId.toString())
                    if (file.exists()) {
                        val img = BitmapFactory.decodeFile(file.absolutePath)
                        binding.pickedImage.setImageBitmap(img!!)
                        binding.pickedImage.visibility = View.VISIBLE
                    }
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