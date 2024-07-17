package com.example.ourmenu.addMenu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ourmenu.data.AddMenuSearchResultData
import com.example.ourmenu.databinding.ItemAddMenuSearchResultBinding

class AddMenuRVAdapter(
    var items: ArrayList<AddMenuSearchResultData>,
    val itemClickListener: (AddMenuSearchResultData) -> Unit,
) : RecyclerView.Adapter<AddMenuRVAdapter.ViewHolder>() {
    inner class ViewHolder(
        private val binding: ItemAddMenuSearchResultBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AddMenuSearchResultData) {
            binding.tvAddMenuSearchResultPlace.text = item.placeName
            binding.tvAddMenuSearchResultAddress.text = item.address
            binding.tvAddMenuSearchResultType.text = item.type
            binding.tvAddMenuSearchResultDistance.text = item.distance

            binding.root.setOnClickListener { itemClickListener(item) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemAddMenuSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        holder.bind(items[position])
    }

    fun updateItems(newItems: ArrayList<AddMenuSearchResultData>) {
        items = newItems
        notifyDataSetChanged()
    }
}
