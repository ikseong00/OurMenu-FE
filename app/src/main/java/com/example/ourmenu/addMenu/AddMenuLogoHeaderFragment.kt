package com.example.ourmenu.addMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ourmenu.databinding.FragmentAddMenuLogoHeaderBinding

class AddMenuLogoHeaderFragment : Fragment() {
    lateinit var binding: FragmentAddMenuLogoHeaderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddMenuLogoHeaderBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

//        childFragmentManager.beginTransaction()
//            .replace(R.id.cl_add_menu_logo_main, TitleFragment())
//            .commitNow()
    }
}
