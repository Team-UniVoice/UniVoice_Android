package com.univoice.feature.example.xml

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityDepartmentInputBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DepartmentInputActivity : BindingActivity<ActivityDepartmentInputBinding>(R.layout.activity_department_input) {
    private lateinit var adapter: SchoolDepartmentListAdapter
    private val departmentList = listOf(
        "컴퓨터공학과", "컴퓨터학과", "컴퓨터과학과", "컴퓨터과학", "컴퓨터교육과", "컴퓨터교육"
    )

    private val filteredList = mutableListOf<String>()
    private var departmentSelected = false
    private var highlightText = ""
    private var selectedSchool: String? = null

    override fun initView() {
        selectedSchool = intent.getStringExtra("selectedSchool")
        setupEditTextListener()
        setupRecyclerView()
        setupNextButton()
    }

    private fun initEditTextDepartmentInput() {
        binding.etDepartmentInputSearch.requestFocus()
    }

    private fun setupRecyclerView() {
        adapter = SchoolDepartmentListAdapter(this, highlightText)
        binding.rvDepartmentInputSearchResults.layoutManager = LinearLayoutManager(this)
        binding.rvDepartmentInputSearchResults.adapter = adapter

        adapter.submitList(filteredList)
        adapter.setHighlightText(highlightText)

        adapter.setOnItemClickListener { position ->
            val selectedDepartment = filteredList[position]
            if (selectedDepartment == "...") return@setOnItemClickListener
            binding.etDepartmentInputSearch.setText(selectedDepartment)
            binding.etDepartmentInputSearch.setTextAppearance(R.style.TextAppearance_UniVoice_title4Semi)
            binding.rvDepartmentInputSearchResults.visibility = View.GONE
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
                binding.rvDepartmentInputSearchResults.visibility = View.VISIBLE // 드롭다운 표시
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
                binding.rvDepartmentInputSearchResults.visibility = View.VISIBLE
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
            filteredList.addAll(results)
            if (results.size > 4) {
                binding.rvDepartmentInputSearchResults.layoutParams.height = resources.getDimensionPixelSize(R.dimen.dropdown_height)
            }
        }
        adapter.submitList(filteredList)
    }

}
