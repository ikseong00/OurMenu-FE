package com.example.ourmenu.community.write

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ourmenu.R
import com.example.ourmenu.community.CommunityPostFragment
import com.example.ourmenu.databinding.ActivityCommunityPostBinding

class CommunityWritePostActivity : AppCompatActivity() {

    lateinit var binding: ActivityCommunityPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommunityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkFlag()


    }

    private fun checkFlag() {
        var flag = intent.getStringExtra("flag") ?: "default"
        flag = "write"
        when (flag) {
            "post" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.community_post_frm, CommunityPostFragment())
                    .commitAllowingStateLoss()
            }

            "write" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.community_post_frm, CommunityWritePostFragment())
                    .commitAllowingStateLoss()
            }

            else -> {
                return
            }
        }
    }
}
