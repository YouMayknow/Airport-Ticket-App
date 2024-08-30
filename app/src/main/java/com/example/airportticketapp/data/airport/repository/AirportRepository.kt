package com.example.airportticketapp.data.airport.repository

import com.example.airportticketapp.data.airport.data.Airport
import com.example.airportticketapp.data.airport.data.AirportDao
import com.example.airportticketapp.data.favourite.data.FavouriteDao
import com.example.airportticketapp.data.favourite.data.FavouriteDatabase
import kotlinx.coroutines.flow.Flow

interface AirportRepository {
    fun getAirportWithString(name  : String) :Flow<List<Airport>>
}


class OfflineAirportRepository(
    val airportDao: AirportDao
): AirportRepository {
    override fun getAirportWithString(name: String) = airportDao.getItemWithString(name = name)
}