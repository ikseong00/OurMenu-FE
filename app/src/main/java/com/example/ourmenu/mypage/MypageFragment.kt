package com.example.ourmenu.mypage

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.ourmenu.databinding.FragmentMypageBinding
import com.example.ourmenu.databinding.MypageCurrentPasswordDialogBinding
import com.example.ourmenu.databinding.MypageImgBottomSheetDialogBinding
import com.example.ourmenu.databinding.MypageKebabBottomSheetDialogBinding
import com.example.ourmenu.databinding.MypageNewPasswordDialogBinding
import com.example.ourmenu.databinding.MypageNicknameDialogBinding
import com.example.ourmenu.landing.LandingActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class MypageFragment : Fragment() {
    lateinit var binding: FragmentMypageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)

        binding.ivMypageKebab.setOnClickListener {
            showCustomDialog()
        }

        binding.ivMypageEditProfileImg.setOnClickListener {
            showImageOptionsDialog()
        }

        return binding.root
    }

    private fun showImageOptionsDialog() {
        val dialogBinding = MypageImgBottomSheetDialogBinding.inflate(LayoutInflater.from(context))
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(dialogBinding.root)

        // Edit profile 이미지 클릭 시 다른 이미지 표시
        binding.ivMypageEditProfileBorder.visibility = View.VISIBLE
        binding.ivMypageEditProfileImgOrange.visibility = View.VISIBLE

        dialogBinding.btnMypageImgDialogAlbum.setOnClickListener {
            // TODO: 앨범에서 사진 선택 로직
        }

        dialogBinding.btnMypageImgDialogDefault.setOnClickListener {
            // TODO: 기본 이미지 적용 로직
        }

        dialogBinding.btnMypageImgDialogCancel.setOnClickListener {
            // 취소 버튼 클릭 처리
            // Edit profile 이미지 클릭 시 다른 이미지 표시
            binding.ivMypageEditProfileBorder.visibility = View.GONE
            binding.ivMypageEditProfileImgOrange.visibility = View.GONE

            bottomSheetDialog.dismiss()
        }

        // 흐린 배경 제거
        bottomSheetDialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

        bottomSheetDialog.show()
    }

    private fun showCustomDialog() {
        val dialogBinding = MypageKebabBottomSheetDialogBinding.inflate(LayoutInflater.from(context))
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(dialogBinding.root)

        dialogBinding.btnMypageKebabDialogNickname.setOnClickListener {
            bottomSheetDialog.dismiss()
            // TODO: 닉네임 변경하기 다이얼로그 표시
            showNicknameDialog()
        }

        dialogBinding.btnMypageKebabDialogPassword.setOnClickListener {
            bottomSheetDialog.dismiss()
            // TODO: 비밀번호 변경하기 다이얼로그 표시
            showCurrentPasswordDialog()
        }

        dialogBinding.btnMypageKebabDialogLogout.setOnClickListener {
            // TODO: 로그아웃 버튼 클릭 처리
            bottomSheetDialog.dismiss()

            // LandingActivity로 이동
            val intent = Intent(requireContext(), LandingActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        dialogBinding.btnMypageKebabDialogCancel.setOnClickListener {
            // 취소 버튼 클릭 처리
            bottomSheetDialog.dismiss()
        }

        // 흐린 배경 제거
        bottomSheetDialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

        bottomSheetDialog.show()
    }

    private fun showNicknameDialog() {
        val dialogBinding = MypageNicknameDialogBinding.inflate(LayoutInflater.from(context))
        val nicknameDialog =
            android.app.AlertDialog
                .Builder(requireContext())
                .setView(dialogBinding.root)
                .create()

        nicknameDialog.setOnShowListener {
            val window = nicknameDialog.window
            window?.setBackgroundDrawableResource(android.R.color.transparent)

            // TODO: util 함수 dp to px로 수정하기
            val params = window?.attributes
            params?.width = dpToPx(288)
            params?.height = WindowManager.LayoutParams.WRAP_CONTENT
            window?.attributes = params
        }

        dialogBinding.ivMypageCloseNicknameDialog.setOnClickListener {
            // 닫기 버튼 클릭 처리
            nicknameDialog.dismiss()
        }

        dialogBinding.btnMypageNicknameConfirm.setOnClickListener {
            // TODO: 확인 버튼 클릭 처리
            val newNickname = dialogBinding.etMypageNickname.text.toString()
            // 닉네임 변경 로직 추가
            nicknameDialog.dismiss()
        }

        nicknameDialog.show()
    }

    private fun showCurrentPasswordDialog() {
        val dialogBinding = MypageCurrentPasswordDialogBinding.inflate(LayoutInflater.from(context))
        val currentPasswordDialog =
            android.app.AlertDialog
                .Builder(requireContext())
                .setView(dialogBinding.root)
                .create()

        currentPasswordDialog.setOnShowListener {
            val window = currentPasswordDialog.window
            window?.setBackgroundDrawableResource(android.R.color.transparent)

            // dp를 px로 변환하여 너비를 288dp로 고정
            val params = window?.attributes
            params?.width = dpToPx(288)
            params?.height = WindowManager.LayoutParams.WRAP_CONTENT
            window?.attributes = params
        }

        dialogBinding.cbMypageCpwShowPw.setOnCheckedChangeListener { _, isChecked ->
            // 비밀번호가 보여도 font설정에 제대로 되도록 설정
            val currentTypeface = dialogBinding.etMypageCpw.typeface

            if (isChecked) {
                // 비밀번호 보이게 하기
                dialogBinding.etMypageCpw.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                // 비밀번호 숨기기
                dialogBinding.etMypageCpw.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }

            dialogBinding.etMypageCpw.typeface = currentTypeface

            // 커서를 text 제일 뒤로 옮기기
            dialogBinding.etMypageCpw.setSelection(dialogBinding.etMypageCpw.text.length)
        }

        dialogBinding.ivMypageCloseCpwDialog.setOnClickListener {
            // 닫기 버튼 클릭 처리
            currentPasswordDialog.dismiss()
        }

        dialogBinding.btnMypageCpwConfirm.setOnClickListener {
            // TODO: 확인 버튼 클릭 처리
            val currentPassword = dialogBinding.etMypageCpw.text.toString()
            // 현재 비밀번호 확인 로직 추가
            currentPasswordDialog.dismiss()
            showNewPasswordDialog()
        }

        currentPasswordDialog.show()
    }

    private fun showNewPasswordDialog() {
        val dialogBinding = MypageNewPasswordDialogBinding.inflate(LayoutInflater.from(context))
        val newPasswordDialog =
            android.app.AlertDialog
                .Builder(requireContext())
                .setView(dialogBinding.root)
                .create()

        newPasswordDialog.setOnShowListener {
            val window = newPasswordDialog.window
            window?.setBackgroundDrawableResource(android.R.color.transparent)

            // dp를 px로 변환하여 너비를 288dp로 고정
            val params = window?.attributes
            params?.width = dpToPx(288)
            params?.height = WindowManager.LayoutParams.WRAP_CONTENT
            window?.attributes = params
        }

        dialogBinding.cbMypageNpwShowPw.setOnCheckedChangeListener { _, isChecked ->
            // 비밀번호가 보여도 font설정에 제대로 되도록 설정
            val currentTypeface = dialogBinding.etMypageNpw.typeface

            if (isChecked) {
                // 비밀번호 보이게 하기
                dialogBinding.etMypageNpw.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                dialogBinding.etMypageNpwCheck.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                // 비밀번호 숨기기
                dialogBinding.etMypageNpw.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                dialogBinding.etMypageNpwCheck.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }

            dialogBinding.etMypageNpw.typeface = currentTypeface
            dialogBinding.etMypageNpwCheck.typeface = currentTypeface

            // 커서를 text 제일 뒤로 옮기기
            dialogBinding.etMypageNpw.setSelection(dialogBinding.etMypageNpw.text.length)
            dialogBinding.etMypageNpwCheck.setSelection(dialogBinding.etMypageNpwCheck.text.length)
        }

        dialogBinding.ivMypageCloseNpwDialog.setOnClickListener {
            // 닫기 버튼 클릭 처리
            newPasswordDialog.dismiss()
        }

        dialogBinding.btnMypageNpwConfirm.setOnClickListener {
            // TODO: 확인 버튼 클릭 처리
            val newPassword = dialogBinding.etMypageNpw.text.toString()
            val confirmPassword = dialogBinding.etMypageNpwCheck.text.toString()
            // 비밀번호 변경 로직 추가
            newPasswordDialog.dismiss()
        }

        newPasswordDialog.show()
    }

    private fun dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }
}
