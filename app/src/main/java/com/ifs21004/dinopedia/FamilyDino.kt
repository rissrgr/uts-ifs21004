package com.ifs21004.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class FamilyDino(
    var family_images : Int,
    var family_name: String,
    var family_descriptions: String,
    var dinosaur_lifespans: String,
    var dinosaur_physical_characteristics: String,
    var dinosaur_habitats: String,
    var dinosaur_classifications: String,
) : Parcelable
