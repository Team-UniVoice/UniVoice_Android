package com.univoice.feature.storage

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.univoice.R
import com.univoice.core_ui.base.BindingFragment
import com.univoice.core_ui.view.UiState
import com.univoice.databinding.FragmentStorageBinding
import com.univoice.domain.entity.NoticeListEntity
import com.univoice.domain.entity.StorageListEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class StorageFragment : BindingFragment<FragmentStorageBinding>(R.layout.fragment_storage) {
    private val viewModel by activityViewModels<StorageViewModel>()
    override fun initView() {
        initStorageObserve()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getStorageList()
    }

    private fun initStorageObserve() {
        viewModel.getStorageState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> Unit
                is UiState.Success -> initStorageAdapter(it.data)
                is UiState.Empty -> Unit
                is UiState.Failure -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun initStorageAdapter(data: List<StorageListEntity>) {
        binding.rvStorageList.adapter = StorageAdapter(onClick = { data ->
            navigateToNoticeDetail(data.id)
        }).apply {
            submitList(data)
        }
    }

    private fun navigateToNoticeDetail(id: Int) {
        val bundle = Bundle().apply {
            putInt(STORAGE_KEY, id)
        }
        findNavController().navigate(R.id.action_fragment_storage_to_fragment_notice_detail, bundle)
    }

    companion object {
        const val STORAGE_KEY = "storage_detail_key"
    }
}