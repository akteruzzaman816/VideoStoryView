package com.akter.videostoryview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akter.videostoryview.databinding.RowVideoShopFullViewBinding

val DiffUtilsStoriesDataModel = object : DiffUtil.ItemCallback<ModelVideoData>() {
    override fun areItemsTheSame(oldItem: ModelVideoData, newItem: ModelVideoData): Boolean {
        return newItem.videoUrl == oldItem.videoUrl
    }

    override fun areContentsTheSame(oldItem: ModelVideoData, newItem: ModelVideoData): Boolean {
        return newItem == oldItem
    }
}

class StoriesAdapter : ListAdapter<ModelVideoData, StoriesViewHolder>(DiffUtilsStoriesDataModel) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        return StoriesViewHolder(
            RowVideoShopFullViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

class StoriesViewHolder(private val binding: RowVideoShopFullViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val viewBinding: RowVideoShopFullViewBinding = binding

    fun bind(storiesDataModel: ModelVideoData) {
//        with(binding.layoutStoryView) {
//            textViewAccountHandle.setTextOrHide(value = storiesDataModel.userName)
//            textViewVideoDescription.setTextOrHide(value = storiesDataModel.storyDescription)
//            textViewMusicTitle.setTextOrHide(value = storiesDataModel.musicCoverTitle)
//
//            imageViewOptionCommentTitle.text =
//                storiesDataModel.commentsCount.formatNumberAsReadableFormat()
//            imageViewOptionLikeTitle.text =
//                storiesDataModel.likesCount.formatNumberAsReadableFormat()
//
//            imageViewProfilePic.loadCenterCropImageFromUrl(storiesDataModel.userProfilePicUrl)
//
//            textViewMusicTitle.isSelected = true
//        }
    }


}