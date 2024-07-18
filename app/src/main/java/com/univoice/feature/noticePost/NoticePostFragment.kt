package com.univoice.feature.noticePost

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.univoice.R
import com.univoice.core_ui.base.BindingFragment
import com.univoice.core_ui.util.context.showPermissionAppSettingsDialog
import com.univoice.core_ui.util.fragment.viewLifeCycle
import com.univoice.core_ui.util.fragment.viewLifeCycleScope
import com.univoice.core_ui.view.UiState
import com.univoice.databinding.FragmentNoticePostBinding
import com.univoice.feature.post.dateTimePicker.TimeBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

@AndroidEntryPoint
class NoticePostFragment :
    BindingFragment<FragmentNoticePostBinding>(R.layout.fragment_notice_post) {
    private val noticePostViewModel by viewModels<NoticePostViewModel>()

    private lateinit var getGalleryLauncher: ActivityResultLauncher<String>
    private lateinit var getPhotoPickerLauncher: ActivityResultLauncher<PickVisualMediaRequest>
    private val requestPermissions =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            when (isGranted) {
                true -> {
                    try {
                        selectImage()
                    } catch (_: Exception) {
                    }
                }

                false -> handlePermissionDenied()
            }
        }
    private var imageUris: MutableList<Uri> = mutableListOf()
    private lateinit var imageAdapter: NoticePostImageAdapter

    private var hereStartDate = ""
    private var hereStartTime = ""
    private var hereEndData = ""
    private var hereEndTime = ""

    private var hereStartTotalTime = ""
    private var hereEndTotalTime = ""

    private var sendStartTotalTime: String? = null
    private var sendEndTotalTime: String? = null

    private var allDayCheck: Boolean = true

    private fun handlePermissionDenied() {
        if (!shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_IMAGES)) {
            requireContext().showPermissionAppSettingsDialog()
        }
    }

    override fun initView() {
        initBackBtnClickListener()
        setTrackTitleTextChange()
        setTrackContentTextChange()
        setApplyBtnEnable()

        initPhotoBtnClickListener()
        initGalleryLauncher()
        initPhotoPickerLauncher()
        initPostImageAdapter()

        initTargetBtnClickListener()
        setOptionTarget()
        initOptionTargetClickListener()
        initOptionTargetDeleteBtnClickListener()
        initPostDateClickListener()
        initSetDateClickListener()
        initCloseClickListener()

        initApplyBtnClickListener()
        setupPostNoticeObserve()
    }

    private fun setupPostNoticeObserve() {
        noticePostViewModel.postNoticeState.flowWithLifecycle(viewLifeCycle).onEach {
            when (it) {
                is UiState.Loading -> Timber.tag("signup").d("로딩")
                is UiState.Success -> findNavController().navigate(
                    R.id.action_fragment_notice_post_to_fragment_notice_post_apply,
                    null, NavOptions.Builder().setPopUpTo(R.id.fragment_notice_post, true).build()
                )

                is UiState.Failure -> Timber.tag("signup").d(it.msg)
                is UiState.Empty -> Timber.tag("signup").d("비었음")
            }
        }.launchIn(viewLifeCycleScope)
    }

    private fun initPostDateClickListener() {
        binding.layoutNoticePostDateBtn.setOnClickListener {
            val startDate: Calendar = Calendar.getInstance()
            val timeBottomSheetFragment = TimeBottomSheetFragment(startDate, 12)
            timeBottomSheetFragment.show(parentFragmentManager, timeBottomSheetFragment.tag)
            initSetDateText()
        }
    }

