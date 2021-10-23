package com.example.maxng.models.mapper

import com.example.maxng.constants.AppConstants.IMAGES
import com.example.maxng.constants.AppConstants.randomNum
import com.example.maxng.models.Vehicles
import javax.inject.Inject

class VehiclesMapper @Inject constructor() : NetworkMapper<Vehicles, Domain> {
    override fun fromRemoteToDomain(remoteResult: Vehicles): List<Domain> {
        val listOfDomain = ArrayList<Domain>()
        remoteResult.results!!.forEach {
            listOfDomain.add(
                Domain(
                    it.name!!,
                    IMAGES[randomNum],
                    it.created!!.split("T").first(),
                    "Vehicles"
                )
            )
        }
        return listOfDomain
    }
}
