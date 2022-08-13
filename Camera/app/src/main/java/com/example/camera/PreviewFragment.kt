package com.example.camera

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.camera.databinding.FragmentPreviewBinding
import java.io.File
import android.graphics.BitmapFactory
import androidx.navigation.fragment.navArgs

class PreviewFragment : Fragment() {

    private lateinit var binding : FragmentPreviewBinding
    private lateinit var myContext: Context
    private val args: PreviewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageName = args.imageName
        val folder = File("${myContext.getExternalFilesDir(Environment.DIRECTORY_DCIM)}")
        val imgFile = File(imageName)
        val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)

        binding.imageView.setImageBitmap(myBitmap)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        myContext = context
    }
}