package com.example.ourmenu.addMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ourmenu.databinding.FragmentAddMenuMapBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class AddMenuMapFragment : Fragment() {
    lateinit var binding: FragmentAddMenuMapBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddMenuMapBinding.inflate(inflater, container, false)

        bottomSheetBehavior = BottomSheetBehavior.from(binding.clAddMenuBottomSheet)

        binding.rvAddMenuSearchResults.layoutManager = LinearLayoutManager(context)

        return binding.root
    }
}
