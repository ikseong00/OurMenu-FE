package com.example.ourmenu.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.ourmenu.R
import com.example.ourmenu.databinding.FragmentSignupEmailSertifyBinding

class SignupEmailSertifyFragment : Fragment() {
    lateinit var binding: FragmentSignupEmailSertifyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSignupEmailSertifyBinding.inflate(inflater, container, false)

        binding.btnSignupEmailSertify.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .addToBackStack("SignupEmailSertify")
                .replace(R.id.cl_mainscreen, SignupPwFragment())
                .commit()
        }

        binding.etSignupCode1.addTextChangedListener{
            binding.etSignupCode2.requestFocus()
        }
        binding.etSignupCode2.addTextChangedListener {
            binding.etSignupCode3.requestFocus()
        }
        binding.etSignupCode3.addTextChangedListener{
            binding.etSignupCode4.requestFocus()
        }
        binding.etSignupCode4.addTextChangedListener{
            binding.etSignupCode5.requestFocus()
        }
        binding.etSignupCode5.addTextChangedListener{
            binding.etSignupCode6.requestFocus()
        }
        return binding.root
    }
}
