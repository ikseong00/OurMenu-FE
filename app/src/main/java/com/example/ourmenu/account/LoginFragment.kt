package com.example.ourmenu.account

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ourmenu.MainActivity
import com.example.ourmenu.R
import com.example.ourmenu.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btnLoginSignup.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.cl_mainscreen, SignupEmailFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }

        binding.btnLoginLogin.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        binding.cbLoginShowPassword.setOnCheckedChangeListener { _, isChecked ->
            // 비밀번호가 보여도 font설정에 제대로 되도록 설정
            val currentTypeface = binding.etLoginPassword.typeface

            if (isChecked) {
                // 비밀번호 보이게 하기
                binding.etLoginPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                // 비밀번호 숨기기
                binding.etLoginPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }

            binding.etLoginPassword.typeface = currentTypeface

            // 커서를 text 제일 뒤로 옮기기
            binding.etLoginPassword.setSelection(binding.etLoginPassword.text.length)
        }

        return binding.root
    }
}
