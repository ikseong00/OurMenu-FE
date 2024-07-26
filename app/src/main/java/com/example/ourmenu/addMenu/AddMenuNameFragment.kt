package com.example.ourmenu.addMenu

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ourmenu.R
import com.example.ourmenu.addMenu.adapter.AddMenuImageAdapter
import com.example.ourmenu.data.AddMenuImageData
import com.example.ourmenu.databinding.FragmentAddMenuNameBinding
import kotlinx.coroutines.launch


class AddMenuNameFragment : Fragment() {

    lateinit var binding: FragmentAddMenuNameBinding
    lateinit var imageUri:Uri
    lateinit var imageResult:ActivityResultLauncher<Intent>
    lateinit var imagePermission:ActivityResultLauncher<String>
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
    private fun openGallery(){
        val gallery = Intent()
        gallery.setAction(Intent.ACTION_PICK)
        gallery.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI,"image/*")
        imageResult.launch(gallery)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            data?.data?.let {
                imageUri = it
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                data?.data?.let {
                    imageUri = it
                }
            }
        }
        imagePermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                openGallery()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddMenuNameBinding.inflate(inflater, container, false)
        binding.btnAddMenuNameNext.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack("MenuAddNameFragment")
                .replace(R.id.cl_add_menu_main, AddMenuTagFragment()) //id 변경해야됨
                .commit()
        }

        var addMenuImageItemList = arrayListOf<AddMenuImageData>(AddMenuImageData(null,"initial"))
        var addMenuImageAdapter = AddMenuImageAdapter(addMenuImageItemList)

        //맨 뒤에 있는 이미지 누르면 갤러리 호출하도록 리스너 설정
        binding.flAddMenuAddImage.setOnClickListener {
            openGallery()
            addMenuImageItemList.add(AddMenuImageData(imageUri,"menuImage"))
        }
        addMenuImageAdapter.imageListener = object : AddMenuImageAdapter.OnImageClickListener {
            override fun onImageClick(imageView: ImageView) {
                lifecycleScope.launch { openGallery() }
                addMenuImageItemList.add(AddMenuImageData(imageUri,"menuImage"))
//                if (ContextCompat.checkSelfPermission(
//                        requireActivity(),
//                        android.Manifest.permission.READ_EXTERNAL_STORAGE
//                    ) == PackageManager.PERMISSION_GRANTED
//                ) {
//                    openGallery()
//                    addMenuImageItemList.add(AddMenuImageData(imageUri,"menuImage"))
//                } else {
//                    //권한 허용되지 않은 상태이면 권한 요청 후 갤러리 불러오기
//                    imagePermission.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
//                }
            }

            override fun onClick(v: View?) {
            }

        }
        binding.rvAddMenuNameMenuImage.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.HORIZONTAL, false
        )
        binding.rvAddMenuNameMenuImage.adapter = addMenuImageAdapter

        return binding.root
    }

}