//    override fun onResume() {
//        super.onResume()
//        if(allDayCheck){
//            binding.tvNoticePostOptionTimeStart.visibility = View.GONE
//            binding.tvNoticePostOptionTimeEnd.visibility = View.GONE
//        }else{
//            binding.tvNoticePostOptionTimeStart.visibility = View.VISIBLE
//            binding.tvNoticePostOptionTimeEnd.visibility = View.VISIBLE
//        }
//    }

    private fun initSetDateClickListener() {
        binding.layoutNoticePostOptionDate.setOnClickListener {
            val startDate: Calendar = Calendar.getInstance()
            val timeBottomSheetFragment = TimeBottomSheetFragment(startDate, 12)
            timeBottomSheetFragment.show(parentFragmentManager, timeBottomSheetFragment.tag)
            initSetDateText()
        }
    }

    private fun initCloseClickListener() {
        with(binding) {
            ivNoticePostOptionDateDelete.setOnClickListener {
                layoutNoticePostOptionDate.visibility = View.GONE
                sendStartTotalTime = null
                sendEndTotalTime = null
            }
        }
    }

    private fun initSetDateText() {
        setFragmentResultListener(TIME_PICKER_KEY) { _, bundle ->
            with(binding) {

                tvNoticePostOptionDateStart.text = bundle.getString(SET_START_DATE, "")
                tvNoticePostOptionDateEnd.text = bundle.getString(SET_END_DATE, "")

                hereStartDate = bundle.getString(SET_START_DATE, "")
                hereEndData = bundle.getString(SET_END_DATE, "")
                hereStartTime = bundle.getString(SET_START_TIME, "")
                hereEndTime = bundle.getString(SET_END_TIME, "")
                allDayCheck = bundle.getBoolean(CLICK_BUTTON)

                if(allDayCheck){
                    binding.tvNoticePostOptionTimeStart.visibility = View.GONE
                    binding.tvNoticePostOptionTimeEnd.visibility = View.GONE
                }else{
                    binding.tvNoticePostOptionTimeStart.visibility = View.VISIBLE
                    binding.tvNoticePostOptionTimeEnd.visibility = View.VISIBLE
                }

                layoutNoticePostOptionDate.visibility = View.VISIBLE

                hereStartTotalTime = "$hereStartDate $hereStartTime"
                hereEndTotalTime = "$hereEndData $hereEndTime"

                Timber.tag("dd").d(hereStartTotalTime)
                Timber.tag("dd").d(hereEndTotalTime)

                sendStartTotalTime = if (allDayCheck) {
                    convertToFullDateFormat(hereStartTotalTime)
                } else {
                    formatDateTime(hereStartTotalTime)
                }

                sendEndTotalTime = if (allDayCheck) {
                    convertToFullDateFormat(hereEndTotalTime)
                } else {
                    formatDateTime(hereEndTotalTime)
                }
            }
        }
    }

    // "2024년7월8일(목)"로 들어오면 "2024년 7월 8일 오전 00시 00분" 로 반환
    private fun convertToFullDateFormat(inputDate: String): String {
        // 정규식을 사용하여 날짜 부분과 요일 부분을 분리
        val datePattern = Regex("""(\d{4})년(\d{1,2})월(\d{1,2})일\(\p{IsAlphabetic}+\)""")
        val matchResult = datePattern.find(inputDate)

        return if (matchResult != null) {
            val (year, month, day) = matchResult.destructured
            // 새로운 형식으로 변환
            "${year}년 ${month}월 ${day}일 오전 00시 00분"
        } else {
            // 매칭되지 않을 경우 원본 문자열 반환
            inputDate
        }
    }

    // 유진언니가 UI 시, 분 텍스트 추가하면 없애야 함!! : "6월 6일(목) 오후 12:25" 로 들어오면 "2024년 6월 6일 오후 12시 25분" 로 반환
    private fun formatDateTime(input: String): String {
        // 정규식을 사용하여 날짜와 시간을 추출
        val regex = Regex("(\\d{1,2})월 (\\d{1,2})일\\(\\S+\\) (오전|오후) (\\d{1,2}):(\\d{2})")
        val matchResult = regex.find(input)

        return if (matchResult != null) {
            val (month, day, period, hour, minute) = matchResult.destructured

            // 시간 부분을 변환
            val formattedHour = if (period == "오후" && hour.toInt() != 12) {
                hour.toInt() + 12
            } else if (period == "오전" && hour.toInt() == 12) {
                0
            } else {
                hour.toInt()
            }

            // 최종 시간 표현
            val finalHour = if (formattedHour == 0) 12 else formattedHour

            // 원하는 형식으로 조립
            "2024년 ${month}월 ${day}일 ${period} ${finalHour}시 ${minute}분"
        } else {
            // 매칭되지 않으면 원래 문자열 반환
            input
        }
    }

    private fun initPostImageAdapter() {
        imageAdapter = NoticePostImageAdapter(imageUris)
        binding.rvNoticePostImage.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvNoticePostImage.adapter = imageAdapter

        if (binding.rvNoticePostImage.itemDecorationCount == 0) {
            binding.rvNoticePostImage.addItemDecoration(NoticePostImageItemDecorator(requireContext()))
        }
    }

    private fun initPhotoBtnClickListener() {
        binding.layoutNoticePostPhotoBtn.setOnClickListener {
            getGalleryPermission()
        }
    }

    private fun getGalleryPermission() {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE -> {
                selectImage()
            }

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                requestPermissions.launch(Manifest.permission.READ_MEDIA_IMAGES)
            }

            else -> {
                requestPermissions.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    private fun selectImage() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            getGalleryLauncher.launch("image/*")
        } else {
            getPhotoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initPhotoPickerLauncher() {
        getPhotoPickerLauncher =
            registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { imageUriData ->
                imageUriData.let {
                    if (imageUris.size + it.size <= 5) {
                        imageUris.addAll(it)
                        imageAdapter.notifyDataSetChanged()
                    }
                }
            }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initGalleryLauncher() {
        getGalleryLauncher =
            registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { imageUriData ->
                imageUriData.let {
                    if (imageUris.size + it.size <= 5) {
                        imageUris.addAll(it)
                        imageAdapter.notifyDataSetChanged()
                    }
                }
            }
    }

    private fun setTrackTitleTextChange() {
        binding.etNoticePostTitle.doAfterTextChanged {
            setApplyBtnEnable()
        }
    }

    private fun setTrackContentTextChange() {
        binding.etNoticePostContent.doAfterTextChanged {
            setApplyBtnEnable()
        }
    }

    private fun initApplyBtnClickListener() {
        binding.btnToolbarNoticePostApply.setOnClickListener {

            val sendTarget: String?

            if (binding.layoutNoticePostOptionTarget.isVisible) {
                sendTarget = binding.tvNoticePostOptionTargetContent.text.toString()
            } else {
                sendTarget = null
            }

//            Timber.tag("dd").d(sendStartTotalTime)
//            Timber.tag("dd").d(sendEndTotalTime)
//
//            Timber.tag("dd").d(sendStartTotalTime?.let { it1 -> convertToISO8601(it1) })
//            Timber.tag("dd").d(sendEndTotalTime?.let { it1 -> convertToISO8601(it1) })

            noticePostViewModel.postNotice(
                title = binding.etNoticePostTitle.text.toString(),
                content = binding.etNoticePostContent.text.toString(),
                target = sendTarget,
                startTime = sendStartTotalTime?.let { it -> convertToISO8601(it) },
                endTime = sendEndTotalTime?.let { it -> convertToISO8601(it) },
                noticeImages = convertUriListToFileList(requireContext(), imageUris)
            )
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun convertToISO8601(inputDate: String): String? {
        // 한국어 날짜 형식
        val koreanDateFormat = SimpleDateFormat("yyyy년 M월 d일 a h시 m분", Locale.KOREAN)

        // ISO 8601 날짜 형식
        val iso8601Format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        iso8601Format.timeZone = TimeZone.getTimeZone("UTC")

        return try {
            // 입력된 한국어 날짜를 파싱
            val date = koreanDateFormat.parse(inputDate)
            // ISO 8601 형식으로 변환하여 반환
            iso8601Format.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun convertUriListToFileList(context: Context, uriList: MutableList<Uri>): List<File> {
        return uriList.mapNotNull { uri ->
            uriToFile(context, uri)
        }
    }
    private fun uriToFile(context: Context, uri: Uri): File? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? = context.contentResolver.query(uri, projection, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                val filePath = it.getString(columnIndex)
                return File(filePath)
            }
        }
        return null
    }

    private fun setApplyBtnEnable() {
        with(binding) {
            if (etNoticePostTitle.text.isNotEmpty() && etNoticePostContent.text.isNotEmpty()) {
                btnToolbarNoticePostApply.setBackgroundResource(R.drawable.shape_mint400_fill_20_rect)
                btnToolbarNoticePostApply.isEnabled = true
            } else {
                btnToolbarNoticePostApply.setBackgroundResource(R.drawable.shape_gray200_fill_20_rect)
                btnToolbarNoticePostApply.isEnabled = false
            }
        }
    }

    private fun setOptionTarget() {
        setFragmentResultListener(NOTICE_POST_TARGET_BOTTOM_SHEET_ARGS) { _, bundle ->
            binding.layoutNoticePostOptionTarget.visibility = View.VISIBLE
            binding.tvNoticePostOptionTargetContent.text =
                bundle.getString(NOTICE_POST_TARGET_BOTTOM_SHEET_ARGS_CONTENT)
        }
    }

    private fun initOptionTargetClickListener() {
        binding.layoutNoticePostOptionTarget.setOnClickListener {
            NoticePostTargetBottomSheet(binding.tvNoticePostOptionTargetContent.text.toString()).show(
                parentFragmentManager,
                NOTICE_POST_TARGET_BOTTOM_SHEET
            )
        }
    }

    private fun initOptionTargetDeleteBtnClickListener() {
        binding.ivNoticePostOptionTargetDelete.setOnClickListener {
            binding.layoutNoticePostOptionTarget.visibility = View.GONE
        }
    }

    private fun initBackBtnClickListener() {
        binding.ibToolbarNoticePostIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initTargetBtnClickListener() {
        binding.layoutNoticePostTargetBtn.setOnClickListener {
            NoticePostTargetBottomSheet().show(
                parentFragmentManager,
                NOTICE_POST_TARGET_BOTTOM_SHEET
            )
        }
    }

    companion object {
        const val NOTICE_POST_TARGET_BOTTOM_SHEET = "notice_post_target_bottom_sheet"
        const val NOTICE_POST_TARGET_BOTTOM_SHEET_ARGS = "notice_post_target_bottom_sheet_args"
        const val NOTICE_POST_TARGET_BOTTOM_SHEET_ARGS_CONTENT =
            "notice_post_target_bottom_sheet_args_content"
        const val TIME_PICKER_KEY = "timePickerKey"
        const val SET_START_DATE = "setStartDate"
        const val SET_END_DATE = "setEndDate"
        const val SET_START_TIME = "setStartTime"
        const val SET_END_TIME = "setEndTime"
        const val CLICK_BUTTON = "changeTime"
    }
}