package com.example.ourmenu.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.ourmenu.databinding.FragmentMapBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MapFragment : Fragment() {
    lateinit var binding: FragmentMapBinding
    lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)

        bottomSheetBehavior = BottomSheetBehavior.from(binding.clMapBottomSheet)

        bottomSheetBehavior.addBottomSheetCallback(
            object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(
                    bottomSheet: View,
                    newState: Int,
                ) {
                    // Do something when state changes
                }

                override fun onSlide(
                    bottomSheet: View,
                    slideOffset: Float,
                ) {
                    adjustButtonPosition()
                }
            },
        )

        return binding.root
    }

    private fun adjustButtonPosition() {
        val buttonHeight = binding.clMapGotoMapBtn.height
        val bottomSheetHeight = binding.clMapBottomSheet.height
        val bottomSheetTop = binding.clMapBottomSheet.top
        val parentHeight = binding.root.height

        val newButtonY = bottomSheetTop - buttonHeight - 42

        binding.clMapGotoMapBtn.y = newButtonY.toFloat()
    }
}
