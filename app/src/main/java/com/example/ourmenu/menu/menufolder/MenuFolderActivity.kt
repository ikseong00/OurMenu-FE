package com.example.ourmenu.menu.menufolder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ourmenu.R
import com.example.ourmenu.databinding.ActivityMenuFolderBinding

class MenuFolderActivity : AppCompatActivity() {

    lateinit var binding : ActivityMenuFolderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuFolderBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.menu_folder_frm, MenuFolderFragment())
            .commitAllowingStateLoss()

    }
}
