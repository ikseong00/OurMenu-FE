package com.example.ourmenu.menu.menuFolder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.graphics.drawable.toDrawable
import com.example.ourmenu.R
import com.example.ourmenu.community.adapter.CommunityFilterSpinnerAdapter
import com.example.ourmenu.databinding.FragmentMenuFolderDetailAllBinding
import com.example.ourmenu.menu.adapter.MenuFolderAllFilterSpinnerAdapter

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

        initSpinner()
        initListener()




        return binding.root
    }

    private fun initSpinner() {
        val adapter =
            MenuFolderAllFilterSpinnerAdapter<String>(requireContext(), arrayListOf("이름순", "등록순", "가격순"))
        adapter.setDropDownViewResource(R.layout.spinner_item_background)
        binding.spnMfdaFilter.adapter = adapter
        binding.spnMfdaFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                adapter.selectedPos = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

    private fun initListener() {

        binding.btnMfdaAddMenu.setOnClickListener {

        }
    }
}
