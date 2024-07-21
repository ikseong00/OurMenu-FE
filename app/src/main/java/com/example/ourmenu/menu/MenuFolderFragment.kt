package com.example.ourmenu.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ourmenu.R
import com.example.ourmenu.databinding.FragmentMenuFolderBinding

class MenuFolderFragment : Fragment() {

    lateinit var binding : FragmentMenuFolderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMenuFolderBinding.inflate(layoutInflater)

        binding.ivMenuFolderVert.setOnClickListener {

        }

        return binding.root

    }

}
