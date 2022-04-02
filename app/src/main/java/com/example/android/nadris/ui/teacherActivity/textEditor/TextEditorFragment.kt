package com.example.android.nadris.ui.teacherActivity.textEditor

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.android.nadris.*
import com.example.android.nadris.databinding.FragmentTextEditorBinding
import com.example.android.nadris.services.Converter
import com.example.android.nadris.ui.teacherActivity.addingSections.AddingSectionsViewModel
import com.example.android.nadris.util.LoadImageFromDevice
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import jp.wasabeef.richeditor.RichEditor
import java.util.regex.Pattern


class TextEditorFragment : Fragment() {

    val viewModel: AddingSectionsViewModel by activityViewModels()

    private lateinit var converter: Converter
    private var image: Bitmap? = null
    var imageStrB64:String? = null

    var mEditor: RichEditor? = null
    private var mPreview: TextView? = null
    private var imgagebase64 = """iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4
        //8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg=="""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentTextEditorBinding.inflate(layoutInflater, container, false)


        //initialize converter
        converter = Converter(requireContext().applicationContext)

        mEditor = binding.editor
        mEditor!!.setEditorHeight(200)
        mEditor!!.setEditorFontSize(22)
        mEditor!!.setEditorFontColor(Color.RED)
        //mEditor.setEditorBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundResource(R.drawable.bg);
        //mEditor.setEditorBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundResource(R.drawable.bg);
        mEditor!!.setPadding(10, 10, 10, 10)
        //mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        //mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        mEditor!!.setPlaceholder("Insert text here...")
        //mEditor.setInputEnabled(false);

        //mEditor.setInputEnabled(false);

        binding.actionUndo.setOnClickListener { mEditor!!.undo() }

        binding.actionRedo.setOnClickListener { mEditor!!.redo() }

        binding.actionBold.setOnClickListener { mEditor!!.setBold() }

        binding.actionItalic.setOnClickListener { mEditor!!.setItalic() }

        binding.actionSubscript.setOnClickListener { mEditor!!.setSubscript() }

        binding.actionSuperscript.setOnClickListener { mEditor!!.setSuperscript() }

        binding.actionStrikethrough.setOnClickListener { mEditor!!.setStrikeThrough() }

        binding.actionUnderline.setOnClickListener { mEditor!!.setUnderline() }

        binding.actionHeading1.setOnClickListener {
            mEditor!!.setHeading(1)
        }

        binding.actionHeading2.setOnClickListener {
            mEditor!!.setHeading(2)
        }

        binding.actionHeading3.setOnClickListener {
            mEditor!!.setHeading(3)
        }

        binding.actionHeading4.setOnClickListener {
            mEditor!!.setHeading(4)
        }

        binding.actionHeading5.setOnClickListener {
            mEditor!!.setHeading(5)
        }

        binding.actionHeading6.setOnClickListener {
            mEditor!!.setHeading(6)
        }

        binding.actionTxtColor.setOnClickListener(object : View.OnClickListener {
            private var isChanged = false
            override fun onClick(v: View) {
                mEditor!!.setTextColor(if (isChanged) Color.BLACK else Color.RED)
                isChanged = !isChanged
            }
        })

        binding.actionBgColor.setOnClickListener(object : View.OnClickListener {
            private var isChanged = false
            override fun onClick(v: View) {
                mEditor!!.setTextBackgroundColor(if (isChanged) Color.TRANSPARENT else Color.YELLOW)
                isChanged = !isChanged
            }
        })

        binding.actionIndent.setOnClickListener { mEditor!!.setIndent() }

        binding.actionOutdent.setOnClickListener { mEditor!!.setOutdent() }

        binding.actionAlignLeft.setOnClickListener { mEditor!!.setAlignLeft() }

        binding.actionAlignCenter.setOnClickListener { mEditor!!.setAlignCenter() }

        binding.actionAlignRight.setOnClickListener { mEditor!!.setAlignRight() }

        binding.actionBlockquote.setOnClickListener { mEditor!!.setBlockquote() }

        binding.actionInsertBullets.setOnClickListener { v: View? -> mEditor!!.setBullets() }

        binding.actionInsertNumbers.setOnClickListener { v: View? -> mEditor!!.setNumbers() }

        binding.actionInsertImage.setOnClickListener{ v: View? ->
            LoadImageFromDevice.selectImage(requireActivity(),requireContext(),this)

        }

        binding.actionInsertYoutube.setOnClickListener {
            val inputView = inflater.inflate(R.layout.layout_input,container,false)

            MaterialAlertDialogBuilder(requireContext())
                .setMessage("Save your changes or discard them ?")
                .setPositiveButton("add"
                ) { dialogInterface, i ->
                    val videoId = getYoutubeId((inputView.findViewById(R.id.youtube_link_input) as TextInputLayout).editText?.text.toString())
                    mEditor!!.insertYoutubeVideo("https://www.youtube.com/embed/$videoId")

                }
                .setNegativeButton("cancel"
                ) { dialogInterface, i ->

                }
                .setView(inputView)
                .show()
        }

        mEditor!!.setOnTextChangeListener {
            Log.v("htmlstring",it)
            viewModel.SectionAsHtml.value = it
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
                    val imageStrB64 = converter.convertBitmapToBase64(image!!)
                    mEditor!!.insertImage("data:image/png;base64,$imageStrB64",null, 320)                } catch (e: Exception) {
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
                val imageStrB64 = converter.convertBitmapToBase64(image!!)
                mEditor!!.insertImage("data:image/png;base64,$imageStrB64",null, 320)
        return
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun getYoutubeId(url: String?): String? {
        val pattern =
            "https?:\\/\\/(?:[0-9A-Z-]+\\.)?(?:youtu\\.be\\/|youtube\\.com\\S*[^\\w\\-\\s])([\\w\\-]{11})(?=[^\\w\\-]|$)(?![?=&+%\\w]*(?:['\"][^<>]*>|<\\/a>))[?=&+%\\w]*"
        val compiledPattern = Pattern.compile(pattern,
            Pattern.CASE_INSENSITIVE)
        val matcher = compiledPattern.matcher(url)
        return if (matcher.find()) {
            matcher.group(1)
        } else null
    }

}