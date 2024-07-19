package com.example.ourmenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ourmenu.databinding.FragmentAddMenuNameBinding

class AddMenuNameFragment : Fragment() {

    lateinit var binding : FragmentAddMenuNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddMenuNameBinding.inflate(inflater,container,false)
        binding.btnAddMenuNameNext.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack("MenuAddNameFragment")
                .replace(R.id.cl_mainscreen,AddMenuTagFragment()) //id 변경해야됨
                .commit()
        }

        return binding.root
    }
}
