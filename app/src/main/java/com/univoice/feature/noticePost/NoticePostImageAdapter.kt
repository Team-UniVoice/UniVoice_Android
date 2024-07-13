package com.univoice.feature.noticePost

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.univoice.databinding.ItemNoticePostImageBinding

class NoticePostImageAdapter(private val imageUris: MutableList<Uri>) :
    RecyclerView.Adapter<NoticePostImageAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemNoticePostImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageView: ImageView = binding.ivNoticePostImageItem
        val cancelImageView: ImageView = binding.ivPostingCancelImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemNoticePostImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageURI(imageUris[position])
        holder.cancelImageView.setOnClickListener {
            imageUris.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, imageUris.size)
        }
    }

    override fun getItemCount() = imageUris.size
}
