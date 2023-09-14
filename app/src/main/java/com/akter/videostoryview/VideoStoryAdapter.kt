package com.akter.videostoryview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akter.videostoryview.databinding.RowVideoShopFullViewBinding

class VideoStoryAdapter(
    private val dataList: List<ModelVideoData>,
) : RecyclerView.Adapter<VideoStoryAdapter.VideoStoryViewHolder>() {

    class VideoStoryViewHolder(val binding: RowVideoShopFullViewBinding) : RecyclerView.ViewHolder(binding.root){
        val viewBinding: RowVideoShopFullViewBinding = binding
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): VideoStoryViewHolder {
        return VideoStoryViewHolder(RowVideoShopFullViewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))
    }

    override fun onBindViewHolder(holder: VideoStoryViewHolder, position: Int) {
        val data = dataList[position]
        holder.binding.apply {


        }
    }

    override fun getItemCount() = dataList.size
}