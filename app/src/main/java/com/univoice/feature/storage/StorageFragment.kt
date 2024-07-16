package com.univoice.feature.storage

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.univoice.R
import com.univoice.core_ui.base.BindingFragment
import com.univoice.databinding.FragmentStorageBinding
import com.univoice.domain.entity.NoticeListEntity
import com.univoice.feature.home.HomeNoticeAdapter

class StorageFragment : BindingFragment<FragmentStorageBinding>(R.layout.fragment_storage) {
    private val viewModel by viewModels<StorageViewModel>()
    override fun initView() {
        initStorageAdapter()
    }

    private fun initStorageAdapter() {
        HomeNoticeAdapter() { data, position ->
            navigateToNoticeDetail(data, position)
        }.also {
            binding.rvStorageList.adapter = it
            it.submitList(viewModel.mockStorageList)
        }
    }

    private fun navigateToNoticeDetail(data: NoticeListEntity, position: Int) {
        findNavController().navigate(R.id.action_fragment_storage_to_fragment_notice_detail)
    }
}