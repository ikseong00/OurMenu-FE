package com.example.ourmenu.home.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ourmenu.data.HomeMenuData
import com.example.ourmenu.databinding.ItemHomeMenuMainBinding

class HomeMenuMainRVAdapter(val items: ArrayList<HomeMenuData>, val context: Context) :
    RecyclerView.Adapter<HomeMenuMainRVAdapter.ViewHolder>() {

    var itemWidth = 0

    inner class ViewHolder(private val binding: ItemHomeMenuMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeMenuData) {
            binding.tvItemMenuMain.text = item.menu
            binding.tvItemStoreMain.text = item.store
            // TODO Glide 추가


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeMenuMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = 2000

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position % items.size])
    }


}
