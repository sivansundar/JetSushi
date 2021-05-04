package com.sivan.jetsushi.util




    fun Float.splitToWholeAndFraction() : Pair<Float, Float> {
        val fraction = this%1
        val whole = this - fraction
        return Pair(whole, fraction)
    }
