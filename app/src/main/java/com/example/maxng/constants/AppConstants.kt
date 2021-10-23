package com.example.maxng.constants

import com.example.maxng.R
import com.example.maxng.ui.Films
import com.example.maxng.ui.People
import com.example.maxng.ui.Planets
import com.example.maxng.ui.Spaceships
import com.example.maxng.ui.Species
import com.example.maxng.ui.Vehicles

object AppConstants {
    val tabTitles = arrayOf("Films", "People", "Spaceships", "Species", "Planets", "Vehicles")
    val destinations = arrayOf(
        Films(),
        People(),
        Spaceships(),
        Species(),
        Planets(),
        Vehicles()
    )

    const val BASE_URL = "https://swapi.dev/api/"

    val IMAGES = arrayOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5,
        R.drawable.image6,
        R.drawable.image7,
        R.drawable.image8,
        R.drawable.image11,
        R.drawable.image12,
        R.drawable.luke_sky
    )

    var randomNum = (IMAGES.indices).random()

    val hearts = arrayOf(
        R.drawable.ic_yet_to_like,
        R.drawable.ic_liked
    )
}
