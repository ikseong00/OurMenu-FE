package com.example.ourmenu.menu

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.ourmenu.R
import com.example.ourmenu.databinding.FragmentMenuFolderBinding

class MenuFolderFragment : Fragment() {

    lateinit var binding: FragmentMenuFolderBinding

    @RequiresApi(Build.VERSION_CODES.S) // 이거 있어야 setRenderEffect 가능
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMenuFolderBinding.inflate(layoutInflater)

        // vert 버튼 클릭
        binding.ivMenuFolderVert.setOnClickListener {
            // 배경에 blur 효과 적용
            binding.clMenuFolderBlur.setRenderEffect(
                RenderEffect.createBlurEffect(7.5f, 7.5f, Shader.TileMode.CLAMP)
            )
            // 메뉴판 수정하기, 삭제하기, 취소 visible
            binding.clMenuFolderVert.visibility = View.VISIBLE
        }

        binding.btnMenuFolderCancel.setOnClickListener {
            // blur 효과 제거
            binding.clMenuFolderBlur.setRenderEffect(null)
            // 메뉴판 수정하기, 삭제하기, 취소 gone
            binding.clMenuFolderVert.visibility = View.GONE
        }



        return binding.root

    }

}
