package com.example.ourmenu.menuinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.ourmenu.databinding.FragmentMenuInfoBinding
import com.example.ourmenu.menu.adapter.MenuInfoVPAdapter

class MenuInfoFragment : Fragment() {
    lateinit var binding: FragmentMenuInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuInfoBinding.inflate(inflater, container, false)

        initViewPager2Adapter()

        return binding.root
    }

    private fun initViewPager2Adapter() {
        val dummyItems = ArrayList<String>()
        for (i in 1..6) {
            dummyItems.add(
                "1"
            )

            binding.vpMenuInfoMenuImage.adapter = MenuInfoVPAdapter(dummyItems)
            binding.vpMenuInfoMenuImage.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            binding.idcMenuInfoIndicator.attachTo(binding.vpMenuInfoMenuImage)
        }
    }
}
