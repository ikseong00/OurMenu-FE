package com.example.ourmenu.account

import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.ourmenu.R
import com.example.ourmenu.account.adapters.SpinnerAdapter
import com.example.ourmenu.databinding.FragmentSignupEmailBinding
import com.example.ourmenu.databinding.SpinnerItemBackgroundEditBinding


class SignupEmailFragment : Fragment() {
    lateinit var binding: FragmentSignupEmailBinding
    var idflag = false
    var adflag = false
    var ddflag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSignupEmailBinding.inflate(inflater, container, false)
        /*val bindingEdit = SpinnerItemBackgroundEditBinding.inflate(inflater, container, false)
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
*/

        /*binding.etSignupEmail.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                val imm : InputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(
                    requireActivity().currentFocus?.windowToken,0
                )
                binding.etSignupEmail.setText("직접 입력하기")
                binding.etSignupEmail.isCursorVisible = false
                binding.llSignupEmailDropdownParent.visibility = View.VISIBLE
            }
            else{
                binding.llSignupEmailDropdownParent.visibility = View.GONE
            }
        }*/
        initDropdown()
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

        binding.etSignupEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    adflag = true
                    flagCheck()
                } else {
                    adflag = false
                    flagCheck()
                }
            }
        })

        binding.root.setOnClickListener {
            if (activity != null && requireActivity().currentFocus != null) {
                closeKeyboard()
                closeDropdown()
                requireActivity().currentFocus?.clearFocus()
            }else if(binding.llSignupEmailDropdownParent.visibility == View.VISIBLE){
                closeDropdown()
                requireActivity().currentFocus?.clearFocus()
            }else{
                closeKeyboard()
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

    fun closeDropdown(){
        binding.llSignupEmailDropdownParent.visibility = View.INVISIBLE
        ddflag = false
        binding.ivSignupDropdown.setImageResource(R.drawable.ic_polygon_down)
    }

    fun closeKeyboard(){
        val inputManager: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            requireActivity().currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    fun initDropdown(){
        binding.etSignupEmail.setOnClickListener{
            binding.etSignupEmail.isCursorVisible = true
        }

        binding.ivSignupDropdown.setOnClickListener {
            if(!ddflag){
                closeKeyboard()
                binding.etSignupEmail.isFocusable = true
                binding.etSignupEmail.requestFocus()
                binding.etSignupEmail.isCursorVisible = false
                binding.llSignupEmailDropdownParent.visibility = View.VISIBLE
                binding.ivSignupDropdown.setImageResource(R.drawable.ic_polygon_up)
                ddflag = true
            }else if (ddflag){
                closeDropdown()
                requireActivity().currentFocus?.clearFocus()
            }
        }
        binding.tvSignupEmailDaum.setOnClickListener {
            binding.etSignupEmail.setText(binding.tvSignupEmailDaum.text)
            closeDropdown()
        }
        binding.tvSignupEmailGmail.setOnClickListener {
            binding.etSignupEmail.setText(binding.tvSignupEmailGmail.text)
            closeDropdown()
        }
        binding.tvSignupEmailNate.setOnClickListener {
            binding.etSignupEmail.setText(binding.tvSignupEmailNate.text)
            closeDropdown()
        }
        binding.tvSignupEmailNaver.setOnClickListener {
            binding.etSignupEmail.setText(binding.tvSignupEmailNaver.text)
            closeDropdown()
        }
        binding.tvSignupEmailKakao.setOnClickListener {
            binding.etSignupEmail.setText(binding.tvSignupEmailKakao.text)
            closeDropdown()
        }
        binding.tvSignupEmailSelf.setOnClickListener {
            binding.etSignupEmail.setText(binding.tvSignupEmailSelf.text)
            closeDropdown()
        }
    }
}
