package com.ifs21004.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dino(
    var image: Int,
    var name: String,
    var desc: String,
    var origin: String,
    var main: String,
    var interesting: String
) : Parcelable
