package com.example.android.nadris.ui.teacherActivity.addContent.bottomSheetRecord

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentAddRecordBinding
import com.example.android.nadris.ui.teacherActivity.addContent.AddContentViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

const val REQUEST_AUDIO_PERMISSION_CODE = 1
private const val OPEN_DOCUMENT_REQUEST_CODE = 99
private const val RECORD_AUDIO_REQUEST_CODE = 100
private const val WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 200
private const val LOG_TAG = "MaintActivity"
@AndroidEntryPoint
class addRecordFragment : BottomSheetDialogFragment() {
    private val viewModel: AddContentViewModel by activityViewModels()
    private lateinit var binding: FragmentAddRecordBinding

    //recording audio related applyAttributes

    private var recorder: MediaRecorder? = null
    private var player: MediaPlayer? = null
    private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)
    private var lastProgress = 0
    private var isPlaying = false
    private val mHandler = Handler()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.fragment_add_record, container, false)
        binding = FragmentAddRecordBinding.inflate(inflater)
        binding.viewModel = viewModel
        getFilePathToSaveRecords()


        setClickListnersForButtons()
        return binding.root
    }


    override fun onStop() {
        super.onStop()

        // to stop the audio or recording when navigating out from the screen
        recorder?.release()
        recorder = null
        player?.release()
        player = null
    }

    private fun setClickListnersForButtons() {

        binding.imgBtRecord.setOnClickListener {
            prepareRecording()
            startRecording()
        }
        binding.imgBtStop.setOnClickListener {
            prepareStop()
            stopRecording()
        }
//
        binding.imgViewPlay.setOnClickListener {
            if (!isPlaying && viewModel.fileName != null) {
                isPlaying = true
                startPlaying()
            } else {
                isPlaying = false
                stopPlaying()
            }
        }
        binding.submit.setOnClickListener {
            val action = addRecordFragmentDirections.actionAddAudioFragmentToAddContentFragment()
            findNavController().navigate(action)
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
        viewModel.fileName = "${requireContext().externalCacheDir?.absolutePath}/audiorecordtest.3gp"
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
            REQUEST_AUDIO_PERMISSION_CODE -> if (grantResults.isNotEmpty()) {
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

    private fun prepareRecording() {
        binding.imgBtRecord.visibility = View.GONE
        binding.imgBtStop.visibility = View.VISIBLE
    }
    private fun prepareStop() {
        binding.imgBtRecord.visibility = View.VISIBLE
        binding.imgBtStop.visibility = View.GONE
    }
    private fun startRecording() {
        if(isPermissionForRecordingAudioGranted()){

            recorder = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setOutputFile(viewModel.fileName)
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
        binding.chronometer.base = SystemClock.elapsedRealtime()
        binding.chronometer.start()
    }

    private fun stopRecording() {
        try {
            recorder!!.stop()
            recorder!!.release()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        recorder = null
        //starting the chronometer
        binding.chronometer.stop()
        binding.chronometer.base = SystemClock.elapsedRealtime()
        //showing the play button
        Toast.makeText(requireContext(), "Recording saved successfully.", Toast.LENGTH_SHORT).show()

    }

    private fun startPlaying() {
        player = MediaPlayer().apply {
            try {
                setDataSource(viewModel.fileName)
                prepare()
                start()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }

        }
        binding.imgViewPlay.setImageResource(R.drawable.ic_pause_circle)
        binding.seekBar.progress = lastProgress
        player!!.seekTo(lastProgress)
        binding.seekBar.max = player!!.duration
        seekBarUpdate()
        binding.chronometer.start()
        player!!.setOnCompletionListener(MediaPlayer.OnCompletionListener {
            binding.imgViewPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            isPlaying = false
            binding.chronometer.stop()
            binding.chronometer.base = SystemClock.elapsedRealtime()
            player!!.seekTo(0)
        })
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (player != null && fromUser) {
                    player!!.seekTo(progress)
                    binding.chronometer.base = SystemClock.elapsedRealtime() - player!!.currentPosition
                    lastProgress = progress
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }
    private var runnable: Runnable = Runnable { seekBarUpdate() }
    private fun seekBarUpdate() {
        if (player != null) {
            val mCurrentPosition = player!!.currentPosition
            binding.seekBar.progress = mCurrentPosition
            lastProgress = mCurrentPosition
        }
        mHandler.postDelayed(runnable, 100)
    }

    private fun stopPlaying() {
        try {
            player!!.release()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        player = null
        binding.imgViewPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        binding.chronometer.stop()
    }
    }




