package com.example.ourmenu.menu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ourmenu.data.HomeMenuData
import com.example.ourmenu.databinding.ItemMenuFolderBinding
import com.example.ourmenu.menu.callback.SwipeItemTouchHelperCallback
import com.example.ourmenu.menu.iteminterface.MenuItemClickListener

class MenuRVAdapter(
    val items: ArrayList<HomeMenuData>, val context: Context,
    val swipeItemTouchHelperCallback: SwipeItemTouchHelperCallback
) : RecyclerView.Adapter<MenuRVAdapter.ViewHolder>() {

    private lateinit var itemClickListener: MenuItemClickListener

    fun setOnItemClickListener(onItemClickListener: MenuItemClickListener) {
        itemClickListener = onItemClickListener
    }

    inner class ViewHolder(val binding: ItemMenuFolderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HomeMenuData) {
            // 왼쪽으로 swipe 된 상태, 수정버튼 삭제버튼 누를 수 있음.


            binding.clItemMenuFolderEdit.setOnClickListener {
                if (swipeItemTouchHelperCallback.isEditable()) {

                    // TODO 이벤트리스너 작성 ( 인터페이스로 )
                    // TODO API 설정
                    itemClickListener.onEditClick()
                }
            }

            binding.clItemMenuFolderDelete.setOnClickListener {
                if (swipeItemTouchHelperCallback.isEditable()) {
                    itemClickListener.onDeleteClick()
                    // TODO API 설정
                }
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuRVAdapter.ViewHolder {
        val binding = ItemMenuFolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuRVAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
