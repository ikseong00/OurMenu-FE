package com.example.ourmenu.account

import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ourmenu.R
import com.example.ourmenu.databinding.FragmentSignupPwBinding

class SignupPwFragment : Fragment() {
    lateinit var binding: FragmentSignupPwBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupPwBinding.inflate(inflater, container, false)
        binding.btnSignupPw.setOnClickListener {
            if (binding.etSignupPasswordEnter.text.length >= 8 && binding.etSignupPasswordEnter.text.matches(Regex("[0-9||a-z|A-Z| ]*"))) {

            } else {

            }
            parentFragmentManager.beginTransaction()
                .addToBackStack("SignupPw")
                .replace(R.id.cl_mainscreen, SignupNameFragment())
                .commit()
        }
        var flag = true
        binding.cbSignupShowPassword.setOnClickListener {
            if (flag) {
                binding.etSignupPasswordEnter.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.etSignupPasswordEnter.inputType = InputType.TYPE_CLASS_TEXT
                flag = false
            } else {
                binding.etSignupPasswordEnter.transformationMethod = PasswordTransformationMethod.getInstance()
                flag = true
            }
        }
        return binding.root
    }

}
