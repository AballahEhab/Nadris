package com.example.android.nadris.ui.teacherActivity.addContent

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.AddContentFragmentBinding
import com.example.android.nadris.ui.teacherActivity.addingSections.AddLessonSectionsFragmentDirections
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

const val REQUEST_AUDIO_PERMISSION_CODE = 1
private const val OPEN_DOCUMENT_REQUEST_CODE = 99

@AndroidEntryPoint
class AddContentFragment : Fragment() {
    val viewModel: AddContentViewModel by activityViewModels()
    private lateinit var binding: AddContentFragmentBinding
    private val TAG = "AddContentFragment"

    //pdf files related attributes
    private var renderer: PdfRenderer? = null
    private var total_pages_num = 0
    private var current_page_index = 0


    private var player: MediaPlayer? = null
    private var isPlaying = false
    private val mHandler = Handler()


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = AddContentFragmentBinding.inflate(layoutInflater, container, false)

        binding.viewModel = viewModel

        setClickListenersForButtons()

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == OPEN_DOCUMENT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                openDocument(uri)
            }
        }
    }

    override fun onStop() {
        super.onStop()

        // to stop recording when navigating out from the screen
        player?.release()
        player = null

    }

    override fun onDestroy() {
        super.onDestroy()
        //close PdfRenderer
        renderer?.close()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setClickListenersForButtons() {
        binding.uploadPDF.setOnClickListener {
            openDocumentPicker()
            binding.pdfViwe.visibility = View.VISIBLE
            binding.uploadPDF.visibility = View.GONE
            binding.textEditor.visibility = View.GONE
        }

        binding.btnNext.setOnClickListener {
            showNextPage()
        }

        binding.btnPrev.setOnClickListener {
            showPreviousPage()
        }

        binding.audio.setOnClickListener {
            binding.recordView.visibility = View.VISIBLE
            val action =
                AddLessonSectionsFragmentDirections.actionAddingSectionsFragmentToAddAudioFragment()
            findNavController().navigate(action)
            binding.audio.visibility = View.GONE
        }

        binding.imgViewPlay.setOnClickListener {
            if (!isPlaying && viewModel.fileName != null) {
                isPlaying = true
                startPlaying()
            } else {
                isPlaying = false
                stopPlaying()
            }
        }

        binding.video.setOnClickListener {
            binding.video.visibility = View.GONE
            val action =
                AddLessonSectionsFragmentDirections.actionAddingSectionsFragmentToAddLinkVideoFragment()
            findNavController().navigate(action)

            if (viewModel.VIDEO_ID.isNotEmpty()) {
                binding.video.visibility = View.VISIBLE
                youtubePlay()
            }
        }

        binding.textEditor.setOnClickListener {
            binding.uploadPDF.visibility = View.GONE
            val action =
                AddLessonSectionsFragmentDirections.actionAddingSectionsFragmentToTextEditorFragment()
            findNavController().navigate(action)

        }

    }

    // pdf related functions
    private fun openDocumentPicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            this.addCategory(Intent.CATEGORY_OPENABLE)
            this.type = "application/pdf"
        }
        startActivityForResult(
            intent,
            OPEN_DOCUMENT_REQUEST_CODE
        )
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun showPage(index: Int) {
        renderer?.let {
            val page = renderer!!.openPage(index)
            val mBitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
            page.render(mBitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
            binding.imgPreviewPdf.setImageBitmap(mBitmap)
            page.close();
            binding.txtPageCount.text = (index + 1).toString() + "/" + total_pages_num
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun openDocument(uri: Uri) {
        try {
            val parcelFileDescriptor =
                activity?.baseContext?.contentResolver?.openFileDescriptor(uri!!, "r")
            renderer = PdfRenderer(parcelFileDescriptor!!)
            total_pages_num = renderer!!.pageCount
            current_page_index = 0
            showPage(current_page_index)
//            binding.pdfView.fromUri(uri)


        } catch (throwable: Throwable) {
            Log.e(TAG, throwable.message.toString())
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun showNextPage() {
        if (current_page_index < total_pages_num - 1) {
            current_page_index++
            showPage(current_page_index)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun showPreviousPage() {
        if (current_page_index > 0) {
            current_page_index--
            showPage(current_page_index)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
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


    private fun startPlaying() {
        player = MediaPlayer().apply {
            try {
                setDataSource(viewModel.fileName)
                prepare()
                start()
            } catch (e: IOException) {
                Log.e(TAG, "prepare() failed")
            }

        }
        binding.imgViewPlay.setImageResource(R.drawable.ic_pause_circle)
        binding.seekBar.progress = viewModel.lastProgress
        player!!.seekTo(viewModel.lastProgress)
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
                    binding.chronometer.base =
                        SystemClock.elapsedRealtime() - player!!.currentPosition
                    viewModel.lastProgress = progress
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
            viewModel.lastProgress = mCurrentPosition
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

    private fun youtubePlay() {
        val onInitializedListener: YouTubePlayer.OnInitializedListener =
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer,
                    b: Boolean,
                ) {
                    youTubePlayer.loadVideo(viewModel.VIDEO_ID)
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult,
                ) {
                }
            }

        val youTubePlayerFragment =
            requireActivity().fragmentManager!!.findFragmentById(R.id.youTube_fr) as YouTubePlayerFragment?
        youTubePlayerFragment!!.initialize(viewModel.developerKey, onInitializedListener)
    }


}