package com.example.ourmenu.account

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.ourmenu.R
import com.example.ourmenu.account.adapters.SpinnerAdapter
import com.example.ourmenu.databinding.FragmentSignupEmailBinding
import com.example.ourmenu.databinding.SpinnerItemBackgroundEditBinding


class SignupEmailFragment : Fragment() {
    lateinit var binding: FragmentSignupEmailBinding
    var idflag = false
    var adflag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSignupEmailBinding.inflate(inflater, container, false)
        val bindingEdit = SpinnerItemBackgroundEditBinding.inflate(inflater, container, false)
        val adapter = SpinnerAdapter<String>(requireContext(), this)
        adapter.initListener()
        adapter.setDropDownViewResource(R.layout.spinner_item_background)
        binding.spnSignupEmail.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 1 || position == 0) {
                    adflag = false
                    flagCheck()
                } else {
                    adflag = true
                    flagCheck()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                adflag = false
            }

        }
        binding.spnSignupEmail.adapter = adapter
        bindingEdit.tvSpinnerItemBackgroundEdit.setOnClickListener {
            bindingEdit.tvSpinnerItemBackgroundEdit.isFocusedByDefault = true
            bindingEdit.flSpinnerItemBackgroundEdit.isFocusableInTouchMode = true
            bindingEdit.tvSpinnerItemBackgroundEdit.isFocusableInTouchMode = true
            bindingEdit.flSpinnerItemBackgroundEdit.requestFocus()
        }

        binding.btnSignupEmail.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack("SignupEmail")
                .replace(R.id.cl_mainscreen, SignupEmailCertifyFragment())
                .commit()
        }
        binding.etSignupEmailId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    idflag = true
                    flagCheck()
                } else {
                    idflag = false
                    flagCheck()
                }
            }
        })

        binding.root.setOnClickListener {
            if (activity != null && requireActivity().currentFocus != null) {
                val inputManager: InputMethodManager =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(
                    requireActivity().currentFocus?.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
                requireActivity().currentFocus?.clearFocus()
            }
        }
        return binding.root
    }

    fun flagCheck() {
        if (idflag and adflag) {
            binding.btnSignupEmail.setEnabled(true)
        } else {
            binding.btnSignupEmail.setEnabled(false)
        }
    }
}
