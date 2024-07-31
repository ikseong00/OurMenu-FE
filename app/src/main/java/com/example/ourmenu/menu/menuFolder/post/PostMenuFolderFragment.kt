package com.example.ourmenu.menu.menuFolder.post

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.ourmenu.R
import com.example.ourmenu.data.DummyMenuData
import com.example.ourmenu.databinding.FragmentPostMenuFolderBinding
import com.example.ourmenu.menu.menuFolder.post.adapter.PostMenuFolderRVAdapter


class PostMenuFolderFragment : Fragment() {

    lateinit var binding: FragmentPostMenuFolderBinding
    var dummyItems = ArrayList<DummyMenuData>()

    // Generic 활용하기 위해 선언한 함수
    // TODO Util 로 빼는 방법 고안
    private inline fun <reified T> getTypeOf(): Class<T> {
        return T::class.java
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostMenuFolderBinding.inflate(layoutInflater)

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

        // TODO 이미지 추가하기
        binding.ivPmfCamera.setOnClickListener {

        }

        // TODO 아이콘 추가하기
        binding.clPmfAddIcon.setOnClickListener {

        }

        // 메뉴 가져오기
        binding.btnPmfGetMenu.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.post_menu_folder_frm, PostMenuFolderGetFragment(this))
                .addToBackStack("PostMenuFolderFragment")
                .commitAllowingStateLoss()
        }

        // 확인
        binding.btnPmfOk.setOnClickListener {
            // TODO API 추가하기

            requireActivity().finish()
        }

    }
}
