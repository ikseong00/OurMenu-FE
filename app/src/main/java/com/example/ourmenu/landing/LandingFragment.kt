package com.example.ourmenu.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ourmenu.databinding.FragmentLandingBinding

class LandingFragment : Fragment() {
    lateinit var binding: FragmentLandingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLandingBinding.inflate(inflater, container, false)

        binding.bLandingLogin.setOnClickListener {
            (activity as? LandingActivity)?.replaceFragment(LoginFragment())
        }

        return binding.root
    }
}
