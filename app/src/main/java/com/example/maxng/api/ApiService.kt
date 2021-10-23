package com.example.maxng.api

import com.example.maxng.models.Films
import com.example.maxng.models.People
import com.example.maxng.models.Planets
import com.example.maxng.models.SpaceShips
import com.example.maxng.models.Species
import com.example.maxng.models.Vehicles
import retrofit2.http.GET

interface ApiService {

    @GET("films")
    suspend fun getFilms(): Films

    @GET("people")
    suspend fun getPeople(): People

    @GET("starships")
    suspend fun getStarShips(): SpaceShips

    @GET("species")
    suspend fun getSpecies(): Species

    @GET("planets")
    suspend fun getPlanets(): Planets

    @GET("vehicles")
    suspend fun getVehicles(): Vehicles
}
