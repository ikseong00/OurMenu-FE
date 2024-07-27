package com.example.ourmenu.menu.menufolder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ourmenu.databinding.FragmentPostMenuFolderGetDetailBinding

class PostMenuFolderGetDetailFragment : Fragment() {

    lateinit var binding: FragmentPostMenuFolderGetDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostMenuFolderGetDetailBinding.inflate(layoutInflater)








        return binding.root
    }
}
