package com.sivan.jetsushi.datafactory

import com.sivan.jetsushi.R

class DataFactory {

    fun getSampleSushis(): List<SushiItem> {

        val sushis = listOf<SushiItem>(
            SushiItem(
                id = 0,
                name = "Sushi Octopus",
                image = R.drawable.shrimp,
                combo = "Rice + Octopus",
                category = listOf<Int>(1,2),
                base_price = 6.50

            ),

            SushiItem(
                id = 1,
                name = "Sushi Salmon",
                image = R.drawable.salmon,
                combo = "Rice + Salmon",
                category = listOf<Int>(1,5),
                base_price = 8.50

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
                imageId = R.drawable.rice
            ),
            SushiCategory(
                id = 2,
                name = "Shrimp",
                imageId = R.drawable.shrimp
            ),
            SushiCategory(
                id = 3,
                name = "Tuna",
                imageId = R.drawable.tuna
            ),
            SushiCategory(
                id = 4,
                name = "Caviar",
                imageId = R.drawable.ic_caviar
            ),

            SushiCategory(
                id = 5,
                name = "Salmon",
                imageId = R.drawable.salmon
            )
        )

        return sushis
    }
}