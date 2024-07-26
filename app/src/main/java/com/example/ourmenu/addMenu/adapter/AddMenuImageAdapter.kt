package com.example.ourmenu.addMenu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.ourmenu.data.AddMenuImageData
import com.example.ourmenu.databinding.ItemAddMenuNameMenuImageBinding

class AddMenuImageAdapter(val items : ArrayList<AddMenuImageData>) : RecyclerView.Adapter<AddMenuImageAdapter.ViewHolder>(){
    inner class ViewHolder(val binding: ItemAddMenuNameMenuImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun defaultBind(item: AddMenuImageData) {
            binding.sivAddMenuImage.setImageURI(item.imageUri)
            binding.ivAddMenuUnion.visibility = View.INVISIBLE
            binding.tvAddMenuRequired.visibility = View.INVISIBLE
            binding.sivAddMenuUnionBg.visibility = View.INVISIBLE
        }
        fun nullBind(item : AddMenuImageData){
            binding.flAddMenuMenuImage.setOnClickListener{
                imageListener.onImageClick(binding.sivAddMenuImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val defaultBinding = ItemAddMenuNameMenuImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(defaultBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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
