package com.example.android.nadris.ui.teacherActivity.addSection

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.android.nadris.databinding.FragmentAddSectionBinding
import java.io.IOException
private const val LOG_TAG = "addSectionFragment"
const val REQUEST_AUDIO_PERMISSION_CODE = 1
private const val OPEN_DOCUMENT_REQUEST_CODE = 99
private const val RECORD_AUDIO_REQUEST_CODE = 100
private const val WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 200
private const val TAG = "MaintActivity"


class addSectionFragment : Fragment() {


    // fragment related attributes
    val viewModel: AddSectionViewModel by viewModels()
    lateinit var binding:FragmentAddSectionBinding

    //pdf files related attributes
    private lateinit var renderer:PdfRenderer
    private var total_pages_num = 0
    private var current_page_index = 0


    //recording audio related applyAttributes
    private var  fileName= ""
    private var recorder: MediaRecorder? = null
    private var player: MediaPlayer? = null
    private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddSectionBinding.inflate(layoutInflater, container, false)

        getFilePathToSaveRecords()


        setClickListnersForButtons()


        return binding.root
    }



    // fragment related functions
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == OPEN_DOCUMENT_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            data?.data?.let {uri ->
                openDocument(uri)
            }
        }


    }

    override fun onStop() {
        super.onStop()

        // to stop the audio or recording when navigating out from the screen
        recorder?.release()
        recorder = null
        player?.release()
        player = null
    }

    override fun onDestroy() {
        super.onDestroy()
        //close PdfRenderer
        if(renderer !=null)
            renderer.close()
    }

    private fun setClickListnersForButtons() {
        binding.btnPick.setOnClickListener {
            openDocumentPicker()
        }
        binding.btnNext.setOnClickListener {
            showNextPage()
        }
        binding.btnPrev.setOnClickListener {
            showPreviousPage()
        }
        binding.imgBtnStartRecording.setOnClickListener {
            startRecording()
        }
        binding.imgBtnStopRecording.setOnClickListener {
            stopRecording()
        }

        binding.imgBtnPlayAudio.setOnClickListener {
            startPlaying()
        }
        binding.imgBtnStopPlaying.setOnClickListener {
            stopPlaying()
        }
    }


    // pdf related functions
    private fun openDocumentPicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            this.addCategory(Intent.CATEGORY_OPENABLE)
            this.type = "application/pdf"
        }
        startActivityForResult(intent, OPEN_DOCUMENT_REQUEST_CODE)
    }

    private fun showPage(index: Int) {
        renderer?.let {
            val page = renderer.openPage(index)
            val mBitmap = Bitmap.createBitmap(page.width,page.height, Bitmap.Config.ARGB_8888)
            page.render(mBitmap,null,null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
            binding.imgPreviewPdf.setImageBitmap(mBitmap)
            page.close();
            binding.txtPageCount.text = (index+1).toString() + "/" + total_pages_num
        }
    }

    private fun openDocument(uri: Uri) {
        try {
            val parcelFileDescriptor = activity?.baseContext?.contentResolver?.openFileDescriptor(uri!!,"r")
            renderer = PdfRenderer(parcelFileDescriptor!!)
            total_pages_num = renderer.pageCount
            current_page_index = 0
            showPage(current_page_index)
//            binding.pdfView.fromUri(uri)


        }catch (throwable:Throwable){
            Log.e(TAG,throwable.message.toString())
        }
    }

    private fun showNextPage() {
        if(current_page_index < total_pages_num-1) {
            current_page_index++
            showPage(current_page_index)
        }
    }
    
    private fun showPreviousPage() {
        if(current_page_index >0) {
            current_page_index--
            showPage(current_page_index)
        }
    }
    


    // recording and playing audio functions
    private fun isPermissionForRecordingAudioGranted(): Boolean {

        val WriteFilesToExternalStoragePermessionStatus = ContextCompat.checkSelfPermission(requireContext().applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val RecordingAudioPermessionStatus = ContextCompat.checkSelfPermission(requireContext().applicationContext, Manifest.permission.RECORD_AUDIO)
        val PERMISSION_GRANTEDStatusCode = PackageManager.PERMISSION_GRANTED

        return WriteFilesToExternalStoragePermessionStatus.equals(PERMISSION_GRANTEDStatusCode) && RecordingAudioPermessionStatus.equals(PERMISSION_GRANTEDStatusCode)
    }

    private fun getFilePathToSaveRecords(){
        // Record to the external cache directory for visibility
        fileName = "${requireContext().externalCacheDir?.absolutePath}/audiorecordtest.3gp"
    }

    private fun requestPermissionsForRecordingAudio() {
        // this method is used to request the
        // permission for audio recording and storage.
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE),
            REQUEST_AUDIO_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // this method is called when user will
        // grant the permission for audio recording.
        when (requestCode) {
            REQUEST_AUDIO_PERMISSION_CODE -> if (grantResults.size > 0) {
                val permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val permissionToStore = grantResults[1] == PackageManager.PERMISSION_GRANTED
                if (permissionToRecord && permissionToStore) {
                    Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun startRecording() {
        if(isPermissionForRecordingAudioGranted()){

            recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }

            start()
        }
        }else{
            requestPermissionsForRecordingAudio()
        }
    }

    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }

    private fun startPlaying() {
            player = MediaPlayer().apply {
                try {
                    setDataSource(fileName)
                    prepare()
                    start()
                } catch (e: IOException) {
                    Log.e(LOG_TAG, "prepare() failed")
                }
            }

    }

    private fun stopPlaying() {
        player?.release()
        player = null
    }



}