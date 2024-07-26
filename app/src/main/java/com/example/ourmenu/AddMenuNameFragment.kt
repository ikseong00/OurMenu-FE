package com.example.ourmenu

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ourmenu.data.AddMenuImageData
import com.example.ourmenu.databinding.FragmentAddMenuNameBinding

class AddMenuNameFragment : Fragment() {

    lateinit var binding: FragmentAddMenuNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddMenuNameBinding.inflate(inflater, container, false)
        binding.btnAddMenuNameNext.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack("MenuAddNameFragment")
                .replace(R.id.cl_mainscreen, AddMenuTagFragment()) //id 변경해야됨
                .commit()
        }

        var addMenuImageItemList = arrayListOf<AddMenuImageData>(AddMenuImageData("", "initial"))
        var addMenuImageAdapter = AddMenuImageAdapter(addMenuImageItemList)
        addMenuImageAdapter.imageListener = object : AddMenuImageAdapter.OnImageClickListener {
            override fun onImageClick(imageView: ImageView) {
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        android.Manifest.permission.READ_MEDIA_IMAGES
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                        if (result.resultCode == RESULT_OK) {
                            val data: Intent? = result.data
                            data?.data?.let {
                                imageView.setImageURI(it)
                            }
                        }
                    }.launch(gallery)
                } else {
                    registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                        if (isGranted) {
                            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                                if (result.resultCode == RESULT_OK) {
                                    val data: Intent? = result.data
                                    data?.data?.let {
                                        imageView.setImageURI(it)
                                    }
                                }
                            }.launch(gallery)
                        }
                    }.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
                }
            }

            override fun onClick(v: View?) {
            }

        }
        binding.rvAddMenuNameMenuimage.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.HORIZONTAL, false
        )
        binding.rvAddMenuNameMenuimage.adapter = addMenuImageAdapter

        return binding.root
    }
}
