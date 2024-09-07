package com.example.airportticketapp.repository

import com.example.airportticketapp.data.AirportDao
import com.example.airportticketapp.model.Airport
import com.example.airportticketapp.model.Favourite
import kotlinx.coroutines.flow.Flow


class OfflineFavouriteRepository(
    private  val airportDao: AirportDao
) : AirportRepository {
    override suspend fun insert(favourite: Favourite) = airportDao.insert(favourite)

    override suspend fun delete(favourite: Favourite) = airportDao.delete(favourite)

    override fun getAllFavouriteItems() = airportDao.getAllFavouriteItems()

    override fun getAirportWithString(name: String?) = airportDao.getItemWithString(name = name)

    override fun getAllAirportItems() = airportDao.getAllAirportItems()
    override fun getAirportWithIdCode(name: String) = airportDao.getAirportWithIdCode(name)
}