package com.example.ourmenu.menu.menuFolder.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ourmenu.R
import com.example.ourmenu.data.DummyMenuData
import com.example.ourmenu.databinding.FragmentPostMenuFolderGetBinding
import com.example.ourmenu.menu.iteminterface.MenuItemClickListener
import com.example.ourmenu.menu.menuFolder.post.adapter.PostMenuFolderGetRVAdapter

class PostMenuFolderGetFragment(val postMenuFolderFragment: PostMenuFolderFragment) : Fragment() {

    lateinit var binding: FragmentPostMenuFolderGetBinding
    lateinit var dummyItems: ArrayList<DummyMenuData>

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

        binding.btnPmfgAddMenu
    }

    private fun initDummy() {
        dummyItems = ArrayList<DummyMenuData>()
        for (i in 1..5) {
            dummyItems.add(
                DummyMenuData(title = "title$i", menuCount = i)
            )
        }
    }

    private fun initRV() {
        binding.rvPmfgMenuFolder.adapter = PostMenuFolderGetRVAdapter(dummyItems).apply {
            setOnItemClickListener(object : MenuItemClickListener {
                // 메뉴 클릭 리스너 추가
                override fun onMenuClick() {
                    val postMenuFolderGetDetailFragment = PostMenuFolderGetDetailFragment(postMenuFolderFragment)

                    parentFragmentManager.beginTransaction()
                        .addToBackStack("PostMenuFolderGetFragment")
                        .replace(R.id.post_menu_folder_frm, postMenuFolderGetDetailFragment)
                        .commit()
                }

                override fun onEditClick() {
                }

                override fun onDeleteClick() {
                }

            })
        }
    }
}
