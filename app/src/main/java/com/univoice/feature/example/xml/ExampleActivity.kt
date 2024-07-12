package com.univoice.feature.example.xml

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListPopupWindow
import android.widget.PopupWindow
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.core_ui.util.context.toast
import com.univoice.core_ui.view.UiState
import com.univoice.databinding.ActivityExampleBinding
import com.univoice.databinding.ActivityStudentIdInputBinding
import com.univoice.databinding.BottomSheetLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ExampleActivity : BindingActivity<ActivityStudentIdInputBinding>(R.layout.activity_student_id_input) {
    private val viewModel by viewModels<ExampleViewModel>()
    private var spinnerPopupWindow: ListPopupWindow? = null
    private var spinnerPopupWindow2: Boolean =  false
    override fun initView() {
        viewModel.getExampleRecyclerview(2)
        val studentIdArray = resources.getStringArray(R.array.student_id_array)
        setSpinner(binding.spStudentId, studentIdArray,binding.clStudentIdInput)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setSpinner(spinner: Spinner, array: Array<String>, constraintLayout: ConstraintLayout) {
        val adapter = CustomSpinnerAdapter2(this, array.toMutableList())
        spinner.adapter = adapter
        spinner.setSelection(0)  // 스피너 초기값 = hint

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                (view as? TextView)?.setTextColor(if (position == 0) getColor(R.color.font_B04) else getColor(R.color.black))
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        spinner.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                toggleSpinnerIcon(spinner)
            }
            false
        }



    }

    @SuppressLint("ClickableViewAccessibility")
    private fun toggleSpinnerIcon(spinner: Spinner) {
        val icon = spinner.findViewById<ImageView>(R.id.spinner_icon)
        println(spinnerPopupWindow2)
        spinnerPopupWindow2!=spinnerPopupWindow2
        println(spinnerPopupWindow2)
        if (spinnerPopupWindow?.isShowing == true) {
            println("닫힘")
            icon?.setImageResource(R.drawable.ic_spinner_down)

        } else {
            println("열림")
            icon?.setImageResource(R.drawable.ic_spinner_up)
        }
    }

    private fun updateSpinnerIcon(spinner: Spinner, isOpen: Boolean) {
        val icon = spinner.findViewById<ImageView>(R.id.spinner_icon)
        icon?.setImageResource(if (isOpen) R.drawable.ic_spinner_up else R.drawable.ic_spinner_down)
    }
}


class CustomSpinnerAdapter2(context: Context, private val items: List<String>) : ArrayAdapter<String>(context, 0, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.custom_spinner, parent, false)
        val textView = view.findViewById<TextView>(R.id.spinner_text)
        textView?.text = if (position == 0) items[position] else items[position + 1] // 0번째 아이템을 힌트로 사용

        if (position == 0) {
            textView?.setTextColor(context.getColor(R.color.font_B04)) // 초기 텍스트 색상 회색
        } else {
            textView?.setTextColor(context.getColor(R.color.black)) // 선택된 후 텍스트 색상 검정색
        }
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false)
        val textView = view.findViewById<TextView>(R.id.text1)
        textView?.text = items[position + 1] // position + 1로 아이템을 가져옴
        return view
    }

    override fun getCount(): Int {
        // hint 항목을 제외한 실제 항목의 개수를 반환
        return super.getCount() - 1
    }

    override fun getItem(position: Int): String? {
        // 첫 번째 항목(힌트)를 제외한 나머지 항목을 반환
        return if (position == 0) null else items[position]
    }
}
