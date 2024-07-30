package com.example.ourmenu.menu.menuFolder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ourmenu.databinding.FragmentPostMenuFolderBinding

class PostMenuFolderFragment : Fragment() {

    lateinit var binding: FragmentPostMenuFolderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostMenuFolderBinding.inflate(layoutInflater)

        initListener()




        return binding.root
    }

    private fun initListener() {
        // 뒤로가기
        binding.ivPmfBack.setOnClickListener {
            requireActivity().finish()
        }

        // 이미지 추가하기
        binding.ivPmfCamera.setOnClickListener {

        }

        // TODO 아이콘 추가하기
        binding.

    }
}
