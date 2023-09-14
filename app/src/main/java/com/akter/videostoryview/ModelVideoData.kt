package com.akter.videostoryview

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class ModelVideoData (
    val id:Int,
    val videoUrl:String
):Serializable