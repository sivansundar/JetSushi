package com.sivan.jetsushi.datafactory

import com.sivan.jetsushi.R

class DataFactory {

    fun getSampleSushis(): List<SushiItem> {

        val sushis = listOf<SushiItem>(
            SushiItem(
                id = 0,
                name = "Sushi Octopus",
                image = R.drawable.octopus_sushi,
                combo = "Rice + Octopus",
                category = listOf<Int>(1,2),
                base_price = 6.50,
                rating = 3.6f

            ),

            SushiItem(
                id = 1,
                name = "Sushi Salmon",
                image = R.drawable.salmon_sushi,
                combo = "Rice + Salmon",
                category = listOf<Int>(1,5),
                base_price = 8.50,
                rating = 4.6f
            )
        )

        return sushis
    }

    fun getSushi(id : Int): SushiItem {
        val sushis = getSampleSushis()

        val item = sushis[id]
        return item
    }

    fun getSushiCategory() : List<SushiCategory> {
        val sushis = listOf<SushiCategory>(
            SushiCategory(
                id = 1,
                name = "Rice",
                imageId = R.drawable.rice_icon
            ),
            SushiCategory(
                id = 2,
                name = "Shrimp",
                imageId = R.drawable.shrimp_icon
            ),
            SushiCategory(
                id = 3,
                name = "Tuna",
                imageId = R.drawable.tuna_icon
            ),
            SushiCategory(
                id = 4,
                name = "Caviar",
                imageId = R.drawable.ic_caviar
            ),

            SushiCategory(
                id = 5,
                name = "Salmon",
                imageId = R.drawable.salmon_icon
            )
        )

        return sushis
    }
}