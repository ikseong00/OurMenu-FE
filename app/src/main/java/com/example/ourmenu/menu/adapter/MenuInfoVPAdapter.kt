package com.example.ourmenu.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ourmenu.databinding.ItemMenuInfoImageBinding



class MenuInfoVPAdapter(val items: ArrayList<String>) : RecyclerView.Adapter<MenuInfoVPAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemMenuInfoImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : String){
            // TODO Glide 추가
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuInfoVPAdapter.ViewHolder {
        val binding = ItemMenuInfoImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuInfoVPAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
