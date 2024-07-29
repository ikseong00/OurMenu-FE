package com.example.ourmenu.account

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
                if(binding.etSignupPasswordEnter.text.toString() == binding.etSignupPasswordEnterRepeat.text.toString()){
                    parentFragmentManager.beginTransaction()
                        .addToBackStack("SignupPw")
                        .replace(R.id.cl_mainscreen, SignupNameFragment())
                        .commit()
                    toast.createToastCorrect(requireContext(),"계정 생성 완료!")?.show()

                }else{
                    toast.createToast(requireContext(),"비밀번호가 일치하지 않아요.")?.show()
                    binding.etSignupPasswordEnter.setBackgroundResource(R.drawable.edittext_bg_error)
                    binding.etSignupPasswordEnterRepeat.setBackgroundResource(R.drawable.edittext_bg_error)
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
                binding.etSignupPasswordEnterRepeat.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.etSignupPasswordEnterRepeat.inputType = InputType.TYPE_CLASS_TEXT
                flag = false
            } else {
                binding.etSignupPasswordEnter.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.etSignupPasswordEnterRepeat.transformationMethod = PasswordTransformationMethod.getInstance()
                flag = true
            }
        }
        return binding.root
    }

}
