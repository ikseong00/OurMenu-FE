package com.example.ourmenu.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ourmenu.data.HomeMenuData
import com.example.ourmenu.databinding.ItemMenuFolderDetailMenuBinding

// TODO 데이터 종류 수정
class MenuFolderRVAdapter(val items: ArrayList<HomeMenuData>) : RecyclerView.Adapter<MenuFolderRVAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemMenuFolderDetailMenuBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HomeMenuData) {
            binding.tvItemMenuFolderDetailMenuName.text = item.menu
            binding.tvItemMenuFolderDetailMenuStore.text = item.store
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuFolderRVAdapter.ViewHolder {
        val binding = ItemMenuFolderDetailMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuFolderRVAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
