package com.example.ourmenu.menu.menuFolder.post

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.ourmenu.R
import com.example.ourmenu.data.DummyMenuData
import com.example.ourmenu.data.menuFolder.request.MenuFolderRequest
import com.example.ourmenu.data.menuFolder.response.MenuFolderResponse
import com.example.ourmenu.databinding.FragmentPostMenuFolderBinding
import com.example.ourmenu.menu.menuFolder.post.adapter.PostMenuFolderRVAdapter
import com.example.ourmenu.retrofit.RetrofitObject
import com.example.ourmenu.retrofit.service.MenuFolderService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class PostMenuFolderFragment : Fragment() {

    lateinit var binding: FragmentPostMenuFolderBinding
    var dummyItems = ArrayList<DummyMenuData>()
    private val retrofit = RetrofitObject.retrofit
    private val service = retrofit.create(MenuFolderService::class.java)

    // Generic 활용하기 위해 선언한 함수
    // TODO Util 로 빼는 방법 고안
    private inline fun <reified T> getTypeOf(): Class<T> {
        return T::class.java
    }

    private var imageUri: Uri? = null

    // 갤러리 open
    private val galleryPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {

                val intent = Intent(Intent.ACTION_PICK)
                intent.setDataAndType(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    "image/*"
                )
                pickImageLauncher.launch(intent)
            } else {
            }
        }

    private val pickImageLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            Log.d("res", result.resultCode.toString())
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data

                data?.data?.let {
                    imageUri = it
                    binding.ivPmfImage.setImageURI(imageUri)
                }
            }
        }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostMenuFolderBinding.inflate(layoutInflater)

        imageUri?.let {
            binding.ivPmfImage.setImageURI(imageUri)
        }

        initDummy()
        checkFilled()
        initListener()
        initRV()
        onSaveInstanceState(Bundle())

        return binding.root
    }

    private fun initRV() {
        binding.rvPmfMenu.adapter = PostMenuFolderRVAdapter(dummyItems)
    }


    private fun initDummy() {
        // TODO Util 로 빼기
        // 안드로이드 버전에 따라 쓰는 함수가 다름
        dummyItems.addAll(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getSerializable("checkedItems", getTypeOf<ArrayList<DummyMenuData>>())
                    ?: arrayListOf()
            } else {
                arguments?.getSerializable("checkedItems") as ArrayList<DummyMenuData>
                    ?: arrayListOf()
            }  // 제네릭으로 * 을 줘야 getSerializable 가능
        )

    }

    private fun checkFilled() {
        // arguments 가 null 이 아니면 활성화, null 이면 비활성화
        binding.btnPmfOk.isEnabled = arguments != null
    }

    private fun initListener() {
        // 뒤로가기
        binding.ivPmfBack.setOnClickListener {
            requireActivity().finish()
        }

        // 이미지 추가하기
        binding.ivPmfCamera.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                galleryPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
            else
                galleryPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        // TODO 아이콘 추가하기
        binding.clPmfAddIcon.setOnClickListener {

        }

        // 메뉴 가져오기 화면 이동
        binding.btnPmfGetMenu.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.post_menu_folder_frm, PostMenuFolderGetFragment(this))
                .addToBackStack("PostMenuFolderFragment")
                .commitAllowingStateLoss()
        }

        // 확인
        binding.btnPmfOk.setOnClickListener {
            // TODO API 추가하기
            postMenuFolder()

            requireActivity().finish()
        }

    }

    private fun postMenuFolder() {
        service.postMenuFolder(MenuFolderRequest(",1", "1", "1")).enqueue(
            object : Callback<MenuFolderResponse> {
                override fun onResponse(
                    call: Call<MenuFolderResponse>,
                    response: Response<MenuFolderResponse>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        val postedMenuFolder = result?.response
                        postedMenuFolder?.let {
                            Log.d("postedMenuFolder", postedMenuFolder.toString())
                        }
                    }
                }

                override fun onFailure(call: Call<MenuFolderResponse>, t: Throwable) {
                    Log.d("postMenuFolder", t.message.toString())
                }

            })
    }
}
