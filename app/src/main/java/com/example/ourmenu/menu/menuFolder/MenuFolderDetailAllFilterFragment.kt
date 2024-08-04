package com.example.ourmenu.menu.menuFolder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.ourmenu.R
import com.example.ourmenu.databinding.FragmentMenuFolderDetailAllFilterBinding
import com.google.android.material.slider.LabelFormatter
import com.google.android.material.slider.RangeSlider

class MenuFolderDetailAllFilterFragment : Fragment() {

    lateinit var binding: FragmentMenuFolderDetailAllFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMenuFolderDetailAllFilterBinding.inflate(layoutInflater)

        initRangeSlider()

        return binding.root
    }

    private fun initRangeSlider() {
        // TODO 회의 후 자세한 수치 조정

        binding.rsMfdafRangeSlider.run {
            valueFrom = 0f // valueFrom , valueTo : 슬라이더가 가질 수 있는 총 범위
            valueTo = 100f
            setValues(0f, 100f) // 슬라이더가 시작할 초기 범위
            stepSize = 10f // 슬라이더 간격 사이즈
            setCustomThumbDrawablesForValues(
                R.drawable.ic_slider_thumb_left, R.drawable.ic_slider_thumb_right) // thumb 설정
            trackActiveTintList = ContextCompat.getColorStateList(requireContext(), R.color.Primary_500_main)!!
            // 활성화 색상
            trackInactiveTintList = ContextCompat.getColorStateList(requireContext(), R.color.Neutral_300)!!
            // 비활성화 색상
            labelBehavior = LabelFormatter.LABEL_GONE
            // 라벨 없애기
        }

        binding.rsMfdafRangeSlider.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: RangeSlider) {
                Log.d("start", "start")
            }

            override fun onStopTrackingTouch(slider: RangeSlider) {
                Log.d("stop", "stop")

            }
        })
        
        binding.rsMfdafRangeSlider.addOnChangeListener(RangeSlider.OnChangeListener { slider, value, fromUser ->
            val values = slider.values
            Log.d("Change", "value: $value, [${values[0]} ~ ${values[1]}")
        })

    }
}
