package com.example.ourmenu.account

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ourmenu.MainActivity
import com.example.ourmenu.R
import com.example.ourmenu.databinding.FragmentSignupNameBinding

class SignupNameFragment : Fragment() {
    lateinit var binding : FragmentSignupNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupNameBinding.inflate(inflater,container,false)
        binding.btnSignupName.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        return binding.root
    }

}
