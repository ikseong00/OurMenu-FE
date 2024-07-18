package com.example.ourmenu.home.iteminterface

import com.example.ourmenu.data.HomeMenuData
import com.example.ourmenu.databinding.ItemHomeMenuMainBinding
import com.example.ourmenu.databinding.ItemHomeMenuSubBinding

interface HomeItemClickListener {
    fun onItemClick(homeMenuData: HomeMenuData)
}
