package com.example.ourmenu.menu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
