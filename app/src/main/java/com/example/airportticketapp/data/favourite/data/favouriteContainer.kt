package com.example.airportticketapp.data.favourite.data

import android.content.Context
import com.example.airportticketapp.data.airport.data.AirportDatabase
import com.example.airportticketapp.data.airport.repository.AirportRepository
import com.example.airportticketapp.data.airport.repository.OfflineAirportRepository
import com.example.airportticketapp.data.favourite.repository.FavouriteRepository
import com.example.airportticketapp.data.favourite.repository.OfflineFavouriteRepository


interface AppContainer {
   val favouriteRepository : FavouriteRepository
   val airRepository : AirportRepository
}

class DefaultAppContainer( private val context: Context) : AppContainer {
    override val favouriteRepository: FavouriteRepository by lazy {
        OfflineFavouriteRepository(favouriteDao = FavouriteDatabase.getFavouriteItems(context).favouriteDao)
    }
    override val airRepository: AirportRepository by lazy {
        OfflineAirportRepository(airportDao = AirportDatabase.getAirportItems(context = context).airportDao)
    }

}