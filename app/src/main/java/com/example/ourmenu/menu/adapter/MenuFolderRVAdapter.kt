package com.example.ourmenu.menu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ourmenu.data.HomeMenuData
import com.example.ourmenu.databinding.ItemMenuFolderBinding
import com.example.ourmenu.menu.callback.ItemTouchHelperCallback

// TODO 데이터 종류 수정
class MenuFolderRVAdapter(val items: ArrayList<HomeMenuData>) : RecyclerView.Adapter<MenuFolderRVAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemMenuFolderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HomeMenuData) {
            
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuFolderRVAdapter.ViewHolder {
        val binding = ItemMenuFolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuFolderRVAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
