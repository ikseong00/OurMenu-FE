package com.example.ourmenu.menu.menuFolder

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.RenderEffect
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ourmenu.R
import com.example.ourmenu.data.HomeMenuData
import com.example.ourmenu.data.menuFolder.data.MenuData
import com.example.ourmenu.data.menuFolder.request.MenuFolderRequest
import com.example.ourmenu.data.menuFolder.response.MenuFolderResponse
import com.example.ourmenu.data.menuFolder.response.MenuResponseArray
import com.example.ourmenu.databinding.FragmentMenuFolderDetailBinding
import com.example.ourmenu.menu.adapter.MenuFolderDetailRVAdapter
import com.example.ourmenu.retrofit.RetrofitObject
import com.example.ourmenu.retrofit.service.MenuFolderService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuFolderDetailFragment : Fragment() {
    lateinit var binding: FragmentMenuFolderDetailBinding
    lateinit var menuItems: ArrayList<MenuData>
    private val retrofit = RetrofitObject.retrofit
    private val service = retrofit.create(MenuFolderService::class.java)

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
                    binding.ivMenuFolderMainImage.setImageURI(imageUri)
                }
            }
        }


    @RequiresApi(Build.VERSION_CODES.S) // 이거 있어야 setRenderEffect 가능
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMenuFolderDetailBinding.inflate(layoutInflater)

        initItemClickListener()
        initVertOnClickListener()
        getMenuItems()
        initRV()
        // 수정화면이면 함수 사용, 아니면 그냥 실행

        arguments?.getBoolean("isEdit")?.let {
            editClicked()
        }

        return binding.root
    }

    private fun getMenuItems() {
        service.getMenus().enqueue(object : Callback<MenuResponseArray> {
            override fun onResponse(call: Call<MenuResponseArray>, response: Response<MenuResponseArray>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    val menuData = result?.response
                    menuData?.let {
                        menuItems = menuData
                    }
                }
            }

            override fun onFailure(call: Call<MenuResponseArray>, t: Throwable) {
                Log.d("menuFolders", t.message.toString())
            }

        })
    }

    private fun initItemClickListener() {
        // TODO 뒤로가기 설정
        binding.ivMenuFolderArrowLeft.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun initRV() {
        val dummyItems = ArrayList<HomeMenuData>()
        for (i in 1..9) {
            dummyItems.add(
                HomeMenuData(i.toString(), i.toString(), "store3"),
            )
        }

        binding.rvMenuFolderMenuList.adapter = MenuFolderDetailRVAdapter(dummyItems)
        binding.rvMenuFolderMenuList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun editClicked() {
        // blur 효과 제거
        binding.clMenuFolderBlur.setRenderEffect(null)
        // 메뉴판 수정하기, 삭제하기, 취소 gone
        binding.clMenuFolderVert.visibility = View.GONE

        // 상단 이미지 blur 효과 적용
        binding.ivMenuFolderMainImage.setRenderEffect(
            RenderEffect.createBlurEffect(2f, 2f, Shader.TileMode.CLAMP),
        )
        // vert 버튼 gone
        binding.ivMenuFolderVert.visibility = View.GONE

        // 카메라 , textView visible
        binding.llMenuFolderEdit.visibility = View.VISIBLE

        // edittext enabled, drawable 적용
        with(binding.etMenuFolderMainName) {
            isEnabled = true
            setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_pen, 0, 0, 0)
        }

        // 메뉴 추가하기 버튼 gone
        binding.btnMenuFolderAddMenu.visibility = View.GONE

        // 확인 버튼 visible
        binding.btnMenuFolderEditOk.visibility = View.VISIBLE
    }

    @RequiresApi(Build.VERSION_CODES.S) // 이거 있어야 setRenderEffect 가능
    private fun initVertOnClickListener() {
        /* vert 버튼 클릭
         * 배경에 blur 적용, 하단 뷰 visible */
        binding.ivMenuFolderVert.setOnClickListener {
            // 배경에 blur 효과 적용
            binding.clMenuFolderBlur.setRenderEffect(
                RenderEffect.createBlurEffect(7.5f, 7.5f, Shader.TileMode.CLAMP),
            )
            // 메뉴판 수정하기, 삭제하기, 취소 visible
            binding.clMenuFolderVert.visibility = View.VISIBLE
        }

        /* 메뉴판 수정하기 클릭
         * blur 효과 제거, 하단 뷰 gone
         * 상단 이미지 blur 적용, vert 버튼 gone
         * 카메라 layout visible
         * edittext 설정 */
        binding.btnMenuFolderEdit.setOnClickListener {
            editClicked()
        }

        /* 삭제하기 클릭
         * blur 효과 제거, 하단 뷰 gone
         * TODO 삭제하기 API */
        binding.btnMenuFolderDelete.setOnClickListener {
            // blur 효과 제거
            binding.clMenuFolderBlur.setRenderEffect(null)
            // 메뉴판 수정하기, 삭제하기, 취소 gone
            binding.clMenuFolderVert.visibility = View.GONE

            // TODO 삭제버튼 클릭 API 구현
        }

        /* 취소 버튼 클릭
         * blur 효과 제거, 하단 뷰 gone */
        binding.btnMenuFolderCancel.setOnClickListener {
            // blur 효과 제거
            binding.clMenuFolderBlur.setRenderEffect(null)
            // 메뉴판 수정하기, 삭제하기, 취소 gone
            binding.clMenuFolderVert.visibility = View.GONE
        }

        /* 확인 클릭
         * 상단 이미지 blur 원상 복구, vert 버튼 visible
         * edittext 원상 복구
         * TODO 확인 버튼 클릭 API 구현 */
        binding.btnMenuFolderEditOk.setOnClickListener {
            // 상단 이미지 blur 효과 적용
            binding.ivMenuFolderMainImage.setRenderEffect(null)
            // vert 버튼 visible
            binding.ivMenuFolderVert.visibility = View.VISIBLE
            // 카메라 , textView visible
            binding.llMenuFolderEdit.visibility = View.GONE
            // edittext 원상 복구
            with(binding.etMenuFolderMainName) {
                isEnabled = false
                setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            }
            // 확인 버튼 gone
            binding.btnMenuFolderEditOk.visibility = View.GONE
            // 메뉴 추가하기 버튼 visible
            binding.btnMenuFolderAddMenu.visibility = View.VISIBLE

            // 메뉴판 수정하기 API
            patchMenuFolder()

        }
    }

    private fun patchMenuFolder() {
        val id = arguments?.getInt("menuFolderId")
        val requestBody = MenuFolderRequest(
            menuFolderIcon = "",
            menuFolderTitle = binding.etMenuFolderMainName.text.toString(),
            menuFolderImgUrl = imageUri.toString()
        )
        service.patchMenuFolder(id!!, requestBody).enqueue(object : Callback<MenuFolderResponse> {
            override fun onResponse(call: Call<MenuFolderResponse>, response: Response<MenuFolderResponse>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    val menuFolder = result?.response
                    menuFolder?.let {
                        Log.d("menuFolder", menuFolder.toString())
                    }
                }
            }

            override fun onFailure(call: Call<MenuFolderResponse>, t: Throwable) {
                Log.d("patchMenuFolder", t.message.toString())

            }

        })
    }
}
