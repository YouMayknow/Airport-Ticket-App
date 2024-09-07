package com.example.airportticketapp.repository

import com.example.airportticketapp.model.Airport
import com.example.airportticketapp.model.Favourite
import kotlinx.coroutines.flow.Flow

interface AirportRepository {
    fun getAirportWithString(name  : String?) : Flow<List<Airport>>
    suspend fun insert(favourite: Favourite)
    suspend fun delete(favourite: Favourite)
    fun getAllFavouriteItems() : Flow<List<Favourite>>
    fun getAllAirportItems() : Flow<List<Airport>>
    fun getAirportWithIdCode(name: String) :Flow<Airport>

}

