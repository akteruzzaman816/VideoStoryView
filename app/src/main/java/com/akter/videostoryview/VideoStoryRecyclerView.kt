package com.akter.videostoryview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VideoStoryRecyclerView(context: Context, attrs: AttributeSet? = null) : RecyclerView(context, attrs) {

    private val TAG = "#X_"
    private var scrollingUp = false
    private var dataList = mutableListOf<ModelVideoData>()
    private var currentPlayingPosition = -1
    private val player: ExoPlayer = ExoPlayer.Builder(context).build()

    init {
        addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                scrollingUp = dy > 0

                if (dy == 0) {
                    playVideo()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when(newState) {
                    SCROLL_STATE_IDLE -> {
                        playVideo()
                    }
                    else -> {
                        player.pause()
                    }
                }
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun  playVideo() {
        Log.d(TAG, "playVideo................: ")
        val firstVisibleItemPosition = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        val lastVisibleItemPosition = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

        val firstCompletelyVisibleItemPosition = (layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
        val lastCompletelyVisibleItemPosition = (layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()

        val targetPosition = if (!scrollingUp) {
                lastVisibleItemPosition
        } else {
                firstVisibleItemPosition
        }

        if (targetPosition != currentPlayingPosition) {

            currentPlayingPosition = if (!scrollingUp) {
                if (lastCompletelyVisibleItemPosition < 0) {
                    return
                }
                lastCompletelyVisibleItemPosition
            } else {
                if (firstCompletelyVisibleItemPosition < 0) {
                    return
                }
                firstCompletelyVisibleItemPosition
            }
            player.stop()

            val currentViewHolder = findViewHolderForAdapterPosition(currentPlayingPosition)
            val currentViewBinding = (currentViewHolder as? VideoStoryAdapter.VideoStoryViewHolder)?.viewBinding
            val currentPlayerViewStory = currentViewBinding?.videoMain
            currentPlayerViewStory?.player = null

            val viewHolder = findViewHolderForAdapterPosition(targetPosition)
            val viewBinding = (viewHolder as? VideoStoryAdapter.VideoStoryViewHolder)?.viewBinding
            val playerViewStory = viewBinding?.videoMain
            playerViewStory?.player = player

            // player views actions
            playerViewStory?.setOnTouchListener { _, event ->
                when(event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        if (player.isPlaying) player.pause()
                        else player.play()
                    }
                }
                true
            }

            // setup video urls
            val storiesDataModel = dataList[currentPlayingPosition]
            val mediaItem: MediaItem = MediaItem.Builder().apply {
                setUri(storiesDataModel.videoUrl.toUri())
            }.build()

            // setup player
            player.apply {
                addListener(object : Player.Listener{
                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        currentViewBinding?.imgPlay?.isVisible = !isPlaying
                    }
                })
                playWhenReady = true
                repeatMode = ExoPlayer.REPEAT_MODE_ONE
                setMediaItem(mediaItem)
                prepare()
                play()
            }
        } else {
            player.play()
        }
    }

    fun updateVideoList(list: MutableList<ModelVideoData>) {
        val shouldPlayFirstVideo = dataList.isEmpty() && list.isNotEmpty()
        dataList = list
        (adapter as VideoStoryAdapter).updateList(list)

        if (shouldPlayFirstVideo) {
            scrollToPosition(0)
        }
    }

    fun onDestroy() {
        player.stop()
        player.release()
    }
}