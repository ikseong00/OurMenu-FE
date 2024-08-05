package com.example.ourmenu.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ourmenu.addMenu.AddMenuActivity
import com.example.ourmenu.data.HomeMenuData
import com.example.ourmenu.databinding.FragmentHomeBinding
import com.example.ourmenu.databinding.ItemHomeMenuMainBinding
import com.example.ourmenu.home.adapter.HomeMenuMainRVAdapter
import com.example.ourmenu.home.adapter.HomeMenuSubRVAdapter
import com.example.ourmenu.home.iteminterface.HomeItemClickListener
import com.example.ourmenu.menu.menuInfo.MenuInfoActivity
import kotlin.math.max

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var dummyItems: ArrayList<HomeMenuData>
    lateinit var itemClickListener: HomeItemClickListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initDummyData()
        initItemClickListener()
        initMainMenuRV()
        initSubMenuRV()



        binding.ivHomeTitleAdd.setOnClickListener {
            val intent = Intent(requireContext(), AddMenuActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    private fun initItemClickListener() {
        itemClickListener =
            object : HomeItemClickListener {
                override fun onItemClick(homeMenuData: HomeMenuData) {
                    val intent = Intent(activity, MenuInfoActivity::class.java)
                    // TODO 추가할 데이터 추가
                    startActivity(intent)
                }
            }
    }

    private fun initSubMenuRV() {
        binding.rvHomeMenuSubFirst.adapter =
            HomeMenuSubRVAdapter(dummyItems).apply {
                setOnItemClickListener(itemClickListener)
            }
        binding.rvHomeMenuSubSecond.adapter =
            HomeMenuSubRVAdapter(dummyItems).apply {
                setOnItemClickListener(itemClickListener)
            }
    }

    private fun initDummyData() {
        dummyItems = ArrayList<HomeMenuData>()
        for (i in 1..6) {
            dummyItems.add(
                HomeMenuData("1", "menu2$i", "store3")
            )
        }
    }

    private fun initMainMenuRV() {
        binding.rvHomeMenuMain.adapter =
            HomeMenuMainRVAdapter(dummyItems, requireContext()).apply {
                setOnItemClickListener(itemClickListener)
            }


        // 아이템의 width를 구하기 위해 viewTreeObserver 사용
        binding.rvHomeMenuMain.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.rvHomeMenuMain.viewTreeObserver.removeOnGlobalLayoutListener(this)

                val width = binding.rvHomeMenuMain.layoutManager?.getChildAt(0)?.width
                val screenWidth = context?.resources?.displayMetrics?.widthPixels
                val offset = (screenWidth!! - width!!) / 2

                (binding.rvHomeMenuMain.layoutManager as LinearLayoutManager)
                    .scrollToPositionWithOffset(
                        1000,
                        offset
                    )
            }

        })


        binding.rvHomeMenuMain.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val firstPos =
                        (binding.rvHomeMenuMain.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                    val secondPos =
                        (binding.rvHomeMenuMain.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    val selectedPos = max(firstPos, secondPos)

                    if (selectedPos != -1) {
                        val viewItem =
                            (binding.rvHomeMenuMain.layoutManager as LinearLayoutManager)
                                .findViewByPosition(selectedPos)

                        viewItem?.run {
                            val itemMargin = (binding.rvHomeMenuMain.measuredWidth - viewItem.measuredWidth) / 2
                            binding.rvHomeMenuMain.smoothScrollBy(this.x.toInt() - itemMargin, 0)
                        }
                    }
                }
            }
        })
    }
}
