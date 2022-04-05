package com.example.android.nadris.util

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.android.nadris.*

object LoadImageFromDevice {

      private fun requestStoragePermission(activity: FragmentActivity, context:Context,fragment:Fragment) {
         if (ActivityCompat.shouldShowRequestPermissionRationale(
                 activity, Manifest.permission.READ_EXTERNAL_STORAGE)
         ) {
             AlertDialog.Builder(context).setTitle(fragment.getString(R.string.permission_needed))
                 .setMessage(fragment.getString(R.string.storage_permission_request))
                 .setPositiveButton(fragment.getString(R.string.confirm)) { _, _ ->
                     ActivityCompat.requestPermissions(activity,
                         arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                         STORAGE_PERMISSION_REQUEST_CODE)
                     selectImage(activity, context,fragment)
                 }
                 .setNegativeButton(
                     fragment.getString(R.string.cancel))
                 { dialog, _ -> dialog.dismiss() }.create().show()
         } else {
             ActivityCompat.requestPermissions(activity,
                 arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                 STORAGE_PERMISSION_REQUEST_CODE)
         }
     }

      private fun requestCameraPermission(activity: FragmentActivity, context:Context,fragment:Fragment) {
         if (ActivityCompat.shouldShowRequestPermissionRationale(
                 activity, Manifest.permission.CAMERA)
         ) {
             AlertDialog.Builder(context).setTitle(fragment.getString(R.string.permission_needed))
                 .setMessage(fragment.getString(R.string.camera_permission_request))
                 .setPositiveButton(fragment.getString(R.string.confirm)) { _, _ ->
                     ActivityCompat.requestPermissions(activity,
                         arrayOf(Manifest.permission.CAMERA),
                         CAMERA_PERMISSION_REQUEST_CODE)
                     selectImage(activity, context,fragment)
                 }
                 .setNegativeButton(
                     fragment.getString(R.string.cancel))
                 { dialog, _ -> dialog.dismiss() }.create().show()

         } else {
             ActivityCompat.requestPermissions(activity,
                 arrayOf(Manifest.permission.CAMERA),
                 CAMERA_PERMISSION_REQUEST_CODE)
         }
     }

      fun selectImage(activity: FragmentActivity, context:Context,fragment:Fragment) {
         val options = fragment.resources.getStringArray(R.array.add_Photo)
         options[0]
          activity.let {
              val builder = AlertDialog.Builder(it)
              builder.setTitle(fragment.getString(R.string.dlg_add_photo_title))
              builder.setItems(options) { dialog, item ->
                  if (options[item] == options[0]) {
                      if (ContextCompat.checkSelfPermission(
                              fragment.requireContext(),
                              Manifest.permission.CAMERA)
                          != PackageManager.PERMISSION_GRANTED
                      ) {
                          requestCameraPermission(activity, context,fragment)
                      } else {
                          val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                          fragment.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                      }
                  } else if (options[item] == options[1]) {
                      if (ContextCompat.checkSelfPermission(
                              fragment.requireContext(),
                              Manifest.permission.READ_EXTERNAL_STORAGE)
                          != PackageManager.PERMISSION_GRANTED
                      ) {
                          requestStoragePermission(activity, context,fragment)
                      } else {
                          val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                          fragment.startActivityForResult(intent, PHOTO_PICKER_REQUEST_CODE)
                      }
                  } else if (options[item] == options[2]) {
                      dialog.dismiss()
                  }
              }
              builder.show()
          }
     }

}