package com.example.ourmenu.landing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ourmenu.R
import com.example.ourmenu.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {
    lateinit var binding: ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 시작 화면일땐 landing fragment 보여주기
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_landing, LandingFragment())
            .commitAllowingStateLoss()
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_landing, fragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }
}
