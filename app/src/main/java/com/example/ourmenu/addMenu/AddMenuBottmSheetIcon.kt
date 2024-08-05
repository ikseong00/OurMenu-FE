package com.example.ourmenu.addMenu

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.createBitmap
import com.example.ourmenu.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.ourmenu.addMenu.AddMenuTagFragment
import com.example.ourmenu.databinding.AddMenuBottomSheetIconBinding

class AddMenuBottomSheetIcon(val fragment: AddMenuTagFragment, var selected: Int) : BottomSheetDialogFragment() {

    lateinit var binding: AddMenuBottomSheetIconBinding
    lateinit var selectList: ArrayList<View>
    lateinit var currentSelected: View
    var currentSelectedIcon = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddMenuBottomSheetIconBinding.inflate(inflater, container, false)
        selectList = arrayListOf(
            binding.ivAmbsiWatermelonSelected,
            binding.ivAmbsiStrawberrySelected,
            binding.ivAmbsiIcecreamSelected,
            binding.ivAmbsiDonutSelected,
            binding.ivAmbsiHotdogStickSelected,
            binding.ivAmbsiHamburgerSelected,
            binding.ivAmbsiCupcakeSelected,
            binding.ivAmbsiPizzaSelected,
            binding.ivAmbsiChickenSelected,
            binding.ivAmbsiHotdogSelected,
            binding.ivAmbsiCoffeeSelected
        )
        binding.flAmbsiWatermelon.setOnClickListener {
            if (binding.ivAmbsiWatermelonSelected.visibility != View.VISIBLE) {
                currentSelected.visibility = View.INVISIBLE
                selected = binding.cgAmbsiIcon.indexOfChild(binding.flAmbsiWatermelon)
                currentSelected = selectList[selected]
                currentSelectedIcon = R.drawable.ic_watermelon
                currentSelected.visibility = View.VISIBLE
            }
        }
        binding.flAmbsiChicken.setOnClickListener {
            if (binding.ivAmbsiChickenSelected.visibility != View.VISIBLE) {
                currentSelected.visibility = View.INVISIBLE
                currentSelectedIcon = R.drawable.ic_chicken
                selected = binding.cgAmbsiIcon.indexOfChild(binding.flAmbsiChicken)
                currentSelected = selectList[selected]
                currentSelected.visibility = View.VISIBLE
            }
        }
        binding.flAmbsiCoffee.setOnClickListener {
            if (binding.ivAmbsiCoffeeSelected.visibility != View.VISIBLE) {
                currentSelected.visibility = View.INVISIBLE
                currentSelectedIcon = R.drawable.ic_coffee
                selected = binding.cgAmbsiIcon.indexOfChild(binding.flAmbsiCoffee)
                currentSelected = selectList[selected]
                currentSelected.visibility = View.VISIBLE
            }
        }
        binding.flAmbsiCupcake.setOnClickListener {
            if (binding.ivAmbsiCupcakeSelected.visibility != View.VISIBLE) {
                currentSelected.visibility = View.INVISIBLE
                currentSelectedIcon = R.drawable.ic_cupcake
                selected = binding.cgAmbsiIcon.indexOfChild(binding.flAmbsiCupcake)
                currentSelected = selectList[selected]
                currentSelected.visibility = View.VISIBLE
            }
        }
        binding.flAmbsiDonut.setOnClickListener {
            if (binding.ivAmbsiDonutSelected.visibility != View.VISIBLE) {
                currentSelected.visibility = View.INVISIBLE
                currentSelectedIcon = R.drawable.ic_donut
                selected = binding.cgAmbsiIcon.indexOfChild(binding.flAmbsiDonut)
                currentSelected = selectList[selected]
                currentSelected.visibility = View.VISIBLE
            }
        }
        binding.flAmbsiHamburger.setOnClickListener {
            if (binding.ivAmbsiHamburgerSelected.visibility != View.VISIBLE) {
                currentSelected.visibility = View.INVISIBLE
                currentSelectedIcon = R.drawable.ic_hamburger
                selected = binding.cgAmbsiIcon.indexOfChild(binding.flAmbsiHamburger)
                currentSelected = selectList[selected]
                currentSelected.visibility = View.VISIBLE
            }
        }
        binding.flAmbsiHotdog.setOnClickListener {
            if (binding.ivAmbsiHotdogSelected.visibility != View.VISIBLE) {
                currentSelected.visibility = View.INVISIBLE
                currentSelectedIcon = R.drawable.ic_hotdog
                selected = binding.cgAmbsiIcon.indexOfChild(binding.flAmbsiHotdog)
                currentSelected = selectList[selected]
                currentSelected.visibility = View.VISIBLE
            }
        }
        binding.flAmbsiHotdogStick.setOnClickListener {
            if (binding.ivAmbsiHotdogStickSelected.visibility != View.VISIBLE) {
                currentSelected.visibility = View.INVISIBLE
                currentSelectedIcon = R.drawable.ic_hotdog_stick
                selected = binding.cgAmbsiIcon.indexOfChild(binding.flAmbsiHotdogStick)
                currentSelected = selectList[selected]
                currentSelected.visibility = View.VISIBLE
            }
        }
        binding.flAmbsiIcecream.setOnClickListener {
            if (binding.ivAmbsiIcecreamSelected.visibility != View.VISIBLE) {
                currentSelected.visibility = View.INVISIBLE
                currentSelectedIcon = R.drawable.ic_icecream
                selected = binding.cgAmbsiIcon.indexOfChild(binding.flAmbsiIcecream)
                currentSelected = selectList[selected]
                currentSelected.visibility = View.VISIBLE
            }
        }
        binding.flAmbsiPizza.setOnClickListener {
            if (binding.ivAmbsiPizzaSelected.visibility != View.VISIBLE) {
                currentSelected.visibility = View.INVISIBLE
                currentSelectedIcon = R.drawable.ic_pizza
                selected = binding.cgAmbsiIcon.indexOfChild(binding.flAmbsiPizza)
                currentSelected = selectList[selected]
                currentSelected.visibility = View.VISIBLE
            }
        }
        binding.flAmbsiStrawberry.setOnClickListener {
            if (binding.ivAmbsiStrawberrySelected.visibility != View.VISIBLE) {
                currentSelected.visibility = View.INVISIBLE
                currentSelectedIcon = R.drawable.ic_strawberry
                selected = binding.cgAmbsiIcon.indexOfChild(binding.flAmbsiStrawberry)
                currentSelected = selectList[selected]
                currentSelected.visibility = View.VISIBLE
            }
        }
        binding.btnAmbstConfirm.setOnClickListener {
            fragment.binding.ivAddMenuTagIcon.setImageResource(currentSelectedIcon)
            fragment.bottomSheetIconStart = selected
            this.dialog?.dismiss()
        }
        binding.btnAmbstReset.setOnClickListener {
            this.dialog?.dismiss()
        }
        currentSelected = selectList[selected]
        currentSelected.visibility = View.VISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog?.setOnDismissListener {
            fragment.clearBlur()
        }
        super.onViewCreated(view, savedInstanceState)
    }

}
