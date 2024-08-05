package com.example.ourmenu.community

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ourmenu.R
import com.example.ourmenu.community.write.CommunityWritePostActivity
import com.example.ourmenu.data.PostData
import com.example.ourmenu.databinding.FragmentCommunityBinding
import com.example.ourmenu.mypage.adapter.MypageRVAdapter

class CommunityFragment : Fragment() {

    lateinit var binding: FragmentCommunityBinding
    lateinit var dummyItems: ArrayList<PostData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCommunityBinding.inflate(inflater, container, false)

        initDummyData()
        initListener()
        initRV()



        return binding.root
    }

    private fun initDummyData() {
        dummyItems = ArrayList<PostData>()
        for (i in 1..6) {
            dummyItems.add(
                PostData(
                    "제목",
                    "가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하",
                    R.drawable.menu_sample2,
                    "베터씨",
                    "1 day ago",
                    999,
                    R.drawable.menu_sample3,
                    9,
                ),
            )
        }
    }

    private fun initListener() {
        binding.ivCommunityWrite.setOnClickListener {
            val intent = Intent(context, CommunityWritePostActivity::class.java)
            intent.putExtra("flag", "write")
            startActivity(intent)
        }
    }

    private fun initRV() {
        val adapter =
            MypageRVAdapter(dummyItems) {
                // TODO: 해당 게시물로 이동하기
                val intent = Intent(context, CommunityWritePostActivity::class.java)
                intent.putExtra("postData", it)
                intent.putExtra("flag", "post")
                startActivity(intent)
            }

        binding.rvCommunity.adapter = adapter
    }

}
