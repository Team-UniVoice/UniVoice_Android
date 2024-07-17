package com.univoice.feature.noticePost

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.univoice.R
import com.univoice.core_ui.base.BindingFragment
import com.univoice.core_ui.util.context.showPermissionAppSettingsDialog
import com.univoice.databinding.FragmentNoticePostBinding
import com.univoice.feature.post.dateTimePicker.TimeBottomSheetFragment
import java.util.Calendar

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
        initApplyBtnClickListener()

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
    }

    private fun initPostDateClickListener() {
        binding.layoutNoticePostDateBtn.setOnClickListener {
            val startDate: Calendar = Calendar.getInstance()
            val timeBottomSheetFragment = TimeBottomSheetFragment(startDate, 12)
            timeBottomSheetFragment.show(parentFragmentManager, timeBottomSheetFragment.tag)
            initSetDateText()
        }
    }

    private fun initSetDateClickListener(){
        binding.layoutNoticePostOptionDate.setOnClickListener {
            val startDate: Calendar = Calendar.getInstance()
            val timeBottomSheetFragment = TimeBottomSheetFragment(startDate, 12)
            timeBottomSheetFragment.show(parentFragmentManager, timeBottomSheetFragment.tag)
            initSetDateText()
        }
    }

    private fun initCloseClickListener(){
        with(binding){
            ivNoticePostOptionDateDelete.setOnClickListener {
                layoutNoticePostOptionDate.visibility = View.GONE
            }
        }
    }

    private fun initSetDateText() {
        setFragmentResultListener(TIME_PICKER_KEY) { _, bundle ->
            val setStartDate = bundle.getString(SET_START_DATE, "")
            val setEndDate = bundle.getString(SET_END_DATE, "")

            with(binding){
                tvNoticePostOptionDateStart.text = setStartDate
                tvNoticePostOptionDateEnd.text = setEndDate
                layoutNoticePostOptionDate.visibility = View.VISIBLE
            }
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
                imageUriData?.let {
                    if (imageUris.size + it.size <= 5) {
                        imageUris.addAll(it)
                        imageAdapter.notifyDataSetChanged()
                        it.forEach { uri ->
                            noticePostViewModel.setPhotoUri(uri.toString())
                        }
                    }
                }
            }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initGalleryLauncher() {
        getGalleryLauncher =
            registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { imageUriData ->
                imageUriData?.let {
                    if (imageUris.size + it.size <= 5) {
                        imageUris.addAll(it)
                        imageAdapter.notifyDataSetChanged()
                        it.forEach { uri ->
                            noticePostViewModel.setPhotoUri(uri.toString())
                        }
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
            findNavController().navigate(
                R.id.action_fragment_notice_post_to_fragment_home,
            )
        }
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
        const val NOTICE_POST_TARGET_BOTTOM_SHEET_ARGS_CONTENT = "notice_post_target_bottom_sheet_args_content"
        const val TIME_PICKER_KEY = "timePickerKey"
        const val SET_START_DATE = "setStartDate"
        const val SET_END_DATE = "setEndDate"
    }
}