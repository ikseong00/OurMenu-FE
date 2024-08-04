package com.example.ourmenu.menu.menuFolder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ourmenu.R
import com.example.ourmenu.databinding.FragmentMenuFolderDetailAllBinding

class MenuFolderDetailAllFragment : Fragment() {

    lateinit var binding: FragmentMenuFolderDetailAllBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMenuFolderDetailAllBinding.inflate(layoutInflater)

        binding.clMfdaFilter.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.menu_folder_frm, MenuFolderDetailAllFilterFragment())
                .commit()
        }




        return binding.root
    }
}
