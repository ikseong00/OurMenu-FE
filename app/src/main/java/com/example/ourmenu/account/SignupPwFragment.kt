package com.example.ourmenu.account

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.fonts.FontFamily
import android.graphics.fonts.FontStyle
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.addTextChangedListener
import com.example.ourmenu.R
import com.example.ourmenu.databinding.FragmentSignupPwBinding
import com.example.ourmenu.databinding.ToastCorrectBinding
import com.example.ourmenu.databinding.ToastErrorBinding

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
        var toast = object {

            fun createToast(context: Context, message: String): Toast? {
                val inflater = LayoutInflater.from(context)
                val binding: ToastErrorBinding =
                    ToastErrorBinding.inflate(inflater,container,false)
                binding.tvToastError.text = message
                binding.root.elevation = 8F

                return Toast(context).apply {
                    setGravity(Gravity.TOP or Gravity.CENTER, 0, 96.toPx())
                    duration = Toast.LENGTH_LONG
                    view = binding.root
                }
            }

            fun createToastCorrect(context: Context, message: String): Toast? {
                val inflater = LayoutInflater.from(context)
                val binding: ToastCorrectBinding =
                    ToastCorrectBinding.inflate(inflater,container,false)
                binding.tvToastCorrect.text = message
                binding.root.elevation = 8F

                return Toast(context).apply {
                    setGravity(Gravity.TOP or Gravity.CENTER, 0, 96.toPx())
                    duration = Toast.LENGTH_LONG
                    view = binding.root
                }
            }

            private fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
        }
        binding.btnSignupPw.setOnClickListener {
            if (binding.etSignupPasswordEnter.text.length >= 8 && binding.etSignupPasswordEnter.text.matches(Regex("[a-z|A-Z]+[0-9]+"))){
                if(binding.etSignupPasswordEnter.text.toString() == binding.etSignupPasswordEnterCheck.text.toString()){
                    parentFragmentManager.beginTransaction()
                        .addToBackStack("SignupPw")
                        .replace(R.id.cl_mainscreen, SignupNicknameFragment())
                        .commit()
                    toast.createToastCorrect(requireContext(),"계정 생성 완료!")?.show()

                }else{
                    toast.createToast(requireContext(),"비밀번호가 일치하지 않아요.")?.show()
                    binding.etSignupPasswordEnter.setBackgroundResource(R.drawable.edittext_bg_error)
                    binding.etSignupPasswordEnterCheck.setBackgroundResource(R.drawable.edittext_bg_error)
                }
            } else {
                toast.createToast(requireContext(),"비밀번호 조건을 다시 확인해주세요.")?.show()
                binding.etSignupPasswordEnter.setBackgroundResource(R.drawable.edittext_bg_error)

            }
        }
        var flag = true
        binding.cbSignupShowPassword.setOnClickListener {
            if (flag) {

                binding.etSignupPasswordEnter.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.etSignupPasswordEnter.inputType = InputType.TYPE_CLASS_TEXT
                binding.etSignupPasswordEnterCheck.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.etSignupPasswordEnterCheck.inputType = InputType.TYPE_CLASS_TEXT
                binding.etSignupPasswordEnter.typeface = ResourcesCompat.getFont(requireContext(),R.font.pretendard_600)
                binding.etSignupPasswordEnterCheck.typeface = ResourcesCompat.getFont(requireContext(),R.font.pretendard_600)
                flag = false
            } else {
                binding.etSignupPasswordEnter.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.etSignupPasswordEnterCheck.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.etSignupPasswordEnter.typeface = ResourcesCompat.getFont(requireContext(),R.font.pretendard_600)
                binding.etSignupPasswordEnterCheck.typeface = ResourcesCompat.getFont(requireContext(),R.font.pretendard_600)
                flag = true
            }
        }

        binding.etSignupPasswordEnter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.etSignupPasswordEnter.setBackgroundResource(R.drawable.edittext_bg_default)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etSignupPasswordEnterCheck.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.etSignupPasswordEnterCheck.setBackgroundResource(R.drawable.edittext_bg_default)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        return binding.root
    }

}
