package com.example.ourmenu.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ourmenu.R
import com.example.ourmenu.databinding.FragmentCommunityPostWriteBinding

class CommunityPostWriteFragment : Fragment() {

    lateinit var binding: FragmentCommunityPostWriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCommunityPostWriteBinding.inflate(layoutInflater)

        return binding.root
    }
}
