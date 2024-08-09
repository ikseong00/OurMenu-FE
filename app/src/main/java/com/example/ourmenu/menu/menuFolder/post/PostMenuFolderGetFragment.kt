package com.example.ourmenu.menu.menuFolder.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ourmenu.data.menu.data.MenuData
import com.example.ourmenu.databinding.FragmentPostMenuFolderGetBinding
import com.example.ourmenu.menu.menuFolder.post.adapter.PostMenuFolderGetDetailRVAdapter

class PostMenuFolderGetFragment(val postMenuFolderFragment: PostMenuFolderFragment) : Fragment() {

    lateinit var binding: FragmentPostMenuFolderGetBinding
    lateinit var rvAdapter: PostMenuFolderGetDetailRVAdapter
    lateinit var dummyItems: ArrayList<MenuData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostMenuFolderGetBinding.inflate(layoutInflater)

        initDummy()
        initRV()
        initListener()



        return binding.root
    }

    private fun initListener() {
        // 뒤로가기
        binding.ivPmfgBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.btnPmfgAddMenu.setOnClickListener {

            val bundle = Bundle().apply {
                putSerializable(
                    "checkedItems", rvAdapter.checkedItems
                )
            }

            postMenuFolderFragment.apply {
                arguments = bundle
            }

            parentFragmentManager.popBackStack()
//            with(parentFragmentManager) {
//                // 백스택 제거
//                popBackStack("PostMenuFolderFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)
//                popBackStack("PostMenuFolderGetFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)
//                beginTransaction()
//                    .replace(R.id.post_menu_folder_frm, postMenuFolderFragment)
//                    .commit()
//            }
        }
    }

    private fun initDummy() {
        dummyItems = ArrayList<MenuData>()
        for (i in 1..5) {
            dummyItems.add(
                MenuData(
                    groupId = i,
                    menuId = i,
                    menuImgUrl = "",
                    menuPrice = 1000 * i,
                    menuTitle = "menuTitle$i",
                    placeAddress = "placeAddress$i",
                    placeTitle = "place$i"
                )
            )
        }
    }

    private fun initRV() {
        rvAdapter = PostMenuFolderGetDetailRVAdapter(dummyItems, requireContext()).apply {
            setOnItemClickListener {
                checkButtonEnabled()
            }
        }
        binding.rvPmfgMenu.adapter = rvAdapter

        // 메뉴 갯수 조정
        binding.tvPmfgMenuCount.text = dummyItems.size.toString()
    }

    private fun checkButtonEnabled() {
        // 체크된 항목이 없으면 비활성화,
        // 체크된 항목이 있으면 활성화
        binding.btnPmfgAddMenu.isEnabled = rvAdapter.checkedItems.isNotEmpty()
    }
}
