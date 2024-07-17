package com.example.ourmenu.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.withStyledAttributes
import com.example.ourmenu.adapters.SpinnerAdapter
import androidx.fragment.app.Fragment
import com.example.ourmenu.R
import com.example.ourmenu.databinding.FragmentSignupEmailBinding
import com.example.ourmenu.databinding.SpinnerItemBackgroundBinding
import com.example.ourmenu.databinding.SpinnerItemBackgroundEditBinding

class SignupEmailFragment : Fragment() {
    lateinit var binding: FragmentSignupEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        fun bgchange(){
//            binding.spnEmail.setBackgroundResource(R.drawable.spinner_shape_upsidedown)
        }
        fun bgchange2(){
            binding.spnEmail.setBackgroundResource(R.drawable.spinner_shape_default)
        }
        binding = FragmentSignupEmailBinding.inflate(inflater, container, false)
        val bindingEdit = SpinnerItemBackgroundEditBinding.inflate(inflater,container,false)
        val adapter = SpinnerAdapter<String>(requireContext())
        adapter.initListener()
        adapter.setDropDownViewResource(R.layout.spinner_item_background)
        binding.spnEmail.adapter = adapter
        bindingEdit.tvSpinnerItemBackgroundEdit.setOnClickListener{
            bindingEdit.tvSpinnerItemBackgroundEdit.isFocusedByDefault = true
            bindingEdit.flSpinnerItemBackgroundEdit.isFocusableInTouchMode = true
            bindingEdit.tvSpinnerItemBackgroundEdit.isFocusableInTouchMode = true
            bindingEdit.flSpinnerItemBackgroundEdit.requestFocus()
        }
        /*binding.spnEmail.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val binding = SpinnerItemBackgroundBinding.inflate(inflater,container,false)
                binding.tvSpinnerItemBackground.setBackgroundResource(R.drawable.spinner_shape_upsidedown)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                val binding = SpinnerItemBackgroundBinding.inflate(inflater,container,false)
                binding.tvSpinnerItemBackground.setBackgroundResource(R.drawable.spinner_shape_upsidedown)
            }
        }*/

        binding.btnSignupEmail.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .addToBackStack("SignupEmail")
                .replace(R.id.cl_mainscreen,SignupEmailSertifyFragment())
                .commit()
        }

        return binding.root
    }
}
