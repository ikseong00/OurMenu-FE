package com.example.ourmenu.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return binding.root
    }
}
