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

        initVertOnClickListener()

        binding = FragmentMenuFolderBinding.inflate(layoutInflater)






        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.S) // 이거 있어야 setRenderEffect 가능
    private fun initVertOnClickListener() {

        /* vert 버튼 클릭
        * 배경에 blur 적용, 하단 뷰 visible */
        binding.ivMenuFolderVert.setOnClickListener {
            // 배경에 blur 효과 적용
            binding.clMenuFolderBlur.setRenderEffect(
                RenderEffect.createBlurEffect(7.5f, 7.5f, Shader.TileMode.CLAMP)
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
            // blur 효과 제거
            binding.clMenuFolderBlur.setRenderEffect(null)
            // 메뉴판 수정하기, 삭제하기, 취소 gone
            binding.clMenuFolderVert.visibility = View.GONE

            // 상단 이미지 blur 효과 적용
            binding.ivMenuFolderMainImage.setRenderEffect(
                RenderEffect.createBlurEffect(2f, 2f, Shader.TileMode.CLAMP)
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
            // TODO 확인 버튼 클릭 API 구현

        }

    }

}
