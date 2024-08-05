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
    lateinit var bottomSheetBehavior: BottomSheetBehavior<ScrollView> //바텀시트 상태 나타내는 변수
    var bottomSheetIcon = AddMenuBottomSheetIcon(this, 0)
    var bottomSheetIconStart = 0 //바텀시트 상태 저장. 다이얼로그로 작성된 바텀시트는 매번 새로 생성되어서 상태를 따로 저장해주어야한다.

    //아래는 둘다 바텀시드 태그의 상태 저장용
    var startChip = 0
    var countChip = 0 //추가된 태그의 개수를 센다.
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
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED //미리 설정한 바텀시트 고정 높이만큼 올라온다
            setBlur()
        }
        binding.flAddMenuTagIcon.setOnClickListener {
            //매번 객체를 새로 생성해주지 않으면 이미 dismiss된 다이얼로그를 보여주려한다고 오류난다.
            bottomSheetIcon = AddMenuBottomSheetIcon(this, bottomSheetIconStart)
            bottomSheetIcon.show(parentFragmentManager, bottomSheetIcon.tag)
            setBlur()
        }

        return binding.root
    }

    //기본 태그 설정
    fun initDefaultTag(string: String): TagDefaultBinding {
        var tag = TagDefaultBinding.inflate(layoutInflater)
        tag.tvTagDefaultTag.setText(string)
        return tag
    }

    //커스텀 태그 설정
    fun initCustomTag(string: String): TagCustomBinding {
        var tag = TagCustomBinding.inflate(layoutInflater)
        tag.tvTagDefaultTag.setText(string)
        return tag
    }

    //태그를 바탕에서 제거
    fun removeTagFromRoot(view: View) {
        binding.clAddMenuTagTag.removeView(view)
        if (binding.clAddMenuTagTag.childCount == 0) {
            binding.clAddMenuTagTag.visibility = View.GONE
        }
        binding.clAddMenuTagTag.requestLayout()
    }

    //태그를 바텀시트에서 제거
    fun removeTagFromSheet(view: View) {
        binding.bsAddMenuTag.cgAmbstSelectedTag.removeView(view)
        if (binding.bsAddMenuTag.cgAmbstSelectedTag.childCount == 0) {
            binding.bsAddMenuTag.cgAmbstSelectedTag.visibility = View.GONE
        }
        countChip -= 1
        binding.bsAddMenuTag.cgAmbstSelectedTag.requestLayout()
    }

    //기본 태그를 추가
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

    //커스텀 태그를 추가
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

    //화면 블러처리
    fun setBlur() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            binding.clAddMenuTag.setRenderEffect(RenderEffect.createBlurEffect(25f, 25f, Shader.TileMode.CLAMP))
            binding.clAddMenuBlur.alpha = 0.2f
        }
    }

    //화면 블러 해제
    public fun clearBlur() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            binding.clAddMenuTag.setRenderEffect(null)
        }
        binding.clAddMenuBlur.alpha = 0f
        binding.clAddMenuTag.invalidate()
    }

    //바텀시트 이벤트 및 레이아웃 초기화
    fun initBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bsAddMenuTag.root)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        //아래처럼 하지 않으면 모서리모양이 제대로 적용되지 않았음.
        val shapeAppearanceModel = ShapeAppearanceModel.Builder()
            .setAllCornerSizes(20f) // 모서리 반지름 설정
            .build()
        val shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel)
        shapeDrawable.setTint(resources.getColor(R.color.Neutral_White))
        binding.bsAddMenuTag.root.background = shapeDrawable

        //태그 직접 입력 창 눌렀을때 입력 버튼 활성화. 2번 눌러야 활성화 되던데 왜그런지 모르겠다.
        binding.bsAddMenuTag.etAmbstEnterTag.setOnClickListener {
            binding.bsAddMenuTag.tvAmbstAddTag.visibility = View.VISIBLE
        }

        //아래 클릭 리스너도 제대로 작동 안된다. 이유 모르겠음.
        binding.bsAddMenuTag.root.setOnClickListener {
            binding.bsAddMenuTag.tvAmbstAddTag.visibility = View.INVISIBLE
            requireActivity().currentFocus?.clearFocus()
            closeKeyboard()
        }

        //입력 버튼 누르면 커스텀 태그 추가
        binding.bsAddMenuTag.tvAmbstAddTag.setOnClickListener {
            if (!binding.bsAddMenuTag.etAmbstEnterTag.text.isNullOrEmpty()) {
                addCustomTag(binding.bsAddMenuTag.etAmbstEnterTag.text.toString())
            }
        }

        //리셋 버튼 누르면 태그 전부 사라짐
        binding.bsAddMenuTag.btnAmbstReset.setOnClickListener {
            binding.bsAddMenuTag.cgAmbstSelectedTag.removeAllViews()
            binding.bsAddMenuTag.cgAmbstSelectedTag.requestLayout() //뷰 새로고침
            binding.bsAddMenuTag.cgAmbstSelectedTag.visibility = View.GONE
        }

        //확인 버튼 누르면 태그 적용된채로 바텀시트 내려감
        binding.bsAddMenuTag.btnAmbstConfirm.setOnClickListener {
            binding.clAddMenuTagTag.requestLayout()
            binding.clAddMenuTagTag.visibility = View.VISIBLE
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            clearBlur()
        }

        //바텀시트가 최대 크기일때는 드래그 안됨(스크롤만됨)
        //바텀시트가 중간 크기일때는 드래그 됨
        //바텀시트 가려질시 스크롤 및 기타 초기화
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

        //바텀시트 상단 회색 가로버튼 클릭시 바텀시트 바로 올라감, 사라짐
        binding.bsAddMenuTag.ivAmbstBtn.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                clearBlur()
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        //바탕 클릭 시 바텀시트 들어감
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
        //바텀시트에서 선택 가능한 태그 눌렀을때 바텀시트에서 태그 추가
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
        //todo 남은 선택 가능한 태그도 clickListener 설정 필요
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
