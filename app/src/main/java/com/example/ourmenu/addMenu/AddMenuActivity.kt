package com.example.ourmenu.addMenu

import android.net.http.SslCertificate.restoreState
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ourmenu.R
import com.example.ourmenu.databinding.ActivityAddMenuBinding


class AddMenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.cl_add_menu_main, AddMenuMapFragment())
                .commitNow()
        }
    }
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
    }
}
