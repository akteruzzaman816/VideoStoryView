package com.akter.videostoryview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akter.videostoryview.databinding.RowVideoShopFullViewBinding

class VideoStoryAdapter(
    private var dataList: List<ModelVideoData>,
) : RecyclerView.Adapter<VideoStoryAdapter.VideoStoryViewHolder>() {


    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: MutableList<ModelVideoData>) {
        val diffCallback = DiffCallback(dataList, list)
        val diffResult = diffCallback.let { DiffUtil.calculateDiff(it) }
        dataList = list
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    class VideoStoryViewHolder(binding: RowVideoShopFullViewBinding) : RecyclerView.ViewHolder(binding.root){
        val viewBinding: RowVideoShopFullViewBinding = binding
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): VideoStoryViewHolder {
        return VideoStoryViewHolder(RowVideoShopFullViewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))
    }

    override fun onBindViewHolder(holder: VideoStoryViewHolder, position: Int) {}

    override fun getItemCount() = dataList.size
}