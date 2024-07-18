package com.example.ourmenu.menuinfo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ourmenu.R
import com.example.ourmenu.databinding.ActivityMenuInfoBinding

class MenuInfoActivity : AppCompatActivity() {

    lateinit var binding: ActivityMenuInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuInfoBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }
}
