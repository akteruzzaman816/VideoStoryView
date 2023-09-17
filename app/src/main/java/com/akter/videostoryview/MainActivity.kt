package com.akter.videostoryview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.PagerSnapHelper
import com.akter.videostoryview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var storyAdapter: VideoStoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater,null,false)
        setContentView(binding.root)

        val dataList = mutableListOf(
            ModelVideoData(1,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"),
            ModelVideoData(2,"https://beeda-1320138795.cos.ap-singapore.myqcloud.com/stream-video/9_transcode_100030.mp4"),
            ModelVideoData(3,"https://beeda-1320138795.cos.ap-singapore.myqcloud.com/stream-video/1_transcode_100030.mp4"),
            ModelVideoData(4,"https://beeda-1320138795.cos.ap-singapore.myqcloud.com/stream-video/7_transcode_100030.mp4"),
        )

        storyAdapter = VideoStoryAdapter(dataList)

        binding.apply {

            // back
            imgBack.setOnClickListener { onBackPressed() }

            // player view
            rvPlayerView.apply {
                adapter = storyAdapter
                updateVideoList(dataList)
            }
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(rvPlayerView)

        }
    }

    override fun onDestroy() {
        with(binding) {
            rvPlayerView.onDestroy()
        }
        super.onDestroy()
    }

}