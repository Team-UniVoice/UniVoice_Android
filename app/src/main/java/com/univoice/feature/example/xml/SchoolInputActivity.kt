package com.univoice.feature.example.xml

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivitySchoolInputBinding
import com.univoice.feature.util.setupToolbarClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolInputActivity :
    BindingActivity<ActivitySchoolInputBinding>(R.layout.activity_school_input) {

    private val viewModel by viewModels<SchoolInputViewModel>()
    private lateinit var adapter: ListViewAdapter
    private val filteredList = mutableListOf<String>()
    private var schoolSelected = false
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
        with(binding.toolbarSchoolInput) {
            tvToolbarTitle.text =
                applicationContext.getString(R.string.tv_toolbar_personal_information_title)
            setupToolbarClickListener(ibToolbarIcon)
        }
    }

    private fun initFocus() {
        binding.etSchoolInputSearch.requestFocus()
    }

    private fun initAdapter() {
        adapter = ListViewAdapter(this, R.layout.listview_item, filteredList, highlightText)
        binding.lvSchoolInputSearchResults.adapter = adapter
    }

    private fun setupListView() {
        binding.lvSchoolInputSearchResults.apply {
            layoutParams.height = resources.getDimensionPixelSize(R.dimen.dropdown_height)

            setOnItemClickListener { _, _, position, _ ->
                val selectedSchool = filteredList[position]
                if (selectedSchool == context.getString(R.string.tv_ellipse)) return@setOnItemClickListener

                binding.etSchoolInputSearch.setText(selectedSchool)
                binding.etSchoolInputSearch.setSelection(selectedSchool.length)
                visibility = View.GONE
                schoolSelected = true
                enableButton()
            }
        }
    }

    private fun setupEditTextListener() {
        binding.etSchoolInputSearch.apply {
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
                    filterSchools(input)
                    binding.lvSchoolInputSearchResults.visibility = View.VISIBLE
                    schoolSelected = false
                    disableButton()
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    text.toString().trim().also {
                        highlightText = it
                        adapter.setHighlightText(it)
                        filterSchools(it)
                    }
                    binding.lvSchoolInputSearchResults.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupNextButton() {
        disableButton()
        with(binding) {
            btnSchoolInputNext.btnSignupNext.setOnClickListener {
                if (schoolSelected) {
                    val selectedSchool = etSchoolInputSearch.text.toString()
                    navigateToDepartmentInput(selectedSchool)
                }
            }
        }
    }

    private fun navigateToDepartmentInput(selectedSchool: String) {
        Intent(this, DepartmentInputActivity::class.java).apply {
            putExtra(SCHOOL_KEY, selectedSchool)
            startActivity(this)
        }
    }

    private fun enableButton() {
        with(binding.btnSchoolInputNext.btnSignupNext) {
            isEnabled = true
            background = ContextCompat.getDrawable(context, R.drawable.shape_mint400_fill_40_rect)
        }
    }

    private fun disableButton() {
        with(binding.btnSchoolInputNext.btnSignupNext) {
            isEnabled = false
            background = ContextCompat.getDrawable(context, R.drawable.shape_gray200_fill_40_rect)
        }
    }

    private fun filterSchools(query: String) {
        filteredList.clear()
        if (query.isNotEmpty()) {
            val results = viewModel.mockSchoolList
                .filter { it.contains(query, ignoreCase = true) }
                .sortedBy { it.replace(query, "", ignoreCase = true) }
            filteredList.addAll(results)
            if (filteredList.size == MAX_SIZE) {
                filteredList.add("...")
            }
        }
        adapter.notifyDataSetChanged()
    }

    companion object {
        const val SCHOOL_KEY = "selectedSchool"
        const val MAX_SIZE = 20
    }
}