package com.example.ourmenu.menu.menuFolder

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import com.example.ourmenu.R
import com.example.ourmenu.community.adapter.CommunityFilterSpinnerAdapter
import com.example.ourmenu.databinding.FragmentMenuFolderDetailAllBinding
import com.example.ourmenu.menu.adapter.MenuFolderAllFilterSpinnerAdapter
import com.google.android.material.chip.Chip

class MenuFolderDetailAllFragment : Fragment() {

    lateinit var binding: FragmentMenuFolderDetailAllBinding
    var chipsItem = ArrayList<Chip>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMenuFolderDetailAllBinding.inflate(layoutInflater)


        initChips()
        initSpinner()
        initListener()




        return binding.root
    }

    private fun initChips() {
        if (chipsItem.size > 0) {
            for (i in 0 until chipsItem.size) {
                // 부모 뷰에서 제거 후 붙이기
                val parent = chipsItem[i].parent as ViewGroup
                parent.removeView(chipsItem[i])
                binding.cgMfda.addView(
                    chipsItem[i]
                )
            }
        }

        // TODO 가격 칩 추가하기
    }

    private fun initSpinner() {
        val adapter =
            MenuFolderAllFilterSpinnerAdapter<String>(requireContext(), arrayListOf("이름순", "등록순", "가격순"))
        adapter.setDropDownViewResource(R.layout.spinner_item_background)
        binding.spnMfdaFilter.adapter = adapter
        binding.spnMfdaFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                adapter.selectedPos = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

    private fun initListener() {

        binding.chipMfdaAll.setOnClickListener {

            val menuFolderDetailAllFilterFragment = MenuFolderDetailAllFilterFragment(this)
            if (chipsItem.size > 0) {
                val bundle = Bundle()
                for (i in 0 until chipsItem.size) {
                    bundle.putString("chip$i", chipsItem[i].text.toString())
                }
                menuFolderDetailAllFilterFragment.arguments = bundle
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.menu_folder_frm, menuFolderDetailAllFilterFragment)
                .addToBackStack("MenuFolderDetailAllFragment")
                .commit()
        }
    }

    fun addChips(context: Context, chips: ArrayList<Chip>) {
        for (i in 0 until chips.size) {
            chips[i].chipBackgroundColor = ContextCompat.getColorStateList(context, R.color.Neutral_White)
            chips[i].chipIconTint = ContextCompat.getColorStateList(context, R.color.Neutral_Black)
            chips[i].setTextColor(ContextCompat.getColorStateList(context, R.color.Neutral_Black))
        }
        chipsItem = chips
    }


}
