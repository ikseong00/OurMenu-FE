package com.example.ourmenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.ourmenu.data.AddMenuImageData
import com.example.ourmenu.databinding.AddMenuNameMenuImageDefaultBinding

class AddMenuImageAdapter(val items : ArrayList<AddMenuImageData>) : RecyclerView.Adapter<AddMenuImageAdapter.ViewHolder>(){
    inner class ViewHolder(val binding: AddMenuNameMenuImageDefaultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun defaultBind(item: AddMenuImageData) {
            binding.sivAddMenuImage.setImageResource(R.drawable.ic_google)
            binding.ivAddMenuUnion.visibility = View.INVISIBLE
            binding.tvAddMenuRequired.visibility = View.INVISIBLE
            binding.sivAddMenuUnionBg.visibility = View.INVISIBLE
        }
        fun nullBind(item : AddMenuImageData){
            binding.root.setOnClickListener{
                imageListener.onImageClick(binding.sivAddMenuImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddMenuImageAdapter.ViewHolder {
        val defaultBinding = AddMenuNameMenuImageDefaultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(defaultBinding)
    }

    override fun onBindViewHolder(holder: AddMenuImageAdapter.ViewHolder, position: Int) {
        if (position == 0){
            holder.nullBind(items[position])
        }else{
            holder.defaultBind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return this.items.size
    }

    public interface OnImageClickListener : View.OnClickListener {
        public fun onImageClick(imageView : ImageView)
    }

    lateinit var imageListener: OnImageClickListener

    public fun setOnProfileClickListener(listener: OnImageClickListener) {
        this.imageListener = listener
    }
}
