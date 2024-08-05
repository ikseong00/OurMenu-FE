package com.example.ourmenu.addMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.ourmenu.R
import com.example.ourmenu.databinding.FragmentAddMenuTagBinding

class AddMenuTagFragment : Fragment() {

    lateinit var binding: FragmentAddMenuTagBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddMenuTagBinding.inflate(inflater, container, false)
        val adapter = ArrayAdapter(requireContext(),R.layout.tag_default, arrayListOf("태그","태그","태그","태그","태그","태그"))
        binding.lvAddMenuTagTag.adapter = adapter
        return binding.root
    }
}
