package com.example.ourmenu.menu.menuFolder.post.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ourmenu.R
import com.example.ourmenu.data.DummyMenuData
import com.example.ourmenu.databinding.ItemMenuFolderBinding
import com.example.ourmenu.databinding.ItemMenuFolderDetailMenuBinding
import com.example.ourmenu.menu.iteminterface.MenuItemClickListener

class PostMenuFolderRVAdapter(private val items: ArrayList<DummyMenuData>) :
    RecyclerView.Adapter<PostMenuFolderRVAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemMenuFolderDetailMenuBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DummyMenuData) {
            binding.sivItemMfdMenuImage
            binding.tvItemMfdMenuName.text = item.menu
            binding.tvItemMfdMenuPlace.text = item.store
            binding.tvItemMfdMenuPlace.text = item.address
            // 안보이게
            binding.ivItemMfdExtraButton.visibility = View.GONE


        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostMenuFolderRVAdapter.ViewHolder {
        val binding = ItemMenuFolderDetailMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostMenuFolderRVAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}
