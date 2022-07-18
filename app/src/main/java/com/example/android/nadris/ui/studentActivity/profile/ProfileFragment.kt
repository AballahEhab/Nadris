package com.example.android.nadris.ui.studentActivity.profile

import android.app.Activity
import android.content.DialogInterface
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.*
import com.example.android.nadris.databinding.FragmentProfileBinding
import com.example.android.nadris.services.Converter
import com.example.android.nadris.ui.SplashActivity
import com.example.android.nadris.ui.studentActivity.posts.CustomAdapter
import com.example.android.nadris.ui.studentActivity.posts.PostPageViewModel
import com.example.android.nadris.util.LoadImageFromDevice
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    val viewModel: ProfileViewModel by viewModels()
    val postsViewModel: PostPageViewModel by viewModels()
    private var image: Bitmap? = null
    private lateinit var converter: Converter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.fragment_profile, container, false)
        initialize()

        binding.viewmodel = viewModel

        binding.lifecycleOwner = this

        viewModel.getProfileInfo_from_api()

        viewModel.getLastActivity()

        binding.rvPostsProfile.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        binding.imagSetting.setOnClickListener {
            this.findNavController()
                .navigate(ProfileFragmentDirections.actionPrivateProfileFragmentToSettingsFragment())
        }

        subscribeToObservers()
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
                    viewModel.imgProfile.value = converter.convertBitmapToBase64(image!!)
                    viewModel.uploadPhoto()

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
                viewModel.imgProfile.value = converter.convertBitmapToBase64(image!!)
                viewModel.uploadPhoto()
                return
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun initialize(){
        binding = FragmentProfileBinding.inflate(layoutInflater)

        converter = Converter(requireContext().applicationContext)

    }

    private fun showImageOptionsDialog() {

        val dialogBuilder= MaterialAlertDialogBuilder(requireContext())
        var options :Array<out String>
        lateinit var onOptionsClick: (DialogInterface?,Int)-> Unit

        if(viewModel.imgProfile.value == null) {
            options = resources.getStringArray(R.array.null_image_click_options)
            onOptionsClick = { dialog, item ->
                when (options[item]) {
                    options[0] -> LoadImageFromDevice.takePhoto(requireActivity(), requireContext(), this)
                    options[1] -> LoadImageFromDevice.uploadPhoto(requireActivity(), requireContext(), this)
                }
                }
        }else{
            options = resources.getStringArray(R.array.image_click_options)
            onOptionsClick = { dialog, item ->
                when (options[item]) {
                    options[0] -> navigateToPreviewImageFragment()
                    options[1] -> LoadImageFromDevice.takePhoto(requireActivity(),
                        requireContext(),
                        this)
                    options[2] -> LoadImageFromDevice.uploadPhoto(requireActivity(),
                        requireContext(),
                        this)
                    options[3] -> viewModel.removePhoto()

                }
            }
        }
        dialogBuilder.setItems(options,onOptionsClick).show()
    }

    private fun navigateToPreviewImageFragment() {
        this.findNavController()
            .navigate(ProfileFragmentDirections.actionPrivateProfileFragmentToPreviewProfilePicFragment(viewModel.imgProfile.value))
    }

    private fun subscribeToObservers() {
        viewModel.navigateToLoginPage.observe(viewLifecycleOwner) {
            if(it){
                requireActivity().startActivity(Intent(requireContext(), SplashActivity::class.java))
                NadrisApplication.currentDatabaseUser = null
            }
        }

        viewModel.profileImageClicked.observe(viewLifecycleOwner) {
            if(it){
                showImageOptionsDialog()
                viewModel.profileImageClickDone()
            }
        }

        viewModel.postsProfileList.observe(viewLifecycleOwner) {
            val adapter = CustomAdapter(postsViewModel)
            binding.rvPostsProfile.adapter = adapter
//            adapter.differ.submitList(it.toList())
            Log.i("length", it.size.toString())
        }


        viewModel.imgProfile.observe(viewLifecycleOwner) { profileImgStr64 ->
            if (profileImgStr64 == null) {
                binding.imgProfile.setImageResource(R.drawable.ic_user)
            } else {
                val img = converter.convertFromBase64ToBitmap(profileImgStr64,"profile_photo")

                binding.imgProfile.setImageBitmap(img!!)

            }
        }
    }


}