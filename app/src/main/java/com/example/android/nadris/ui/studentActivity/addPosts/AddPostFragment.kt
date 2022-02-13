package com.example.android.nadris

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.nadris.databinding.AddPostFragmentBinding
import com.example.android.nadris.ui.studentActivity.addPosts.AddPostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class addPostFragment : Fragment() {

//    companion object {
//        fun newInstance() = addPostFragment()
//    }

    val viewModel: AddPostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
//        viewModel = ViewModelProvider(this).get(AddPostViewModel::class.java)
        inflater.inflate(R.layout.add_post_fragment, container, false)
        val bindigin =AddPostFragmentBinding.inflate(inflater)
        bindigin.addPostViewModel = viewModel
        val subjects = resources.getStringArray(R.array.subject)
        val adapter= ArrayAdapter(requireContext(), R.layout.list_item, subjects)
        (bindigin.spAddSubject.editText as? AutoCompleteTextView)?.setAdapter(adapter)!!
        bindigin.imageButton.setOnClickListener{

            pickImageFromGallery()

 }
        bindigin.publicButton.setOnClickListener {
           var str:String=""+viewModel.question.value+"\n"+viewModel.subjects.value+"\n"+viewModel.image;
          Toast.makeText(requireContext(),str,Toast.LENGTH_LONG).show()
       }

        return bindigin.root}


     fun pickImageFromGallery() {
         val intent = Intent(Intent.ACTION_PICK)
       intent.type = "image/*"
         startActivityForResult(intent, viewModel.IMAGE_REQUEST_CODE) }

    // edit to imageview

    // override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
     //   super.onActivityResult(requestCode, resultCode, data)
      // if (requestCode == viewModel.IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
       //     binding.imageView.setImageURI(data?.data)
       // }


    /*   take photo from camera
    to do
     */

    //private fun hasReadStoragePerm():Boolean{
     //   return EasyPermissions.hasPermissions(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
   // }
   // private fun readStorageTask(){
     //   if (hasReadStoragePerm()){


       //     pickImageFromGallery()
       // }else{
         //   EasyPermissions.requestPermissions(
          //      requireActivity(),
            //    getString(R.string.storage_permission_text),
             //   viewModel.READ_STORAGE_PERM,
              //  Manifest.permission.READ_EXTERNAL_STORAGE
          //  )
        //}
    //}
   // private fun pickImageFromGallery(){
     //   var intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
     //   if (intent.resolveActivity(requireActivity().packageManager) != null){
      //      startActivityForResult(intent,viewModel.IMAGE_REQUEST_CODE)
     //   }
   // }
  //  fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
     //  if (EasyPermissions.somePermissionPermanentlyDenied(requireActivity(),perms)){
       //     AppSettingsDialog.Builder(requireActivity()).build().show()
     //   }
   // }
    // fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

   // }

     //fun onRationaleDenied(requestCode: Int) {

   // }

     //fun onRationaleAccepted(requestCode: Int) {

   // }

}