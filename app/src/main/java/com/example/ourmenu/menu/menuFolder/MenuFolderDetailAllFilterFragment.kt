package com.example.ourmenu.menu.menuFolder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.ourmenu.R
import com.example.ourmenu.databinding.FragmentMenuFolderDetailAllFilterBinding
import com.google.android.material.slider.LabelFormatter

class MenuFolderDetailAllFilterFragment : Fragment() {

    lateinit var binding: FragmentMenuFolderDetailAllFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMenuFolderDetailAllFilterBinding.inflate(layoutInflater)

        binding.rssss.run {
            valueFrom = 0f
            valueTo = 100f
            setValues(10f, 70f)
            setCustomThumbDrawablesForValues(R.drawable.ic_slider_thumb_left, R.drawable.ic_slider_thumb_right)
            trackActiveTintList = ContextCompat.getColorStateList(requireContext(), R.color.Primary_500_main)!!
            trackInactiveTintList = ContextCompat.getColorStateList(requireContext(), R.color.Neutral_300)!!
            labelBehavior = LabelFormatter.LABEL_GONE
        }

        return binding.root
    }
}
