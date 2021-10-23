package com.example.maxng.models.mapper

interface NetworkMapper<Entity, Domain> {
    fun fromRemoteToDomain(remoteResult: Entity): List<Domain>?
}
