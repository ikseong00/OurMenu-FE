package com.example.ourmenu.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ourmenu.R
import com.example.ourmenu.databinding.FragmentCommunityPostBinding

class CommunityPostFragment : Fragment() {

    lateinit var binding: FragmentCommunityPostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCommunityPostBinding.inflate(layoutInflater)
        

        return binding.root
    }
}
