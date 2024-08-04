package com.example.ourmenu.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ourmenu.data.MypagePostData
import com.example.ourmenu.databinding.ItemMypageMypostBinding

class MypageRVAdapter(
    var items: ArrayList<MypagePostData>,
    val itemClickListener: (MypagePostData) -> Unit,
) : RecyclerView.Adapter<MypageRVAdapter.ViewHolder>() {
    inner class ViewHolder(
        private val binding: ItemMypageMypostBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MypagePostData) {
            binding.tvMypagePostTitle.text = item.title
            binding.tvMypagePostContent.text = item.content
            binding.sivMypagePostProfile.setImageResource(item.profileImg)
            binding.tvMypagePostUsername.text = item.username
            binding.tvMypagePostTime.text = item.time
            binding.tvMypagePostViewCount.text = item.viewCount.toString()
            binding.sivMypageMenuThumbnail.setImageResource(item.thumbnail)
            binding.tvMypageMenuCount.text = item.menuCount.toString()

            binding.root.setOnClickListener { itemClickListener(item) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemMypageMypostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
