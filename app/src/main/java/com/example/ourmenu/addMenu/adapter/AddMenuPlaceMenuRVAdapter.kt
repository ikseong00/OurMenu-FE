package com.example.ourmenu.addMenu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ourmenu.R
import com.example.ourmenu.data.PlaceMenuData
import com.example.ourmenu.databinding.ItemAddMenuBtnBinding
import com.example.ourmenu.databinding.ItemAddMenuPlaceMenuBinding

class AddMenuPlaceMenuRVAdapter(
    var items: ArrayList<PlaceMenuData>,
    val onItemSelected: (Int) -> Unit,
    val onButtonClicked: () -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var selectedPosition: Int = RecyclerView.NO_POSITION

    companion object {
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_BUTTON = 1
    }

    inner class MenuViewHolder(
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
                binding.ivAddMenuBsAddBtn.setImageResource(R.drawable.ic_add_menu_checked)
            } else {
                binding.ivAddMenuBsAddBtn.setImageResource(R.drawable.ic_add_menu_stroked)
            }
        }
    }

    inner class ButtonViewHolder(
        private val binding: ItemAddMenuBtnBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnAddMenuAddMenu.setOnClickListener {
                onButtonClicked()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder =
        if (viewType == VIEW_TYPE_ITEM) {
            val binding = ItemAddMenuPlaceMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MenuViewHolder(binding)
        } else {
            val binding = ItemAddMenuBtnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ButtonViewHolder(binding)
        }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        if (holder is MenuViewHolder) {
            holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int = items.size + 1 // 버튼때문에 1 추가

    override fun getItemViewType(position: Int): Int = if (position == items.size) VIEW_TYPE_BUTTON else VIEW_TYPE_ITEM
}
