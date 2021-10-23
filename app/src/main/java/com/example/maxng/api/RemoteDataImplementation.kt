package com.example.maxng.api

import com.example.maxng.models.mapper.Domain
import com.example.maxng.models.mapper.FilmsMapper
import com.example.maxng.models.mapper.PeopleMapper
import com.example.maxng.models.mapper.PlanetsMapper
import com.example.maxng.models.mapper.SpaceShipMapper
import com.example.maxng.models.mapper.SpeciesMapper
import com.example.maxng.models.mapper.VehiclesMapper
import com.example.maxng.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class RemoteDataImplementation @Inject constructor(
    private val apiService: ApiService,
    private val filmsMapper: FilmsMapper,
    private val peopleMapper: PeopleMapper,
    private val planetsMapper: PlanetsMapper,
    private val spaceShipsMapper: SpaceShipMapper,
    private val speciesMapper: SpeciesMapper,
    private val vehiclesMapper: VehiclesMapper

) {
    suspend fun getFilms(): Flow<Resource<List<Domain>>> = flow {
        emit(Resource.loading(null))

        try {
            emit(
                Resource.success(
                    filmsMapper.fromRemoteToDomain(
                        apiService.getFilms()
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.error(null))
        }
    }

    suspend fun getPeople(): Flow<Resource<List<Domain>>> = flow {
        emit(Resource.loading(null))

        try {
            emit(
                Resource.success(
                    peopleMapper.fromRemoteToDomain(
                        apiService.getPeople()
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.error(null))
        }
    }

    suspend fun getSpaceShips(): Flow<Resource<List<Domain>>> = flow {
        emit(Resource.loading(null))

        try {
            emit(
                Resource.success(
                    spaceShipsMapper.fromRemoteToDomain(
                        apiService.getStarShips()
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.error(null))
        }
    }

    suspend fun getSpecies(): Flow<Resource<List<Domain>>> = flow {
        emit(Resource.loading(null))

        try {
            emit(
                Resource.success(
                    speciesMapper.fromRemoteToDomain(
                        apiService.getSpecies()
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.error(null))
        }
    }

    suspend fun getPlanets(): Flow<Resource<List<Domain>>> = flow {
        emit(Resource.loading(null))

        try {
            emit(
                Resource.success(
                    planetsMapper.fromRemoteToDomain(
                        apiService.getPlanets()
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.error(null))
        }
    }

    suspend fun getVehicles(): Flow<Resource<List<Domain>>> = flow {
        emit(Resource.loading(null))

        try {
            emit(
                Resource.success(
                    vehiclesMapper.fromRemoteToDomain(
                        apiService.getVehicles()
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.error(null))
        }
    }
}
