package com.example.maxng.models.mapper

import com.example.maxng.constants.AppConstants.IMAGES
import com.example.maxng.constants.AppConstants.randomNum
import com.example.maxng.models.Species
import javax.inject.Inject

class SpeciesMapper @Inject constructor() : NetworkMapper<Species, Domain> {
    override fun fromRemoteToDomain(remoteResult: Species): List<Domain> {
        val listOfDomain = ArrayList<Domain>()
        remoteResult.results!!.forEach {
            listOfDomain.add(
                Domain(
                    it.name!!,
                    IMAGES[randomNum],
                    it.created!!.split("T").first(),
                    "Species"
                )
            )
        }
        return listOfDomain
    }
}
