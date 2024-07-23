package com.example.ourmenu.addMenu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ourmenu.R
import com.example.ourmenu.data.PlaceMenuData
import com.example.ourmenu.databinding.ItemAddMenuPlaceMenuBinding

class AddMenuPlaceMenuRVAdapter(
    var items: ArrayList<PlaceMenuData>,
    val onItemSelected: (Int) -> Unit,
) : RecyclerView.Adapter<AddMenuPlaceMenuRVAdapter.ViewHolder>() {
    private var selectedPosition: Int = RecyclerView.NO_POSITION

    inner class ViewHolder(
        private val binding: ItemAddMenuPlaceMenuBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.ivAddMenuBsAddBtn.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
                onItemSelected(selectedPosition) // 아이템 선택 콜백 호출
            }
        }

        fun bind(item: PlaceMenuData) {
            binding.tvAddMenuBsMenu.text = item.menuName
            binding.tvAddMenuBsPrice.text = item.price

            if (adapterPosition == selectedPosition) {
                binding.ivAddMenuBsAddBtn.setImageResource(R.drawable.ic_add_menu_filled)
            } else {
                binding.ivAddMenuBsAddBtn.setImageResource(R.drawable.ic_add_menu_stroked)
            }
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
