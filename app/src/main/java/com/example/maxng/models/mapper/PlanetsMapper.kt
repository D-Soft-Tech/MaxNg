package com.example.maxng.models.mapper

import com.example.maxng.constants.AppConstants.IMAGES
import com.example.maxng.constants.AppConstants.randomNum
import com.example.maxng.models.Planets
import javax.inject.Inject

class PlanetsMapper @Inject constructor() : NetworkMapper<Planets, Domain> {
    override fun fromRemoteToDomain(remoteResult: Planets): List<Domain> {
        val listOfDomain = ArrayList<Domain>()
        remoteResult.results!!.forEach {
            listOfDomain.add(
                Domain(
                    it.name!!,
                    IMAGES[randomNum],
                    it.created!!.split("T").first(),
                    "Planets"
                )
            )
        }
        return listOfDomain
    }
}
