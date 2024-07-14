package com.univoice.feature.example.xml

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityDepartmentInputBinding
import com.univoice.feature.example.xml.SchoolInputActivity.Companion.MAX_SIZE
import com.univoice.feature.example.xml.SchoolInputActivity.Companion.SCHOOL_KEY
import com.univoice.feature.util.setupToolbarClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DepartmentInputActivity :
    BindingActivity<ActivityDepartmentInputBinding>(R.layout.activity_department_input) {

    private val viewModel by viewModels<DepartmentInputViewModel>()
    private lateinit var adapter: ListViewAdapter
    private val filteredList = mutableListOf<String>()
    private var departmentSelected = false
    private var highlightText = ""

    override fun initView() {
        setupToolbar()
        initFocus()
        initAdapter()
        setupListView()
        setupNextButton()
        setupEditTextListener()
    }

    private fun setupToolbar() {
        with(binding.toolbarDepartmentInput) {
            tvToolbarTitle.text =
                applicationContext.getString(R.string.tv_toolbar_personal_information_title)
            setupToolbarClickListener(ibToolbarIcon)
        }
    }

    private fun initFocus() {
        binding.etDepartmentInputSearch.requestFocus()
    }

    private fun initAdapter() {
        adapter = ListViewAdapter(this, R.layout.listview_item, filteredList, highlightText)
        binding.lvDepartmentInputSearchResults.adapter = adapter
    }

    private fun setupListView() {
        binding.lvDepartmentInputSearchResults.apply {
            layoutParams.height = resources.getDimensionPixelSize(R.dimen.dropdown_height)
            setOnItemClickListener { _, _, position, _ ->
                val selectedDepartment = filteredList[position]
                if (selectedDepartment == context.getString(R.string.tv_ellipse)) return@setOnItemClickListener

                binding.etDepartmentInputSearch.setText(selectedDepartment)
                binding.etDepartmentInputSearch.setSelection(selectedDepartment.length)
                visibility = View.GONE
                departmentSelected = true
                enableButton()
            }
        }
    }

    private fun setupEditTextListener() {
        binding.etDepartmentInputSearch.apply {
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val input = s.toString().trim()
                    highlightText = input
                    adapter.setHighlightText(input)
                    filterDepartments(input)
                    binding.lvDepartmentInputSearchResults.visibility = View.VISIBLE
                    departmentSelected = false
                    disableButton()
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    text.toString().trim().also {
                        highlightText = it
                        adapter.setHighlightText(it)
                        filterDepartments(it)
                    }
                    binding.lvDepartmentInputSearchResults.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupNextButton() {
        disableButton()
        with(binding) {
            btnDepartmentInputNext.btnSignupNext.setOnClickListener {
                if (departmentSelected) {
                    val selectedDepartment = etDepartmentInputSearch.text.toString()
                    val selectedSchool = intent.getStringExtra(SCHOOL_KEY)
                    navigateToStudentIdInput(selectedSchool, selectedDepartment)
                }
            }
        }
    }

    private fun navigateToStudentIdInput(selectedSchool: String?, selectedDepartment: String) {
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
            val results = viewModel.mockDepartmentList
                .filter { it.contains(query, ignoreCase = true) }
                .sortedBy { it.replace(query, "", ignoreCase = true) }
            filteredList.addAll(results.take(MAX_SIZE))
            if (results.size > MAX_SIZE) {
                filteredList.add(applicationContext.getString(R.string.tv_ellipse))
            }
        }
        adapter.notifyDataSetChanged()
    }

    companion object {
        const val DEPARTMENT_KEY = "selectedDepartment"
    }
}
