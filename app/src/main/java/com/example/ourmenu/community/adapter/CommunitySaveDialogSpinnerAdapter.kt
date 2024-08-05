package com.example.ourmenu.community.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.example.ourmenu.R
import com.example.ourmenu.data.PlaceInfoData
import com.example.ourmenu.databinding.CommunityFilterSpinnerDefaultBinding
import com.example.ourmenu.databinding.CommunityFilterSpinnerItemBinding
import com.example.ourmenu.databinding.CommunitySaveDialogSpinnerItemBinding
import com.example.ourmenu.util.Utils.viewGone


class CommunitySaveDialogSpinnerAdapter(
    private val context: Context,
    private var items: ArrayList<String>,
) :
    ArrayAdapter<String>(context, R.layout.spinner_default_background, items) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // 스피너의 기본 선택된 항목을 위한 뷰
        val binding = CommunityFilterSpinnerDefaultBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        binding.itemCfsdDropdownText.text = this.getItem(position)

        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        // 스피너의 드롭다운 목록을 위한 뷰

        val binding = CommunitySaveDialogSpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.itemCsdsiText.text = items[position]
        return binding.root
    }




}

