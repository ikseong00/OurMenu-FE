package com.example.ourmenu.menu.menuFolder.post

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ourmenu.R
import com.example.ourmenu.databinding.FragmentPostMenuFolderBinding

class PostMenuFolderFragment : Fragment() {

    lateinit var binding: FragmentPostMenuFolderBinding
//    private lateinit var dummyItems: ArrayList<*> // 제네릭으로 * 을 줘야 getSerializable 가능


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostMenuFolderBinding.inflate(layoutInflater)


        checkFilled()
        initListener()


        return binding.root
    }


    private fun initDummy() {
        // TODO Util 로 빼기
        val dummyItems = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable("data", ArrayList::class.java)!!
        } else {
            arguments?.getSerializable("data") as ArrayList<*>
        }

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
                .replace(R.id.post_menu_folder_frm, PostMenuFolderGetFragment())
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
