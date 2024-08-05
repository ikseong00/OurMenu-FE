package com.example.ourmenu.addMenu

import android.content.Context
import android.content.DialogInterface
import android.graphics.PorterDuff
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ScrollView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.example.ourmenu.R
import com.example.ourmenu.databinding.FragmentAddMenuTagBinding
import com.example.ourmenu.databinding.TagCustomBinding
import com.example.ourmenu.databinding.TagDefaultBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

class AddMenuTagFragment : Fragment() {

    lateinit var binding: FragmentAddMenuTagBinding
    lateinit var bottomSheetBehavior: BottomSheetBehavior<ScrollView>
    lateinit var bottomSheetBehaviorIcon: BottomSheetBehavior<ConstraintLayout>
    var bottomSheetIcon = AddMenuBottomSheetIcon(this, 0)
    var bottomSheetIconStart = 0

    var startChip = 0
    var countChip = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddMenuTagBinding.inflate(inflater, container, false)
        binding.clAddMenuTagTag.requestLayout()
        initBottomSheet()
        binding.flAddMenuChooseTag.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            setBlur()
        }
        binding.flAddMenuTagIcon.setOnClickListener {
            bottomSheetIcon = AddMenuBottomSheetIcon(this, bottomSheetIconStart)
            bottomSheetIcon.show(parentFragmentManager, bottomSheetIcon.tag)
            setBlur()
        }

        return binding.root
    }

    fun initDefaultTag(string: String): TagDefaultBinding {
        var tag = TagDefaultBinding.inflate(layoutInflater)
        tag.tvTagDefaultTag.setText(string)
        return tag
    }

    fun initCustomTag(string: String): TagCustomBinding {
        var tag = TagCustomBinding.inflate(layoutInflater)
        tag.tvTagDefaultTag.setText(string)
        return tag
    }

    fun removeTagFromRoot(view: View) {
        binding.clAddMenuTagTag.removeView(view)
        if (binding.clAddMenuTagTag.childCount == 0) {
            binding.clAddMenuTagTag.visibility = View.GONE
        }
        binding.clAddMenuTagTag.requestLayout()
    }

    fun removeTagFromSheet(view: View) {
        binding.bsAddMenuTag.cgAmbstSelectedTag.removeView(view)
        if (binding.bsAddMenuTag.cgAmbstSelectedTag.childCount == 0) {
            binding.bsAddMenuTag.cgAmbstSelectedTag.visibility = View.GONE
        }
        countChip -= 1
        binding.bsAddMenuTag.cgAmbstSelectedTag.requestLayout()
    }

    fun addDefaultTag(string: String) {
        binding.bsAddMenuTag.cgAmbstSelectedTag.visibility = View.VISIBLE
        var rootTag = initDefaultTag(string)
        var sheetTag = initDefaultTag(string)
        rootTag.ivTagDelete.setOnClickListener {
            removeTagFromRoot(rootTag.root)
            removeTagFromSheet(sheetTag.root)
        }
        sheetTag.ivTagDelete.setOnClickListener {
            removeTagFromRoot(rootTag.root)
            removeTagFromSheet(sheetTag.root)
        }
        binding.clAddMenuTagTag.addView(rootTag.root)
        binding.clAddMenuTagTag.requestLayout()
        binding.bsAddMenuTag.cgAmbstSelectedTag.addView(sheetTag.root)
        binding.bsAddMenuTag.cgAmbstSelectedTag.requestLayout()
        countChip += 1
    }

    fun addCustomTag(string: String) {
        binding.bsAddMenuTag.cgAmbstSelectedTag.visibility = View.VISIBLE
        var rootTag = initCustomTag(string)
        var sheetTag = initCustomTag(string)
        rootTag.ivTagDelete.setOnClickListener {
            removeTagFromRoot(rootTag.root)
            removeTagFromSheet(sheetTag.root)
        }
        sheetTag.ivTagDelete.setOnClickListener {
            removeTagFromRoot(rootTag.root)
            removeTagFromSheet(sheetTag.root)
        }
        binding.clAddMenuTagTag.addView(rootTag.root)
        binding.clAddMenuTagTag.requestLayout()
        binding.bsAddMenuTag.cgAmbstSelectedTag.addView(sheetTag.root)
        binding.bsAddMenuTag.cgAmbstSelectedTag.requestLayout()
        countChip += 1
    }

    fun setBlur() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            binding.clAddMenuTag.setRenderEffect(RenderEffect.createBlurEffect(25f, 25f, Shader.TileMode.CLAMP))
            binding.clAddMenuBlur.alpha = 0.2f
        }
    }

    public fun clearBlur() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            binding.clAddMenuTag.setRenderEffect(null)
        }
        binding.clAddMenuBlur.alpha = 0f
        binding.clAddMenuTag.invalidate()
    }

    fun initBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bsAddMenuTag.root)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN


        val shapeAppearanceModel = ShapeAppearanceModel.Builder()
            .setAllCornerSizes(20f) // 모서리 반지름 설정
            .build()
        val shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel)
        shapeDrawable.setTint(resources.getColor(R.color.Neutral_White))
        binding.bsAddMenuTag.root.background = shapeDrawable

        binding.bsAddMenuTag.etAmbstEnterTag.setOnClickListener {
            binding.bsAddMenuTag.tvAmbstAddTag.visibility = View.VISIBLE
        }
        binding.bsAddMenuTag.root.setOnClickListener {
            binding.bsAddMenuTag.tvAmbstAddTag.visibility = View.INVISIBLE
            requireActivity().currentFocus?.clearFocus()
            closeKeyboard()
        }
        binding.bsAddMenuTag.tvAmbstAddTag.setOnClickListener {
            if (!binding.bsAddMenuTag.etAmbstEnterTag.text.isNullOrEmpty()) {
                addCustomTag(binding.bsAddMenuTag.etAmbstEnterTag.text.toString())
            }
        }

        binding.bsAddMenuTag.btnAmbstReset.setOnClickListener {
            binding.bsAddMenuTag.cgAmbstSelectedTag.removeAllViews()
            binding.bsAddMenuTag.cgAmbstSelectedTag.requestLayout()
            binding.bsAddMenuTag.cgAmbstSelectedTag.visibility = View.GONE
        }

        binding.bsAddMenuTag.btnAmbstConfirm.setOnClickListener {
            binding.clAddMenuTagTag.requestLayout()
            binding.clAddMenuTagTag.visibility = View.VISIBLE
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            clearBlur()
        }

        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        bottomSheetBehavior.isDraggable = false
                    }

                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        bottomSheetBehavior.isDraggable = true
                    }

                    BottomSheetBehavior.STATE_HIDDEN -> {
                        binding.bsAddMenuTag.root.scrollTo(0, 0)
                        startChip = binding.clAddMenuTagTag.childCount
                        countChip = 0
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })

        binding.bsAddMenuTag.ivAmbstBtn.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                clearBlur()
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        binding.root.setOnClickListener {
            if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                clearBlur()
                binding.bsAddMenuTag.root.scrollTo(0, 0)
                if (countChip > 0) {
                    binding.clAddMenuTagTag.removeViewsInLayout(startChip, countChip)
                    binding.clAddMenuTagTag.requestLayout()
                }
            }
            binding.clAddMenuTagTag.removeAllViews()
            closeKeyboard()
            requireActivity().currentFocus?.clearFocus()
        }
        binding.bsAddMenuTag.flAmbstRice.setOnClickListener {
            addDefaultTag(binding.bsAddMenuTag.tvAmbstRice.text.toString())
        }
        binding.bsAddMenuTag.flAmbstBread.setOnClickListener {
            addDefaultTag(binding.bsAddMenuTag.tvAmbstBread.text.toString())
        }
        binding.bsAddMenuTag.flAmbstNoodle.setOnClickListener {
            addDefaultTag(binding.bsAddMenuTag.tvAmbstNoodle.text.toString())
        }
        binding.bsAddMenuTag.flAmbstRice.setOnClickListener {
            addDefaultTag(binding.bsAddMenuTag.tvAmbstRice.text.toString())
        }
    }

    fun closeKeyboard() {
        val inputManager: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            requireActivity().currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}
