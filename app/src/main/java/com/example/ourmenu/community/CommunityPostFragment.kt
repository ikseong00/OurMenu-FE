package com.example.ourmenu.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.ourmenu.R
import com.example.ourmenu.community.adapter.CommunityPostRVAdapter
import com.example.ourmenu.data.HomeMenuData
import com.example.ourmenu.data.PostData
import com.example.ourmenu.databinding.CommunityDeleteDialogBinding
import com.example.ourmenu.databinding.CommunityKebabBottomSheetDialogBinding
import com.example.ourmenu.databinding.CommunityReportDialogBinding
import com.example.ourmenu.databinding.FragmentCommunityPostBinding
import com.example.ourmenu.util.Utils.applyBlurEffect
import com.example.ourmenu.util.Utils.dpToPx
import com.example.ourmenu.util.Utils.removeBlurEffect
import com.example.ourmenu.util.Utils.showToast
import com.google.android.material.bottomsheet.BottomSheetDialog

class CommunityPostFragment(val isMine: Boolean) : Fragment() {

    lateinit var binding: FragmentCommunityPostBinding
    lateinit var dummyItems: ArrayList<HomeMenuData>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCommunityPostBinding.inflate(layoutInflater)

        initDummy()
        initBundle()
        initListener()
        initRV()


        return binding.root
    }

    private fun initDummy() {
        dummyItems = ArrayList<HomeMenuData>()
        for (i in 1..6) {
            dummyItems.add(
                HomeMenuData(
                    "제목",
                    "화산라멘",
                    "화산점",
                    R.drawable.menu_sample
                ),
            )
        }
    }

    private fun initRV() {
        val adapter = CommunityPostRVAdapter(
            dummyItems,
            requireContext(),
            onDeleteClick = {
                // TODO 저장 API
                showSaveDialog()
            },
            onSaveClick = {
                // TODO 삭제 API
            }
        )
    }


    private fun initBundle() {
        val postData = arguments?.getSerializable("postData") as PostData
        postData.let {
            binding.sivCommunityPostProfileImage.setImageResource(it.profileImg)
            binding.etCommunityPostTitle.hint = it.title
            binding.tvCommunityPostName.text = it.username
            binding.etCommunityPostContent.hint = it.content
        }
    }

    private fun initListener() {
        binding.ivCommunityPostBack.setOnClickListener {
            requireActivity().finish()
        }

        binding.ivCommunityPostKebab.setOnClickListener {
            showKebabDialog()
        }

        binding.btnCommunityPostOk.setOnClickListener {
            showToast(requireContext(), R.drawable.ic_complete, "게시글이 수정되었어요!")
            // TODO 게시글 수정하기
            setEdit(false)
        }
    }


    private fun setEdit(isEdit: Boolean) {
        if (isEdit) {
            binding.clCommunityPostProfile.visibility = View.GONE
            binding.btnCommunityPostOk.visibility = View.VISIBLE
            binding.ivCommunityPostKebab.visibility = View.GONE
        } else {
            binding.clCommunityPostProfile.visibility = View.VISIBLE
            binding.btnCommunityPostOk.visibility = View.GONE
            binding.ivCommunityPostKebab.visibility = View.VISIBLE
        }
    }

    // 저장하기
    private fun showSaveDialog() {
        TODO("Not yet implemented")
    }

    // kebab 버튼 클릭
    private fun showKebabDialog() {
        val bottomSheetDialog: BottomSheetDialog
        // 내 글 일때
        if (isMine) {
            val dialogBinding = CommunityKebabBottomSheetDialogBinding.inflate(LayoutInflater.from(context))
            bottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheetDialog.setContentView(dialogBinding.root)

            // 수정하기
            dialogBinding.btnCkbsgEdit.setOnClickListener {
                bottomSheetDialog.dismiss()
                setEdit(true)
            }

            // 삭제하기
            dialogBinding.btnCkbsgDelete.setOnClickListener {
                bottomSheetDialog.dismiss()
                showDeleteDialog()
            }

            dialogBinding.btnCkbsgCancel.setOnClickListener {
                bottomSheetDialog.dismiss()
            }

            // 흐린 배경 제거
            bottomSheetDialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

            bottomSheetDialog.show()
        } else {
            // 남의 글 일때
            val dialogBinding = CommunityReportDialogBinding.inflate(LayoutInflater.from(context))
            bottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheetDialog.setContentView(dialogBinding.root)

            // 신고하기
            dialogBinding.btnCrdReport.setOnClickListener {
                bottomSheetDialog.dismiss()
                showReportDialog()
            }

            dialogBinding.btnCrdCancel.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
        }
        // 흐린 배경 제거
        bottomSheetDialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

        bottomSheetDialog.show()
    }

    private fun showReportDialog() {
        val rootView = (activity?.window?.decorView as? ViewGroup)?.getChildAt(0) as? ViewGroup
        // 블러 효과 추가
        rootView?.let { applyBlurEffect(it) }

        val dialogBinding = CommunityReportDialogBinding.inflate(LayoutInflater.from(context))
        val deleteDialog =
            android.app.AlertDialog
                .Builder(requireContext())
                .setView(dialogBinding.root)
                .create()

        deleteDialog.setOnShowListener {
            val window = deleteDialog.window
            window?.setBackgroundDrawableResource(android.R.color.transparent)

            val params = window?.attributes
            params?.width = dpToPx(requireContext(), 288)
            params?.height = WindowManager.LayoutParams.WRAP_CONTENT
            window?.attributes = params
        }

        // dialog 사라지면 블러효과도 같이 사라짐
        deleteDialog.setOnDismissListener {
            rootView?.let { removeBlurEffect(it) }
        }

        dialogBinding.ivCrdClose.setOnClickListener {
            deleteDialog.dismiss()
        }

        dialogBinding.btnCrdReport.setOnClickListener {
            // TODO: 게시글 신고 API
            deleteDialog.dismiss()
            showToast(requireContext(), R.drawable.ic_complete, "게시글이 삭제되었어요!")
        }

        dialogBinding.btnCrdCancel.setOnClickListener {
            deleteDialog.dismiss()
        }

        deleteDialog.show()
    }

    // kebab -> 삭제하기
    private fun showDeleteDialog() {
        val rootView = (activity?.window?.decorView as? ViewGroup)?.getChildAt(0) as? ViewGroup
        // 블러 효과 추가
        rootView?.let { applyBlurEffect(it) }

        val dialogBinding = CommunityDeleteDialogBinding.inflate(LayoutInflater.from(context))
        val deleteDialog =
            android.app.AlertDialog
                .Builder(requireContext())
                .setView(dialogBinding.root)
                .create()

        deleteDialog.setOnShowListener {
            val window = deleteDialog.window
            window?.setBackgroundDrawableResource(android.R.color.transparent)

            val params = window?.attributes
            params?.width = dpToPx(requireContext(), 288)
            params?.height = WindowManager.LayoutParams.WRAP_CONTENT
            window?.attributes = params
        }

        // dialog 사라지면 블러효과도 같이 사라짐
        deleteDialog.setOnDismissListener {
            rootView?.let { removeBlurEffect(it) }
        }

        dialogBinding.ivCddClose.setOnClickListener {
            deleteDialog.dismiss()
        }

        dialogBinding.btnCddDelete.setOnClickListener {
            // TODO: 게시글 삭제 API
            deleteDialog.dismiss()
            showToast(requireContext(), R.drawable.ic_complete, "게시글이 삭제되었어요!")
        }

        dialogBinding.btnCddCancel.setOnClickListener {
            deleteDialog.dismiss()
        }

        deleteDialog.show()
    }
}
