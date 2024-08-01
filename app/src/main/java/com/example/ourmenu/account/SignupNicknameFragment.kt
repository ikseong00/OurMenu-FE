package com.example.ourmenu.account

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ourmenu.MainActivity
import com.example.ourmenu.R
import com.example.ourmenu.databinding.FragmentSignupNicknameBinding
import com.example.ourmenu.databinding.ToastErrorBinding

class SignupNicknameFragment : Fragment() {
    lateinit var binding: FragmentSignupNicknameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSignupNicknameBinding.inflate(inflater, container, false)
        binding.btnSignupNickname.setOnClickListener {
            if (binding.etSignupNickname.text.length <= 10 && (binding.etSignupNickname.text.isNotEmpty()))
                {
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                } else {
                binding.etSignupNickname.setBackgroundResource(R.drawable.edittext_bg_error)
                var toast =
                    object {
                        fun createToast(
                            context: Context,
                            message: String,
                        ): Toast? {
                            val inflater = LayoutInflater.from(context)
                            val binding: ToastErrorBinding =
                                ToastErrorBinding.inflate(inflater, container, false)
                            binding.tvToastError.text = message
                            binding.root.elevation = 8F

                            return Toast(context).apply {
                                setGravity(Gravity.TOP or Gravity.CENTER, 0, 96.toPx())
                                duration = Toast.LENGTH_LONG
                                view = binding.root
                            }
                        }

                        private fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
                    }
                toast.createToast(requireContext(), "최대 10자까지 가능해요!")?.show()
            }
        }
        binding.etSignupNickname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.etSignupNickname.setBackgroundResource(R.drawable.edittext_bg_default)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        return binding.root
    }
}
