package com.example.ourmenu.menu.menuInfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ourmenu.R
import com.example.ourmenu.databinding.ActivityMenuInfoBinding

class MenuInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuInfoBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.menu_info_frm, MenuInfoFragment())
            .commitAllowingStateLoss()

    }
}
