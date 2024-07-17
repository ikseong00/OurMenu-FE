package com.example.ourmenu.menu

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ourmenu.data.HomeMenuData
import com.example.ourmenu.databinding.FragmentMenuBinding
import com.example.ourmenu.menu.adapter.MenuRVAdapter
import com.example.ourmenu.menu.callback.ItemTouchHelperCallback

//import com.example.ourmenu.menu.listener.ItemTouchHelperCallback

class MenuFragment : Fragment() {

    lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMenuBinding.inflate(inflater, container, false)



        initTouchHelper()


        return binding.root

    }

    //
    @SuppressLint("ClickableViewAccessibility") // 이줄 없으면 setOnTouchListener 에 밑줄생김
    private fun initTouchHelper() {

        val dummyItems = ArrayList<HomeMenuData>()
        for (i in 1..6) {
            dummyItems.add(
                HomeMenuData("1", "menu2", "store3")
            )
        }

        val itemTouchHelperCallback = ItemTouchHelperCallback().apply {
            setClamp(128F)
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvMenuMenuFolder)


        // 리사이클러 뷰 설정
        with(binding.rvMenuMenuFolder) {
            adapter = MenuRVAdapter(dummyItems, requireContext(), itemTouchHelperCallback)

            // 다른 뷰를 건들면 기존 뷰의 swipe 가 초기화 됨
            setOnTouchListener { _, _ ->
                itemTouchHelperCallback.removePreviousClamp(this)
                false
            }
        }
    }

}
