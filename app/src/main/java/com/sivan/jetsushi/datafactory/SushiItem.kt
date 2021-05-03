package com.sivan.jetsushi.datafactory

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SushiItem(
    var id: Int,
    var name: String,
    var image: Int,
    var combo: String,
    var category: List<Int>,
    var base_price: Double,

    ) : Parcelable{


}