package com.univoice.feature.signup

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.core_ui.view.UiState
import com.univoice.databinding.ActivityDepartmentInputBinding
import com.univoice.feature.signup.SchoolInputActivity.Companion.MAX_SIZE
import com.univoice.feature.signup.SchoolInputActivity.Companion.SCHOOL_KEY
import com.univoice.feature.util.setupToolbarClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DepartmentInputActivity :
    BindingActivity<ActivityDepartmentInputBinding>(R.layout.activity_department_input) {

    private val viewModel by viewModels<DepartmentInputViewModel>()
    private lateinit var adapter: SchoolDepartmentListAdapter
    private val filteredList = mutableListOf<String>()
    private var departmentSelected = false
    private var highlightText = ""

    override fun initView() {
        initToolbar()
        initEditTextFocus()
        initSchoolDepartmentListAdapter()
        setupNextButton()
        setupEditTextListener()
        postDepartments()
        observeDepartmentList()
    }

    private fun initToolbar() {
        with(binding.toolbarDepartmentInput) {
            tvToolbarTitle.text =
                applicationContext.getString(R.string.tv_toolbar_personal_information_title)
            setupToolbarClickListener(ibToolbarIcon)
        }
    }

    private fun initEditTextFocus() {
        binding.etDepartmentInputSearch.requestFocus()
    }

    private fun initSchoolDepartmentListAdapter() {
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
        if (selectedDepartment == applicationContext.getString(R.string.tv_ellipse)) return
        binding.etDepartmentInputSearch.setText(selectedDepartment)
        binding.etDepartmentInputSearch.setSelection(selectedDepartment.length)
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
        disableButton()
        with(binding) {
            btnDepartmentInputNext.btnSignupNext.setOnClickListener {
                if (departmentSelected) {
                    val selectedDepartment = etDepartmentInputSearch.text.toString()
                    val selectedSchool = intent.getStringExtra(SCHOOL_KEY)
                    selectedSchool?.let {
                        navigateToStudentIdInput(selectedSchool, selectedDepartment)
                    }
                }
            }
        }
    }

    private fun navigateToStudentIdInput(selectedSchool: String, selectedDepartment: String) {
        Intent(this, StudentIdInputActivity::class.java).apply {
            putExtra(SCHOOL_KEY, selectedSchool)
            putExtra(DEPARTMENT_KEY, selectedDepartment)
            startActivity(this)
        }
    }

    private fun enableButton() {
        with(binding.btnDepartmentInputNext.btnSignupNext) {
            isEnabled = true
            background = ContextCompat.getDrawable(
                this@DepartmentInputActivity,
                R.drawable.shape_mint400_fill_40_rect
            )
        }
    }

    private fun disableButton() {
        with(binding.btnDepartmentInputNext.btnSignupNext) {
            isEnabled = false
            background = ContextCompat.getDrawable(
                this@DepartmentInputActivity,
                R.drawable.shape_gray200_fill_40_rect
            )
        }
    }

    private fun filterDepartments(query: String) {
        filteredList.clear()
        if (query.isNotEmpty()) {
            val results = (viewModel.departmentListState.value as? UiState.Success)?.data
                ?.filter { it.contains(query, ignoreCase = true) }
                ?.sortedBy { it.replace(query, "", ignoreCase = true) } ?: emptyList()
            filteredList.addAll(results.take(MAX_SIZE))
            if (results.size > MAX_SIZE) {
                filteredList.add(applicationContext.getString(R.string.tv_ellipse))
            }
        }
        adapter.submitList(filteredList)
    }

    private fun postDepartments() {
        val selectedSchool = intent.getStringExtra(SCHOOL_KEY)
        selectedSchool?.let {
            viewModel.postDepartments(it)
        }
    }

    private fun observeDepartmentList() {
        lifecycleScope.launch {
            viewModel.departmentListState.collect { state ->
                when (state) {
                    is UiState.Loading -> Unit
                    is UiState.Success -> {
                        filterDepartments(binding.etDepartmentInputSearch.text.toString().trim())
                    }
                    is UiState.Empty -> Unit
                    is UiState.Failure -> Unit
                }
            }
        }
    }

    companion object {
        const val DEPARTMENT_KEY = "selectedDepartment"
    }
}
