package com.example.ourmenu.menu.menuFolder.post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ourmenu.R
import com.example.ourmenu.databinding.ActivityPostMenuFolderBinding

class PostMenuFolderActivity : AppCompatActivity() {

    lateinit var binding: ActivityPostMenuFolderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPostMenuFolderBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.post_menu_folder_frm, PostMenuFolderFragment())
            .commitAllowingStateLoss()


    }
}
