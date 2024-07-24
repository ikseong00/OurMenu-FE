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

        binding.etCode1.addTextChangedListener{
            binding.etCode2.requestFocus()
        }
        binding.etCode2.addTextChangedListener {
            binding.etCode3.requestFocus()
        }
        binding.etCode3.addTextChangedListener{
            binding.etCode4.requestFocus()
        }
        binding.etCode4.addTextChangedListener{
            binding.etCode5.requestFocus()
        }
        binding.etCode5.addTextChangedListener{
            binding.etCode6.requestFocus()
        }
        return binding.root
    }
}
