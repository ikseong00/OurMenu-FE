package com.example.ourmenu.addMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ourmenu.databinding.FragmentAddMenuTagBinding

class AddMenuTagFragment : Fragment() {

    lateinit var binding : FragmentAddMenuTagBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddMenuTagBinding.inflate(inflater,container,false)
        return binding.root
    }

}
