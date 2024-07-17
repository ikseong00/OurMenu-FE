package com.example.ourmenu.addMenu

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ourmenu.addMenu.adapter.AddMenuRVAdapter
import com.example.ourmenu.data.AddMenuSearchResultData
import com.example.ourmenu.databinding.FragmentAddMenuMapBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class AddMenuMapFragment : Fragment() {
    lateinit var binding: FragmentAddMenuMapBinding
    lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    lateinit var adapter: AddMenuRVAdapter
    private lateinit var allItems: ArrayList<AddMenuSearchResultData>
    private lateinit var recentItems: ArrayList<AddMenuSearchResultData>

    private var isKeyboardVisible = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddMenuMapBinding.inflate(inflater, container, false)

        bottomSheetBehavior = BottomSheetBehavior.from(binding.clAddMenuBottomSheet)

        initDummyData()
        initResultRV()

        //  키보드 상태 변화 감지해서 화면 길이 조절하기
        binding.root.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            binding.root.getWindowVisibleDisplayFrame(rect)
            val screenHeight = binding.root.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            if (keypadHeight > screenHeight * 0.15) {
                isKeyboardVisible = true
            } else {
                //  키보드 안보일 때
                if (isKeyboardVisible) {
                    isKeyboardVisible = false
                    adjustLayoutForKeyboardDismiss()
                }
            }
        }

        // bottom sheet 바깥을 클릭했을 때 bottom sheet 숨기기
        binding.root.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            }
        }

        return binding.root
    }

    private fun initResultRV() {
        adapter =
            AddMenuRVAdapter(arrayListOf()) { place ->
                showPlaceDetails(place)
                returnToMap()
            }
        binding.rvAddMenuSearchResults.layoutManager = LinearLayoutManager(context)
        binding.rvAddMenuSearchResults.adapter = adapter

        // 검색바 focus됐을 때
        binding.etAddMenuSearch.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.vAddMenuSearchBg.visibility = View.VISIBLE
                binding.rvAddMenuSearchResults.visibility = View.VISIBLE
                adapter.updateItems(recentItems)
            } else {
                binding.vAddMenuSearchBg.visibility = View.GONE
                binding.rvAddMenuSearchResults.visibility = View.GONE
            }
        }

        // 검색했을 때 결과 표시
        binding.etAddMenuSearch.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int,
                ) {
                    if (s.isNullOrEmpty()) {
                        adapter.updateItems(recentItems)
                    } else {
                        val filteredItems =
                            ArrayList(
                                allItems.filter {
                                    it.placeName.contains(s, ignoreCase = true)
                                },
                            )
                        adapter.updateItems(filteredItems)
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            },
        )
    }

    private fun initDummyData() {
        allItems =
            arrayListOf(
                AddMenuSearchResultData("아워떡볶이 1", "서울 마포구 와우산로 112 1", "음식점 1", "1km", true),
                AddMenuSearchResultData("아워떡볶이 2", "서울 마포구 와우산로 112 2", "음식점 2", "2km", false),
                AddMenuSearchResultData("아워떡볶이 3", "서울 마포구 와우산로 112 3", "음식점 3", "3km", true),
            )
        recentItems = ArrayList(allItems.filter { it.recentSearch })
    }

    private fun returnToMap() {
        binding.rvAddMenuSearchResults.visibility = View.GONE
        binding.etAddMenuSearch.clearFocus()
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etAddMenuSearch.windowToken, 0)
    }

    private fun adjustLayoutForKeyboardDismiss() {
        binding.root.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        binding.root.requestLayout()
    }

    private fun showPlaceDetails(item: AddMenuSearchResultData) {
//        binding.tvPlaceName.text = place.name
//        binding.tvPlaceAddress.text = place.address

//        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

        // 키보드 숨기기
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etAddMenuSearch.windowToken, 0)

        // 키보드가 사라질 때 Bottom Sheet가 화면의 가장 아래에 위치하도록
        binding.clAddMenuBottomSheet.postDelayed({
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }, 100)
    }
}
