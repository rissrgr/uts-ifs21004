package com.ifs21004.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dino(
    var dino_image : Int,
    var dinosaurs_name : String,
    var dinosaurs_desc : String,
    var dinosaurs_carac : String,
    var dinosaurs_cel : String,
    var dinosaurs_habit : String,
    var dinosaurs_eats : String,
    var dinosaurs_length : String,
    var dinosaurs_long : String,
    var dinosaurs_weigth : String,
    var dinosaurs_weak : String,
) : Parcelable
