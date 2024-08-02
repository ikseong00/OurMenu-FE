package com.example.ourmenu.addMenu

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ourmenu.AddMenuSelectMenuFragment
import com.example.ourmenu.R
import com.example.ourmenu.addMenu.adapter.AddMenuSearchResultRVAdapter
import com.example.ourmenu.data.PlaceInfoData
import com.example.ourmenu.data.PlaceMenuData
import com.example.ourmenu.databinding.FragmentAddMenuMapBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class AddMenuMapFragment : Fragment() {
    lateinit var binding: FragmentAddMenuMapBinding
    lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    lateinit var resultAdapter: AddMenuSearchResultRVAdapter

    private lateinit var placeItems: ArrayList<PlaceInfoData>
    private lateinit var recentPlaceItems: ArrayList<PlaceInfoData>
    private lateinit var placeMenuItems: ArrayList<PlaceMenuData>
    private lateinit var placeMenuImgItems: ArrayList<Int>

    private var isKeyboardVisible = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddMenuMapBinding.inflate(inflater, container, false)

        bottomSheetBehavior = BottomSheetBehavior.from(binding.clAddMenuBottomSheet)

        // BottomSheet의 초기 상태를 숨김으로 설정
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

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

        // BottomSheet 상태 변화 리스너 설정
        bottomSheetBehavior.addBottomSheetCallback(
            object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(
                    bottomSheet: View,
                    newState: Int,
                ) {
                }

                override fun onSlide(
                    bottomSheet: View,
                    slideOffset: Float,
                ) {
                }
            },
        )

        // bottom sheet 바깥을 클릭했을 때 bottom sheet 숨기기
        binding.root.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                binding.etAddMenuSearch.text.clear() // 입력 필드 비우기 추가

                // TODO: 지도에서 focus 사라지도록?
            }
        }

        // bottom sheet를 클릭했을 때 위로 올라오도록 설정
        binding.clAddMenuBottomSheet.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        // 뒤로가기 버튼 클릭 이벤트 처리
        binding.ivAddMenuLogoBack.setOnClickListener {
            binding.etAddMenuSearch.text.clear() // 입력 필드 비우기 추가
            handleBackPress()
        }

        // 검색바 focus됐을 때
        binding.etAddMenuSearch.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.vAddMenuSearchBg.visibility = View.VISIBLE
                binding.fcvAddMenuMap.visibility = View.GONE
                binding.rvAddMenuSearchResults.visibility = View.VISIBLE
                binding.clAddMenuRecentSearch.visibility = View.VISIBLE
                resultAdapter.updateItems(recentPlaceItems)
                binding.etAddMenuSearch.text.clear() // 입력 필드 비우기

                // bottom sheet가 떠있는 상태에서 검색바를 클릭하면 bottom sheet가 사라지도록
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            } else {
                binding.vAddMenuSearchBg.visibility = View.GONE
                binding.fcvAddMenuMap.visibility = View.VISIBLE
                binding.rvAddMenuSearchResults.visibility = View.GONE
                binding.clAddMenuRecentSearch.visibility = View.GONE
            }
        }

        // 검색바에서 엔터 키 이벤트 처리
        binding.etAddMenuSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
            ) {
                performSearch(binding.etAddMenuSearch.text.toString())
                true
            } else {
                false
            }
        }

        // 검색 결과 없을 때 버튼 이벤트 처리
        binding.btnAddMenuNoResult.setOnClickListener {
            binding.etAddMenuSearch.text.clear() // 입력 필드 비우기 추가

            // 키보드 숨기기
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.etAddMenuSearch.windowToken, 0)

            parentFragmentManager
                .beginTransaction()
                .addToBackStack("AddMenuMap")
                .replace(R.id.cl_add_menu_main, AddMenuNameFragment())
                .commit()
        }

        binding.btnAddMenuNext.setOnClickListener {
            binding.etAddMenuSearch.text.clear() // 입력 필드 비우기 추가

            parentFragmentManager
                .beginTransaction()
                .addToBackStack("AddMenuMap")
                .replace(R.id.cl_add_menu_main, AddMenuSelectMenuFragment())
                .commit()
        }

        return binding.root
    }

    private fun initResultRV() {
        resultAdapter =
            AddMenuSearchResultRVAdapter(arrayListOf()) { place ->
                showPlaceDetails(place)
                binding.etAddMenuSearch.setText(place.placeName) // 검색 결과 item의 placeName으로 input field 설정
                returnToMap()
            }
        binding.rvAddMenuSearchResults.layoutManager = LinearLayoutManager(context)
        binding.rvAddMenuSearchResults.adapter = resultAdapter
    }

    private fun initDummyData() {
        placeMenuImgItems = arrayListOf(R.drawable.menu_sample, R.drawable.menu_sample2, R.drawable.menu_sample3)

        placeMenuItems =
            arrayListOf(
                PlaceMenuData("마라탕", "14,000원"),
                PlaceMenuData("마라샹궈", "20,000원"),
                PlaceMenuData("꿔바로우", "12,000원"),
                PlaceMenuData("일반 떡볶이", "14,000원"),
                PlaceMenuData("로제 떡볶이", "15,000원"),
                PlaceMenuData("짜장 떡볶이", "13,000원"),
                PlaceMenuData("야채김밥", "4,000원"),
                PlaceMenuData("참치김밥", "5,000원"),
                PlaceMenuData("김치김밥", "5,000원"),
            )

        placeItems =
            arrayListOf(
                PlaceInfoData(
                    "건대 마라",
                    "음식점",
                    "10km",
                    true,
                    "서울 마포구 와우산로 112 1",
                    "매일 10:00 - 21:00",
                    placeMenuImgItems,
                    placeMenuItems,
                ),
                PlaceInfoData(
                    "아워 떡볶이",
                    "음식점",
                    "8km",
                    false,
                    "서울 마포구 와우산로 112 2",
                    "매일 10:00 - 21:00",
                    placeMenuImgItems,
                    placeMenuItems,
                ),
                PlaceInfoData(
                    "쿠잇 분식점",
                    "음식점",
                    "7km",
                    true,
                    "서울 마포구 와우산로 112 3",
                    "매일 10:00 - 21:00",
                    placeMenuImgItems,
                    placeMenuItems,
                ),
            )

        recentPlaceItems = ArrayList(placeItems.filter { it.recentSearch })
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

    private fun showPlaceDetails(item: PlaceInfoData) {
        binding.tvAddMenuBsPlaceName.text = item.placeName
        binding.tvAddMenuBsAddress.text = item.address
        binding.tvAddMenuBsTime.text = item.time
        binding.sivAddMenuBsImg1.setImageResource(item.imgs[0])
        binding.sivAddMenuBsImg2.setImageResource(item.imgs[1])
        binding.sivAddMenuBsImg3.setImageResource(item.imgs[2])

        // 키보드 숨기기
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etAddMenuSearch.windowToken, 0)

        // 키보드가 사라질 때 Bottom Sheet가 화면의 가장 아래에 위치하도록
        binding.clAddMenuBottomSheet.postDelayed({
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }, 100)
    }

    private fun handleBackPress() {
        if (binding.vAddMenuSearchBg.visibility == View.VISIBLE ||
            binding.rvAddMenuSearchResults.visibility == View.VISIBLE
        ) {
            // 검색 화면이 보일 때 -> 지도 화면으로 전환
            binding.vAddMenuSearchBg.visibility = View.GONE
            binding.fcvAddMenuMap.visibility = View.VISIBLE
            binding.rvAddMenuSearchResults.visibility = View.GONE
            binding.clAddMenuRecentSearch.visibility = View.GONE
            binding.etAddMenuSearch.clearFocus()
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.etAddMenuSearch.windowToken, 0)
        } else {
            // 지도 화면이 보일 때 -> 이전 화면으로 돌아가기
            requireActivity().onBackPressed()
        }
    }

    private fun performSearch(query: String) {
        if (query.isEmpty()) {
            binding.rvAddMenuSearchResults.visibility = View.GONE
            binding.clAddMenuNoResult.visibility = View.VISIBLE
        } else {
            binding.clAddMenuRecentSearch.visibility = View.GONE
            val filteredItems = ArrayList(placeItems.filter { it.placeName.contains(query, ignoreCase = true) })
            resultAdapter.updateItems(filteredItems)

            if (filteredItems.isEmpty()) {
                binding.clAddMenuNoResult.visibility = View.VISIBLE
            } else {
                binding.clAddMenuNoResult.visibility = View.GONE

                // 검색 결과 화면 표시
                binding.vAddMenuSearchBg.visibility = View.VISIBLE
                binding.fcvAddMenuMap.visibility = View.GONE
                binding.rvAddMenuSearchResults.visibility = View.VISIBLE
                binding.clAddMenuRecentSearch.visibility = View.GONE
            }
        }
    }
}
