package com.example.maxng.api

import com.example.maxng.localDB.LocalDaoImplementation
import com.example.maxng.models.mapper.Domain
import com.example.maxng.utils.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataImplementation,
    private val localDataSource: LocalDaoImplementation
) {
    fun fetchResultsAndSaveToRoom(): Boolean {
        val isCompleted = CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getFilms().collect { filmsFromRemote ->
                when (filmsFromRemote.status) {
                    Status.SUCCESS -> {
                        filmsFromRemote.data!!.forEach { film ->
                            localDataSource.insert(film)
                        }
                    }
                    else -> {
                        // Do nothing
                    }
                }
            }

            remoteDataSource.getPeople().collect { peopleFromRemote ->
                when (peopleFromRemote.status) {
                    Status.SUCCESS -> {
                        peopleFromRemote.data!!.forEach { person ->
                            localDataSource.insert(person)
                        }
                    } else -> {
                        // Do nothing
                    }
                }
            }

            remoteDataSource.getSpaceShips().collect { spaceShipsFromRemote ->
                when (spaceShipsFromRemote.status) {
                    Status.SUCCESS -> {
                        spaceShipsFromRemote.data!!.forEach { spaceShip ->
                            localDataSource.insert(spaceShip)
                        }
                    }
                    else -> {
                        // Do nothing
                    }
                }
            }

            remoteDataSource.getSpecies().collect { speciesFromRemote ->
                when (speciesFromRemote.status) {
                    Status.SUCCESS -> {
                        speciesFromRemote.data!!.forEach { specie ->
                            localDataSource.insert(specie)
                        }
                    } else -> {
                        // Do nothing
                    }
                }
            }

            remoteDataSource.getPlanets().collect { planetsFromRemote ->
                when (planetsFromRemote.status) {
                    Status.SUCCESS -> {
                        planetsFromRemote.data!!.forEach { planet ->
                            localDataSource.insert(planet)
                        }
                    }
                    else -> {
                        // Do nothing
                    }
                }
            }

            remoteDataSource.getVehicles().collect { vehiclesFromRemote ->
                when (vehiclesFromRemote.status) {
                    Status.SUCCESS -> {
                        vehiclesFromRemote.data!!.forEach { vehicle ->
                            localDataSource.insert(vehicle)
                        }
                    } else -> {
                        // Do nothing
                    }
                }
            }
        }

        // To check if the job has completed and not cancelled
        return true
    }

    fun fetchFromRoom(isDoneFetchingFromRemote: Boolean, category: String): Flow<List<Domain>>? {
        return if (isDoneFetchingFromRemote) {
            localDataSource.getAllData(category)
        } else {
            null
        }
    }

    suspend fun updateLike(data: Domain) {
        localDataSource.update(data)
    }

    fun getLiked(check: Int): Flow<List<Domain>> =
        localDataSource.getLiked(check)
}
