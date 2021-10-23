package com.example.maxng.models.mapper

import com.example.maxng.constants.AppConstants.IMAGES
import com.example.maxng.constants.AppConstants.randomNum
import com.example.maxng.models.SpaceShips
import javax.inject.Inject

class SpaceShipMapper @Inject constructor() : NetworkMapper<SpaceShips, Domain> {
    override fun fromRemoteToDomain(remoteResult: SpaceShips): List<Domain> {
        val listOfDomain = ArrayList<Domain>()
        remoteResult.results!!.forEach {
            listOfDomain.add(
                Domain(
                    it.name!!,
                    IMAGES[randomNum],
                    it.created!!.split("T").first(),
                    "SpaceShips"
                )
            )
        }
        return listOfDomain
    }
}
