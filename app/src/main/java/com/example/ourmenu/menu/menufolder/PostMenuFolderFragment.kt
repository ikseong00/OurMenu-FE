package com.example.ourmenu.menu.menufolder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ourmenu.databinding.FragmentPostMenuFolderBinding

class PostMenuFolderFragment : Fragment() {

    lateinit var binding: FragmentPostMenuFolderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostMenuFolderBinding.inflate(layoutInflater)






        return binding.root
    }
}
