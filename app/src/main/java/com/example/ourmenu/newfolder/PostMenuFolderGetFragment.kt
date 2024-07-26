package com.example.ourmenu.newfolder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ourmenu.R
import com.example.ourmenu.databinding.FragmentPostMenuFolderGetBinding

class PostMenuFolderGetFragment : Fragment() {

    lateinit var binding: FragmentPostMenuFolderGetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostMenuFolderGetBinding.inflate(layoutInflater)





        return binding.root
    }
}
