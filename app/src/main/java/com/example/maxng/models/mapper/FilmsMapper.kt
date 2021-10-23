package com.example.maxng.models.mapper

import com.example.maxng.constants.AppConstants.IMAGES
import com.example.maxng.constants.AppConstants.randomNum
import com.example.maxng.models.Films
import javax.inject.Inject

class FilmsMapper @Inject constructor() : NetworkMapper<Films, Domain> {
    override fun fromRemoteToDomain(remoteResult: Films): List<Domain> {
        val listOfDomain = ArrayList<Domain>()
        remoteResult.results!!.forEach {
            listOfDomain.add(
                Domain(
                    it.title!!,
                    IMAGES[randomNum],
                    it.release_date!!,
                    "Films"
                )
            )
        }
        return listOfDomain
    }
}
