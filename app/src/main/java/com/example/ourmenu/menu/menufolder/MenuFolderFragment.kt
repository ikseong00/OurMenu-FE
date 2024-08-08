package com.example.ourmenu.menu.menuFolder

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.ourmenu.addMenu.AddMenuActivity
import com.example.ourmenu.data.HomeMenuData
import com.example.ourmenu.data.menuFolder.data.MenuFolderData
import com.example.ourmenu.data.menuFolder.response.MenuFolderResponse
import com.example.ourmenu.databinding.FragmentMenuFolderBinding
import com.example.ourmenu.menu.adapter.MenuFolderRVAdapter
import com.example.ourmenu.menu.callback.SwipeItemTouchHelperCallback
import com.example.ourmenu.menu.iteminterface.MenuItemClickListener
import com.example.ourmenu.menu.menuFolder.post.PostMenuFolderActivity
import com.example.ourmenu.retrofit.RetrofitObject
import com.example.ourmenu.retrofit.service.MenuFolderService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MenuFolderFragment : Fragment() {
    lateinit var binding: FragmentMenuFolderBinding
    lateinit var itemClickListener: MenuItemClickListener
    lateinit var menuFolderItems: ArrayList<MenuFolderData>
    private val retrofit = RetrofitObject.retrofit
    private val service = retrofit.create(MenuFolderService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMenuFolderBinding.inflate(inflater, container, false)



        getMenuFolders()


        initItemListener()
        initTouchHelperRV()

        return binding.root
    }

    private fun getMenuFolders() {
        service.getMenuFolders().enqueue(
            object : Callback<MenuFolderResponse> {
                override fun onResponse(
                    call: Call<MenuFolderResponse>,
                    response: Response<MenuFolderResponse>,
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        val menuFolders = result?.response
//                        menuFolders?.response?.forEach {
//                            Log.d("menuFolders", "${it.title}")
//                        }
                        menuFolders?.let {
                            menuFolderItems = menuFolders
                        }
                    }
                }

                override fun onFailure(
                    call: Call<MenuFolderResponse>,
                    t: Throwable,
                ) {
                    Log.d("menuFolders", t.message.toString())
                }
            })

    }

    private fun initItemListener() {
        // 상단 메뉴 추가
        binding.ivMenuAddMenu.setOnClickListener {
            // TODO API 추가
            val intent = Intent(context, AddMenuActivity::class.java)
            startActivity(intent)
        }

        // 메뉴판 추가하기
        binding.btnMenuAddMenuFolder.setOnClickListener {
            val intent = Intent(context, PostMenuFolderActivity::class.java)
            startActivity(intent)
        }

        // 전체 메뉴판 보기
        binding.btnMenuAllMenu.setOnClickListener {
            val intent = Intent(context, MenuFolderDetailActivity::class.java)
            intent.putExtra("isAll", true)
            startActivity(intent)
        }

        itemClickListener =
            object : MenuItemClickListener {
                override fun onMenuClick() {
                    val intent = Intent(context, MenuFolderDetailActivity::class.java)
                    startActivity(intent)
                }

                override fun onEditClick() {
                    // MenuFolderFragment 에서 editClick() 메소드 실행
                    val intent = Intent(context, MenuFolderDetailActivity::class.java)
                    intent.putExtra("isEdit", true)
                    startActivity(intent)
                }

                override fun onDeleteClick() {
                    TODO("Not yet implemented")
                }
            }
    }

    //
    @SuppressLint("ClickableViewAccessibility") // 이줄 없으면 setOnTouchListener 에 밑줄생김
    private fun initTouchHelperRV() {
        val dummyItems = ArrayList<HomeMenuData>()
        for (i in 1..6) {
            dummyItems.add(
                HomeMenuData("1", "menu2", "store3"),
            )
        }

        val clamp: Float

        // TODO Util 로 뺴기
        fun dpToPx(dp: Int): Int {
            val density = resources.displayMetrics.density
            return (dp * density).toInt()
        }
        clamp = dpToPx(120).toFloat()

        val swipeItemTouchHelperCallback =
            SwipeItemTouchHelperCallback().apply {
                setClamp(clamp)
            }

        val itemTouchHelper = ItemTouchHelper(swipeItemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvMenuMenuFolder)
        // 리사이클러 뷰 설정
        with(binding.rvMenuMenuFolder) {
            adapter =
                MenuFolderRVAdapter(menuFolderItems, requireContext(), swipeItemTouchHelperCallback).apply {
                    setOnItemClickListener(itemClickListener)
                }
            // 다른 뷰를 건들면 기존 뷰의 swipe 가 초기화 됨
            setOnTouchListener { _, _ ->
                swipeItemTouchHelperCallback.removePreviousClamp(this)
                false
            }
        }
    }
}
