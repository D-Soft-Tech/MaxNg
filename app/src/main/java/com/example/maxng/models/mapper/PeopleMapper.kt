package com.example.maxng.models.mapper

import com.example.maxng.constants.AppConstants.IMAGES
import com.example.maxng.constants.AppConstants.randomNum
import com.example.maxng.models.People
import javax.inject.Inject

class PeopleMapper @Inject constructor() : NetworkMapper<People, Domain> {
    override fun fromRemoteToDomain(remoteResult: People): List<Domain> {
        val listOfDomain = ArrayList<Domain>()
        remoteResult.results!!.forEach {
            listOfDomain.add(
                Domain(
                    it.name!!,
                    IMAGES[randomNum],
                    it.created!!.split("T").first(),
                    "People"
                )
            )
        }
        return listOfDomain
    }
}
