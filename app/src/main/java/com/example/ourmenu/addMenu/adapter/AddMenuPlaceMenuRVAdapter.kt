package com.example.ourmenu.addMenu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ourmenu.data.PlaceMenuData
import com.example.ourmenu.databinding.ItemAddMenuPlaceMenuBinding

class AddMenuPlaceMenuRVAdapter(
    var items: ArrayList<PlaceMenuData>,
) : RecyclerView.Adapter<AddMenuPlaceMenuRVAdapter.ViewHolder>() {
    inner class ViewHolder(
        private val binding: ItemAddMenuPlaceMenuBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlaceMenuData) {
            binding.tvAddMenuBsMenu.text = item.menuName
            binding.tvAddMenuBsPrice.text = item.price
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemAddMenuPlaceMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        holder.bind(items[position])
    }
}
