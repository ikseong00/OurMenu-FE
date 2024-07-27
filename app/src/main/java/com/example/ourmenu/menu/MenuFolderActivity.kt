package com.example.ourmenu.menu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ourmenu.R
import com.example.ourmenu.databinding.ActivityMenuFolderBinding

class MenuFolderActivity : AppCompatActivity() {

    lateinit var binding: ActivityMenuFolderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuFolderBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // 메뉴화면에서 수정버튼으로 클릭되면 true, 기본값이면 false
        val isEdit = intent.getBooleanExtra(
            "isEdit"/* true */, false
        )

        // 수정화면 인지 기본화면 인지 세팅하는 부분
        val menuFolderFragment = MenuFolderFragment()
        val bundle = Bundle()
        bundle.putBoolean("isEdit", isEdit as Boolean)
        menuFolderFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.menu_folder_frm, menuFolderFragment)
            .commitAllowingStateLoss()

    }
}
