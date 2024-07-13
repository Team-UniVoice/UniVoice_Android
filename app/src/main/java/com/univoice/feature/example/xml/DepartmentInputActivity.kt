package com.univoice.feature.example.xml

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityDepartmentInputBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DepartmentInputActivity : BindingActivity<ActivityDepartmentInputBinding>(R.layout.activity_department_input) {
    private lateinit var adapter: ListViewAdapter
    private val departmentList = listOf(
        "컴퓨터공학과", "컴퓨터학과", "컴퓨터과학과", "컴퓨터과학", "컴퓨터교육과", "컴퓨터교육"
    )
    private val filteredList = mutableListOf<String>()
    private var departmentSelected = false
    private var highlightText = ""
    private var selectedSchool: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedSchool = intent.getStringExtra("selectedSchool")
        initView()
    }

    override fun initView() {
        setupEditTextListener()
        setupListView()
        setupNextButton()
    }

<<<<<<< Updated upstream
=======
    private fun initEditTextDepartmentInput() {
        binding.etDepartmentInputSearch.requestFocus()
    }

>>>>>>> Stashed changes
    private fun setupListView() {
        adapter = ListViewAdapter(this, R.layout.listview_item, filteredList, highlightText)
        binding.lvDepartmentInputSearchResults.adapter = adapter
        val layoutParams = binding.lvDepartmentInputSearchResults.layoutParams
        layoutParams.height = resources.getDimensionPixelSize(R.dimen.dropdown_height)
        binding.lvDepartmentInputSearchResults.layoutParams = layoutParams

        binding.lvDepartmentInputSearchResults.setOnItemClickListener { parent, view, position, id ->
            val selectedDepartment = filteredList[position]
            if (selectedDepartment == "...") return@setOnItemClickListener
            binding.etDepartmentInputSearch.setText(selectedDepartment)
            binding.lvDepartmentInputSearchResults.visibility = View.GONE
            hideKeyboard()
            departmentSelected = true
            enableButton()
        }
    }

    private fun setupEditTextListener() {
        binding.etDepartmentInputSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = s.toString().trim()
                highlightText = input
                adapter.setHighlightText(input)
                filterDepartments(input)
                binding.lvDepartmentInputSearchResults.visibility = View.VISIBLE // 드롭다운 표시
                departmentSelected = false
                disableButton()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.etDepartmentInputSearch.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                val input = binding.etDepartmentInputSearch.text.toString().trim()
                highlightText = input
                adapter.setHighlightText(input)
                filterDepartments(input)
                binding.lvDepartmentInputSearchResults.visibility = View.VISIBLE
            }
        }
    }

    private fun setupNextButton() {
        binding.btnDepartmentInputNext.setOnClickListener {
            if (departmentSelected) {
                val selectedDepartment = binding.etDepartmentInputSearch.text.toString()
                val intent = Intent(this, StudentIdInputActivity::class.java)
                intent.putExtra("selectedSchool", selectedSchool)
                intent.putExtra("selectedDepartment", selectedDepartment)
                startActivity(intent)
            }
        }
    }

    private fun enableButton() {
        binding.btnDepartmentInputNext.isEnabled = true
        binding.btnDepartmentInputNext.background = ContextCompat.getDrawable(this, R.drawable.bg_mint400_radius_40dp)
    }

    private fun disableButton() {
        binding.btnDepartmentInputNext.isEnabled = false
        binding.btnDepartmentInputNext.background = ContextCompat.getDrawable(this, R.drawable.bg_gray200_radius_40dp)
    }

    private fun filterDepartments(query: String) {
        filteredList.clear()
        if (query.isNotEmpty()) {
            val results = departmentList.filter { it.contains(query, ignoreCase = true) }
                .sortedBy { it.replace(query, "", ignoreCase = true) }
                .take(20)
            filteredList.addAll(results)
            if (results.size == 20) {
                filteredList.add("...")
            }
        }
        adapter.notifyDataSetChanged()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etDepartmentInputSearch.windowToken, 0)
    }
}
