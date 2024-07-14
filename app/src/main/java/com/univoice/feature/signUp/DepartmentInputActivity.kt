package com.univoice.feature.signUp

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
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
            handleDepartmentSelection(position)
        }
    }

    private fun handleDepartmentSelection(position: Int) {
        val selectedDepartment = filteredList[position]
        if (selectedDepartment == "...") return
        binding.etDepartmentInputSearch.setText(selectedDepartment)
        binding.etDepartmentInputSearch.setTextAppearance(R.style.TextAppearance_UniVoice_title4Semi)
        binding.rvDepartmentInputSearchResults.visibility = View.GONE
        departmentSelected = true
        enableButton()
    }

    private fun setupEditTextListener() {
        binding.etDepartmentInputSearch.addTextChangedListener(createTextWatcher())
        binding.etDepartmentInputSearch.setOnFocusChangeListener { _, hasFocus -> handleEditTextFocusChange(hasFocus) }
    }

    private fun createTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                handleTextChanged(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        }
    }

    private fun handleTextChanged(input: String) {
        val trimmedInput = input.trim()
        highlightText = trimmedInput
        adapter.setHighlightText(trimmedInput)
        filterDepartments(trimmedInput)
        binding.rvDepartmentInputSearchResults.visibility = View.VISIBLE // 드롭다운 표시
        departmentSelected = false
        disableButton()
    }

    private fun handleEditTextFocusChange(hasFocus: Boolean) {
        if (hasFocus) {
            val input = binding.etDepartmentInputSearch.text.toString().trim()
            highlightText = input
            adapter.setHighlightText(input)
            filterDepartments(input)
            binding.rvDepartmentInputSearchResults.visibility = View.VISIBLE
        }
    }

    private fun setupNextButton() {
        binding.btnDepartmentInputNext.setOnClickListener {
            if (departmentSelected) {
                proceedToNextScreen()
            }
        }
    }

    private fun proceedToNextScreen() {
        val selectedDepartment = binding.etDepartmentInputSearch.text.toString()
        val intent = Intent(this, StudentIdInputActivity::class.java).apply {
            putExtra("selectedSchool", selectedSchool)
            putExtra("selectedDepartment", selectedDepartment)
        }
        startActivity(intent)
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
            adjustRecyclerViewHeight(results.size)
        }
        adapter.submitList(filteredList)
    }

    private fun adjustRecyclerViewHeight(resultsSize: Int) {
        if (resultsSize > 4) {
            binding.rvDepartmentInputSearchResults.layoutParams.height = resources.getDimensionPixelSize(R.dimen.dropdown_height)
        }
    }
}
