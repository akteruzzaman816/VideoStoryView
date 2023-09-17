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
            ModelVideoData("https://beeda-1320138795.cos.ap-singapore.myqcloud.com/stream-video/1693733903622_adaptiveDynamicStreaming_1243146.m3u8"),
            ModelVideoData("https://beeda-1320138795.cos.ap-singapore.myqcloud.com/stream-video/1693733884253_adaptiveDynamicStreaming_1243146.m3u8"),
            ModelVideoData("https://beeda-1320138795.cos.ap-singapore.myqcloud.com/stream-video/1693735735884_adaptiveDynamicStreaming_1243146.m3u8"),
            ModelVideoData("https://beeda-1320138795.cos.ap-singapore.myqcloud.com/stream-video/1689664150175_adaptiveDynamicStreaming_1243146.m3u8"),
            ModelVideoData("https://beeda-1320138795.cos.ap-singapore.myqcloud.com/stream-video/1693733859890_adaptiveDynamicStreaming_1243146.m3u8"),
            ModelVideoData("https://beeda-1320138795.cos.ap-singapore.myqcloud.com/stream-video/1693733923784_adaptiveDynamicStreaming_1243146.m3u8"),
            ModelVideoData("https://beeda-1320138795.cos.ap-singapore.myqcloud.com/stream-video/1693980034430_adaptiveDynamicStreaming_1243146.m3u8"),
            ModelVideoData("https://beeda-1320138795.cos.ap-singapore.myqcloud.com/stream-video/1693980064735_adaptiveDynamicStreaming_1243146.m3u8"),
            ModelVideoData("https://beeda-1320138795.cos.ap-singapore.myqcloud.com/stream-video/1693980089447_adaptiveDynamicStreaming_1243146.m3u8"),
        )

        storyAdapter = VideoStoryAdapter()

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