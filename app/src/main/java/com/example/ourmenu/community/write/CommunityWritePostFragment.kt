package com.example.ourmenu.community.write

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ourmenu.R
import com.example.ourmenu.community.write.adapter.CommunityWritePostRVAdapter
import com.example.ourmenu.data.HomeMenuData
import com.example.ourmenu.databinding.FragmentCommunityWritePostBinding
import kotlin.math.max

class CommunityWritePostFragment : Fragment() {

    lateinit var binding: FragmentCommunityWritePostBinding
    lateinit var rvAdapter: CommunityWritePostRVAdapter
    lateinit var dummyData: ArrayList<HomeMenuData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCommunityWritePostBinding.inflate(layoutInflater)

        initDummy()
        initRV()

        return binding.root
    }

    private fun initDummy() {
        dummyData = arrayListOf<HomeMenuData>()
    }

    private fun initRV() {
        rvAdapter = CommunityWritePostRVAdapter(dummyData, requireContext()) {
            parentFragmentManager.beginTransaction()
                .replace(R.id.community_post_frm, CommunityWritePostGetFragment(this))
                .addToBackStack("CommunityWritePostFragment")
                .commitAllowingStateLoss()
        }
        binding.rvCommunityPost.adapter = rvAdapter

        binding.rvCommunityPost.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val firstPos =
                        (binding.rvCommunityPost.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                    val secondPos =
                        (binding.rvCommunityPost.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    val selectedPos = max(firstPos, secondPos)

                    if (selectedPos != -1) {
                        val viewItem =
                            (binding.rvCommunityPost.layoutManager as LinearLayoutManager)
                                .findViewByPosition(selectedPos)

                        viewItem?.run {
                            val itemMargin = (binding.rvCommunityPost.measuredWidth - viewItem.measuredWidth) / 2
                            binding.rvCommunityPost.smoothScrollBy(this.x.toInt() - itemMargin, 0)
                        }
                    }
                }
            }
        })
    }

}
