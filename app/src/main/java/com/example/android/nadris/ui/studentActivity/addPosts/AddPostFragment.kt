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
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentAddPosBinding
import com.example.android.nadris.services.Converter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddPostFragment : Fragment() {

    val viewModel: AddPostViewModel by viewModels()
    lateinit var binding: FragmentAddPosBinding
    private lateinit var converter: Converter
    private val REQUEST_IMAGE_CAPTURE = 1
    private val PHOTO_PICKER_REQUEST_CODE = 2
    private val CAMERA_PERMISSION_REQUEST_CODE = 3
    private val STORAGE_PERMISSION_REQUEST_CODE = 4
private  var image: Bitmap?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        inflater.inflate(R.layout.fragment_add_pos, container, false)
        binding = FragmentAddPosBinding.inflate(inflater)
        binding.addPostViewModel = viewModel
        //initialize converter
        converter = Converter(requireContext().applicationContext)

        val subjects = resources.getStringArray(R.array.subject)
        val adapter = ArrayAdapter(requireContext(), R.layout.item_gender_list, subjects)
        (binding.spAddSubject.editText as? AutoCompleteTextView)?.setAdapter(adapter)!!

        binding.imageButton.setOnClickListener {
            selectImage()
        }

        binding.publishButton.setOnClickListener {
            if (binding.textViewAddQesition.text.toString().isNotEmpty()) {
                viewModel.addPost()
                it.findNavController().navigate(AddPostFragmentDirections.actionAddPostFragmentToNavigationPosts())
            } else {
                Toast.makeText(requireContext().applicationContext, "test", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(requireContext(), "Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity!!, Manifest.permission.CAMERA)
        ) {
            AlertDialog.Builder(context).setTitle("permission needed").setMessage("this needed")
                .setPositiveButton("Ok") { _, _ ->
                    ActivityCompat.requestPermissions(activity!!,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        STORAGE_PERMISSION_REQUEST_CODE)
                    selectImage()
                }
                .setNegativeButton(
                    "cancel")
                { dialog, _ -> dialog.dismiss() }.create().show()
        } else {
            ActivityCompat.requestPermissions(activity!!,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                STORAGE_PERMISSION_REQUEST_CODE)
        }
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity!!, Manifest.permission.CAMERA)
        ) {
            AlertDialog.Builder(context).setTitle("permission needed").setMessage("this needed")
                .setPositiveButton("Ok") { _, _ ->
                    ActivityCompat.requestPermissions(activity!!,
                        arrayOf(Manifest.permission.CAMERA),
                        CAMERA_PERMISSION_REQUEST_CODE)
                    selectImage()
                }
                .setNegativeButton(
                    "cancel")
                { dialog, _ -> dialog.dismiss() }.create().show()

        } else {
            ActivityCompat.requestPermissions(activity!!,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE)
        }
    }

    private fun selectImage() {
        val options = resources.getStringArray(R.array.add_Photo)
        options[0]
        activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(getString(R.string.dlg_add_photo_title))
            builder.setItems(options) { dialog, item ->
                if (options[item] == options[0]) {
                    if (ContextCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED
                    ) {
                        requestCameraPermission()
                    } else {
                        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                    }
                } else if (options[item] == options[1]) {
                    if (ContextCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED
                    ) {
                        requestStoragePermission()
                    } else {
                        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        startActivityForResult(intent, PHOTO_PICKER_REQUEST_CODE)
                    }
                } else if (options[item] == options[2]) {
                    dialog.dismiss()
                }
            }
            builder.show()
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
                    viewModel.imageStrB64= converter.convertBitmapToBase64(image!!)
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
                image= BitmapFactory.decodeFile(picturePath)
                viewModel.imageStrB64= converter.convertBitmapToBase64(image!!)
                binding.pickedImage.setImageBitmap(getResizedBitmap(image!!, 100))
                return
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun getResizedBitmap(image: Bitmap, maxSize: Int): Bitmap? {
        var width = image.width
        var height = image.height
        val bitmapRatio = width.toFloat() / height.toFloat()
        if (bitmapRatio > 1) {
            width = maxSize
            height = (width / bitmapRatio).toInt()
        } else {
            height = maxSize
            width = (height * bitmapRatio).toInt()
        }
        return Bitmap.createScaledBitmap(image, width, height, true)
    }

}