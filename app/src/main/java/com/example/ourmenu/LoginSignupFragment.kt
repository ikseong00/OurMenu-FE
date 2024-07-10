package com.example.ourmenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ourmenu.databinding.FragmentLoginSignupBinding

class LoginSignupFragment : Fragment() {
    lateinit var binding : FragmentLoginSignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginSignupBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireFragmentManager().beginTransaction()
            .replace(binding.clMainscreen.id,SignupEmailFragment()) //LoginFragment로 수정해야합니다.
            .commit()

        return binding.root
    }
}
