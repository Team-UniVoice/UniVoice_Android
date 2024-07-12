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
import com.univoice.databinding.ActivitySchoolInputBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolInputActivity : BindingActivity<ActivitySchoolInputBinding>(R.layout.activity_school_input) {

    private lateinit var adapter: ListViewAdapter
    private val schoolList = listOf(
        "서울대학교", "서울과학기술대학교", "서울시립대학교", "서울여자대학교", "서울학교5", "서울학교6",
        "서울학교7", "서울학교8", "서울학교9", "서울학교10", "서울학교11", "서울학교12", "서울학교13",
        "서울학교14", "서울학교15", "서울학교16", "서울학교17", "서울학교18", "서울학교19", "서울학교20", "서울학교21"
    )
    private val filteredList = mutableListOf<String>()
    private var schoolSelected = false
    private var highlightText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        setupEditTextListener()
        setupListView()
        setupNextButton()
    }

    private fun setupListView() {
        adapter = ListViewAdapter(this, R.layout.listview_item, filteredList, highlightText)
        binding.lvSchoolInputSearchResults.adapter = adapter
        val layoutParams = binding.lvSchoolInputSearchResults.layoutParams
        layoutParams.height = resources.getDimensionPixelSize(R.dimen.dropdown_height)
        binding.lvSchoolInputSearchResults.layoutParams = layoutParams

        binding.lvSchoolInputSearchResults.setOnItemClickListener { parent, view, position, id ->
            val selectedSchool = filteredList[position]
            if (selectedSchool == "...") return@setOnItemClickListener
            binding.etSchoolInputSearch.setText(selectedSchool)
            binding.lvSchoolInputSearchResults.visibility = View.GONE
            hideKeyboard()
            schoolSelected = true
            enableButton()
        }
    }

    private fun setupEditTextListener() {
        binding.etSchoolInputSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = s.toString().trim()
                highlightText = input
                adapter.setHighlightText(input)
                filterSchools(input)
                binding.lvSchoolInputSearchResults.visibility = View.VISIBLE // 드롭다운 표시
                schoolSelected = false
                disableButton()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.etSchoolInputSearch.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                val input = binding.etSchoolInputSearch.text.toString().trim()
                highlightText = input
                adapter.setHighlightText(input)
                filterSchools(input)
                binding.lvSchoolInputSearchResults.visibility = View.VISIBLE
            }
        }
    }

    private fun setupNextButton() {
        binding.btnSchoolInputNext.setOnClickListener {
            if (schoolSelected) {
                val selectedSchool = binding.etSchoolInputSearch.text.toString()
                val intent = Intent(this, DepartmentInputActivity::class.java)
                intent.putExtra("selectedSchool", selectedSchool)
                startActivity(intent)
            }
        }
    }

    private fun enableButton() {
        binding.btnSchoolInputNext.isEnabled = true
        binding.btnSchoolInputNext.background = ContextCompat.getDrawable(this, R.drawable.bg_mint400_radius_40dp)
    }

    private fun disableButton() {
        binding.btnSchoolInputNext.isEnabled = false
        binding.btnSchoolInputNext.background = ContextCompat.getDrawable(this, R.drawable.bg_gray200_radius_40dp)
    }

    private fun filterSchools(query: String) {
        filteredList.clear()
        if (query.isNotEmpty()) {
            val results = schoolList.filter { it.contains(query, ignoreCase = true) }
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
        imm.hideSoftInputFromWindow(binding.etSchoolInputSearch.windowToken, 0)
    }
}
