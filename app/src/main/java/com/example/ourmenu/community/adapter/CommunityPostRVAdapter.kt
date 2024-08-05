package com.example.ourmenu.community.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ourmenu.data.HomeMenuData
import com.example.ourmenu.databinding.ItemCommunityPostMenuBinding
import okhttp3.internal.addHeaderLenient

class CommunityPostRVAdapter(
    var items: ArrayList<HomeMenuData>,
    val context: Context,
    val onSaveClick: (HomeMenuData) -> Unit,
    val onDeleteClick: (HomeMenuData) -> Unit
) : RecyclerView.Adapter<CommunityPostRVAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ItemCommunityPostMenuBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeMenuData, position: Int) {
//            binding.sivItemMenuImageMain.setImageResource(item.imageUrl)
//            binding.tvItemMenuMain.text = item.menu
//            binding.tvItemStoreMain.text = item.store
//
            binding.ivItemCpmDelete.setOnClickListener {
                onDeleteClick(item)
                removeItem(position)
            }
            binding.ivItemCpmSave.setOnClickListener {
                onSaveClick(item)
            }


            // 화면 자석 효과
            if (adapterPosition == 0 || adapterPosition == items.size) {
                binding.layoutItemHomeMenuMain.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)

                val displayMetrics = context.resources.displayMetrics
                val screenWidth = displayMetrics.widthPixels
                var mLayoutParam: RecyclerView.LayoutParams =
                    binding.layoutItemHomeMenuMain.layoutParams as RecyclerView.LayoutParams
                if (adapterPosition == 0)
                    mLayoutParam.leftMargin = (screenWidth - binding.layoutItemHomeMenuMain.measuredWidthAndState) / 2
                else
                    mLayoutParam.rightMargin = (screenWidth - binding.layoutItemHomeMenuMain.measuredWidthAndState) / 2
            }
        }
    }

    fun removeItem(position: Int) {
        // 아이템 삭제
        items.removeAt(position)
        // 어댑터에 변경 사항 알리기
        notifyItemRemoved(position)
        // 선택 사항: 아이템 삭제 후 갱신된 데이터 알림
        notifyItemRangeChanged(position, items.size)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CommunityPostRVAdapter.ViewHolder {
        val binding = ItemCommunityPostMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(
        holder: CommunityPostRVAdapter.ViewHolder,
        position: Int,
    ) {
        holder.bind(items[position], position)
    }


}
