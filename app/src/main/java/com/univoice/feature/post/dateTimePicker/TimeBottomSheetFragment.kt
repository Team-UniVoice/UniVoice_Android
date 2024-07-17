package com.univoice.feature.post.dateTimePicker

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.univoice.R
import com.univoice.core_ui.base.BindingBottomSheetFragment
import com.univoice.databinding.FragmentTimeBottomSheetBinding
import com.univoice.feature.noticePost.NoticePostFragment.Companion.SET_END_DATE
import com.univoice.feature.noticePost.NoticePostFragment.Companion.SET_START_DATE
import com.univoice.feature.noticePost.NoticePostFragment.Companion.TIME_PICKER_KEY
import com.univoice.feature.post.dateTimePicker.adapter.AM
import com.univoice.feature.post.dateTimePicker.adapter.CustomSnapHelper
import com.univoice.feature.post.dateTimePicker.adapter.DateDayAdapter
import com.univoice.feature.post.dateTimePicker.adapter.DateMonthAdapter
import com.univoice.feature.post.dateTimePicker.adapter.DatePickerUtils
import com.univoice.feature.post.dateTimePicker.adapter.DateYearAdapter
import com.univoice.feature.post.dateTimePicker.adapter.TimeDateAdapter
import com.univoice.feature.post.dateTimePicker.adapter.TimeHourAdapter
import com.univoice.feature.post.dateTimePicker.adapter.TimeMeridiemAdapter
import com.univoice.feature.post.dateTimePicker.adapter.TimeMinuteAdapter
import com.univoice.feature.post.dateTimePicker.adapter.dpToPx
import com.univoice.feature.post.dateTimePicker.adapter.initVerticalAdapter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TimeBottomSheetFragment(
    private val startDate: Calendar,
    private val maxMonthToDisplay: Int
) : BindingBottomSheetFragment<FragmentTimeBottomSheetBinding>(R.layout.fragment_time_bottom_sheet) {

    private lateinit var utils: DatePickerUtils
    private var endDate: Calendar = Calendar.getInstance().also {
        it.timeInMillis = startDate.timeInMillis
        it.add(Calendar.MONTH, maxMonthToDisplay)
    }

    private var isSelected = true

    companion object {
        const val DIVIDER_HEIGHT = 38
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.TransparentBottomSheetDialogFragment)

        utils = DatePickerUtils(startDate, endDate)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val behavior = BottomSheetBehavior.from(view.parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        behavior.isDraggable = false

        binding.viewDateTime.layoutParams.height = DIVIDER_HEIGHT.dpToPx(requireContext()).toInt()

        val dateAdapter = TimeDateAdapter(utils.getAllDates(), DIVIDER_HEIGHT)
        val hourAdapter = TimeHourAdapter(utils.getHours(), DIVIDER_HEIGHT)
        val meridiemAdapter =
            TimeMeridiemAdapter(utils.addEmptyValueInString(utils.getMeridiem()), DIVIDER_HEIGHT)
        val minuteAdapter = TimeMinuteAdapter(utils.getMinutes(), DIVIDER_HEIGHT)
        val yearAdapter = DateYearAdapter(utils.getAllYears(), DIVIDER_HEIGHT)
        val monthAdapter = DateMonthAdapter(utils.getAllMonths(), DIVIDER_HEIGHT)
        val dayAdapter = DateDayAdapter(utils.getAllDates(), DIVIDER_HEIGHT)

        with(binding){
            rvDateDetail.initVerticalAdapter(dateAdapter, true)
            rvDateHour.initVerticalAdapter(hourAdapter, true)
            rvDateMeridiem.initVerticalAdapter(meridiemAdapter, true)
            rvDateMinute.initVerticalAdapter(minuteAdapter, true)
            rvDateDayYear.initVerticalAdapter(yearAdapter, true)
            rvDateDayMonth.initVerticalAdapter(monthAdapter, true)
            rvDateDay.initVerticalAdapter(dayAdapter, true)
        }

        val dateSnapListener = object : CustomSnapHelper.SnapListener {
            override fun onViewSnapped(position: Int) {
                val selectedDate = dateAdapter.dates[position]
                val fullDateFormat = SimpleDateFormat("M월 d일(EEE)", Locale.KOREA)
                setTimeDateText(fullDateFormat.format(selectedDate.time))
                val monthFormat = SimpleDateFormat("M월", Locale.KOREA)
                val dayFormat = SimpleDateFormat("d일(EEE)", Locale.KOREA)
                val monthText = monthFormat.format(selectedDate.time)
                val dayText = dayFormat.format(selectedDate.time)

                setDateMonthText(monthText)
                setDateDayText(dayText)

                utils.setSelectedDate(
                    selectedDate.get(Calendar.DAY_OF_YEAR),
                    selectedDate.get(Calendar.YEAR)
                )
            }
        }

        val yearSnapListener = object : CustomSnapHelper.SnapListener {
            override fun onViewSnapped(position: Int) {
                val selectedYear = yearAdapter.years[position]
                val dateFormat = SimpleDateFormat("YYYY년", Locale.KOREA)

                setDateYearText(dateFormat.format(selectedYear.time))
                utils.setSelectedYear(position)
            }
        }

        val monthSnapListener = object : CustomSnapHelper.SnapListener {
            override fun onViewSnapped(position: Int) {
                val selectedMonth = monthAdapter.months[position]
                val dateFormat = SimpleDateFormat("M월", Locale.KOREA)
                setDateMonthText(dateFormat.format(selectedMonth.time))

                utils.setSelectedMonth(selectedMonth.get(Calendar.MONTH))
            }
        }

        val daySnapListener = object : CustomSnapHelper.SnapListener {
            override fun onViewSnapped(position: Int) {
                val selectedDay = dayAdapter.days[position]
                val dateFormat = SimpleDateFormat(" d일(EEE)", Locale.KOREA)
                setDateDayText(dateFormat.format(selectedDay.time))

                val selectedDate = dateAdapter.dates[position]
                val fullDateFormat = SimpleDateFormat("M월 d일(EEE)", Locale.KOREA)
                setTimeDateText(fullDateFormat.format(selectedDate.time))

                utils.setSelectedDate(
                    selectedDate.get(Calendar.DAY_OF_YEAR),
                    selectedDate.get(Calendar.YEAR)
                )

                utils.setSelectedDay(selectedDay.get(Calendar.DAY_OF_MONTH))
            }
        }

        val hourSnapListener = object : CustomSnapHelper.SnapListener {
            override fun onViewSnapped(position: Int) {

                val selectedHour = hourAdapter.hour[position]
                val dateFormat = SimpleDateFormat("HH", Locale.KOREA)

                setHourText(dateFormat.format(selectedHour.time))
                utils.setSelectedHour(selectedHour.get(Calendar.HOUR))
            }
        }

        val minuteSnapListener = object : CustomSnapHelper.SnapListener {
            override fun onViewSnapped(position: Int) {
                val selectedHour = minuteAdapter.minute[position]
                val dateFormat = SimpleDateFormat(":mm", Locale.KOREA)

                setMinuteText(dateFormat.format(selectedHour.time))
                utils.setSelectedHour(selectedHour.get(Calendar.HOUR))
            }
        }

        val meridiemSnapListener = object : CustomSnapHelper.SnapListener {
            override fun onViewSnapped(position: Int) {
                val meridiemValue =
                    if (meridiemAdapter.meridiem[position] == AM) Calendar.AM else Calendar.PM
                setMeridiemText(meridiemValue)
            }
        }

        CustomSnapHelper(binding.rvDateDetail, dateSnapListener)
        CustomSnapHelper(binding.rvDateHour, hourSnapListener)
        CustomSnapHelper(binding.rvDateMinute, minuteSnapListener)
        CustomSnapHelper(binding.rvDateMeridiem, meridiemSnapListener)
        CustomSnapHelper(binding.rvDateDayYear, yearSnapListener)
        CustomSnapHelper(binding.rvDateDayMonth, monthSnapListener)
        CustomSnapHelper(binding.rvDateDay, daySnapListener)
    }

    override fun initView() {
        setStartTextColor(isSelected)
        initCloseBtnClickListener()
        initDateDayClickListener()
        initStartTimeClickListener()
        initEndTimeClickListener()
        initStartDateClickListener()
        initEndDateClickListener()
        initSuccessBtnClickListener()
    }

    private fun initSuccessBtnClickListener() {
        with(binding) {
            btnDateSuccess.setOnClickListener {
                var setStartDate = ""
                var setEndDate = ""

                val startDate = layoutStartTime.tvStartTimeDay.text.toString()
                val startTimeHour = layoutStartTime.tvStartTimeHour.text.toString()
                val startTimeMinute = layoutStartTime.tvStartTimeMinute.text.toString()
                val startTimeMeridiem = layoutStartTime.tvStartTimeMeridiem.text.toString()
                val endDate = layoutEndTime.tvEndTimeDay.text.toString()
                val endTimeHour = layoutEndTime.tvEndTimeHour.text.toString()
                val endTimeMinute = layoutEndTime.tvEndTimeMinute.text.toString()
                val endTimeMeridiem = layoutEndTime.tvEndTimeMeridiem.text.toString()
                val startYear = layoutStartDate.tvStartDateYear.text.toString()
                val startMonth = layoutStartDate.tvStartDateMonth.text.toString()
                val startDay = layoutStartDate.tvStartDateDay.text.toString()
                val endYear = layoutEndDate.tvEndDateYear.text.toString()
                val endMonth = layoutEndDate.tvEndDateMonth.text.toString()
                val endDay = layoutEndDate.tvEndDateDay.text.toString()

                if (!isSelected) {
                    setStartDate =
                        "${startDate} \n ${startTimeMeridiem} ${startTimeHour} ${startTimeMinute}"
                    setEndDate = "${endDate} \n ${endTimeMeridiem} ${endTimeHour} ${endTimeMinute}"

                } else {
                    setStartDate = context?.getString(
                        R.string.tv_date_day_picker,
                        startYear,
                        startMonth,
                        startDay
                    ).toString()
                    setEndDate =
                        context?.getString(R.string.tv_date_day_picker, endYear, endMonth, endDay)
                            .toString()
                }
                val resultBundle = Bundle().apply {
                    putString(SET_START_DATE, setStartDate)
                    putString(SET_END_DATE, setEndDate)
                }

                parentFragmentManager.setFragmentResult(TIME_PICKER_KEY, resultBundle)
                dismiss()
            }
        }
    }

    private fun initCloseBtnClickListener() {
        binding.btnDateClose.setOnClickListener {
            dismiss()
        }
    }

    private fun initDateDayClickListener() {
        with(binding) {
            btnDateTime.setOnClickListener {
                if (btnDateTime.isSelected) {
                    btnDateTime.text = getString(R.string.btn_date_time_selected)
                    layoutDateTime.visibility = View.VISIBLE
                    layoutDateDay.visibility = View.INVISIBLE
                    layoutDateDayPicker.visibility = View.INVISIBLE
                    layoutDateTimePicker.visibility = View.VISIBLE
                } else {
                    btnDateTime.text = getString(R.string.btn_date_time_unselected)
                    layoutDateTime.visibility = View.INVISIBLE
                    layoutDateDay.visibility = View.VISIBLE
                    layoutDateDayPicker.visibility = View.VISIBLE
                    layoutDateTimePicker.visibility = View.INVISIBLE
                }
                setStartTextColor(isSelected)
                btnDateTime.isSelected = !btnDateTime.isSelected
            }
        }
    }

    private fun initStartTimeClickListener() {
        binding.layoutStartTime.layoutItemStartTime.setOnClickListener {
            isSelected = !isSelected
            setStartTextColor(isSelected)
        }
    }

    private fun initEndTimeClickListener() {
        binding.layoutEndTime.layoutItemEndTime.setOnClickListener {
            isSelected = !isSelected
            setStartTextColor(isSelected)
        }
    }

    private fun initStartDateClickListener() {
        binding.layoutStartDate.layoutItemStartDate.setOnClickListener {
            isSelected = !isSelected
            setStartTextColor(isSelected)
        }
    }

    private fun initEndDateClickListener() {
        binding.layoutEndDate.layoutItemEndDate.setOnClickListener {
            isSelected = !isSelected
            setStartTextColor(isSelected)
        }
    }

    private fun setStartTextColor(isSelected: Boolean) {
        if (isSelected) {
            initChangeGroupTextColor(R.id.group_start_time, R.color.mint_700)
            initChangeGroupTextColor(R.id.group_start_date, R.color.mint_700)
            initChangeGroupTextColor(R.id.group_end_time, R.color.font_B03)
            initChangeGroupTextColor(R.id.group_end_date, R.color.font_B03)
        } else {
            initChangeGroupTextColor(R.id.group_end_time, R.color.mint_700)
            initChangeGroupTextColor(R.id.group_end_date, R.color.mint_700)
            initChangeGroupTextColor(R.id.group_start_time, R.color.font_B03)
            initChangeGroupTextColor(R.id.group_start_date, R.color.font_B03)
        }
    }

    private fun setTimeDateText(date: String) {
        with(binding) {
            if (isSelected) {
                layoutStartTime.tvStartTimeDay.text = date
            } else {
                layoutEndTime.tvEndTimeDay.text = date
            }
        }
    }

    private fun setDateYearText(date: String) {
        with(binding) {
            if (isSelected) {
                layoutStartDate.tvStartDateYear.text = date
            } else {
                layoutEndDate.tvEndDateYear.text = date
            }
        }
    }

    private fun setDateMonthText(date: String) {
        with(binding) {
            if (isSelected) {
                layoutStartDate.tvStartDateMonth.text = date
            } else {
                layoutEndDate.tvEndDateMonth.text = date
            }
        }
    }

    private fun setDateDayText(date: String) {
        with(binding) {
            if (isSelected) {
                layoutStartDate.tvStartDateDay.text = date
            } else {
                layoutEndDate.tvEndDateDay.text = date
            }
        }
    }

    private fun setHourText(date: String) {
        with(binding) {
            if (isSelected) {
                layoutStartTime.tvStartTimeHour.text = date
            } else {
                layoutEndTime.tvEndTimeHour.text = date
            }
        }
    }

    private fun setMinuteText(date: String) {
        with(binding) {
            if (isSelected) {
                layoutStartTime.tvStartTimeMinute.text = date
            } else {
                layoutEndTime.tvEndTimeMinute.text = date
            }
        }
    }

    private fun setMeridiemText(meridiemValue: Int) {
        with(binding) {
            if (isSelected) {
                layoutStartTime.tvStartTimeMeridiem.text =
                    requireContext().getString(if (meridiemValue == Calendar.AM) R.string.tv_date_am else R.string.tv_date_pm)
            } else {
                layoutEndTime.tvEndTimeMeridiem.text =
                    requireContext().getString(if (meridiemValue == Calendar.AM) R.string.tv_date_am else R.string.tv_date_pm)
            }
        }
    }

    private fun initChangeGroupTextColor(groupId: Int, color: Int) {
        val groupDialogTime: Group = binding.root.findViewById(groupId)
        val referencedIds = groupDialogTime.referencedIds
        for (id in referencedIds) {
            val view = binding.root.findViewById<View>(id) as? TextView
            view?.setTextColor(ContextCompat.getColor(requireContext(), color))
        }
    }
}