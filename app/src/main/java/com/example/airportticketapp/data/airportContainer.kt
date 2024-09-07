package com.example.airportticketapp.data

import android.content.Context
import com.example.airportticketapp.repository.AirportRepository
import com.example.airportticketapp.repository.OfflineFavouriteRepository


interface AppContainer {
   val airRepository : AirportRepository
}

class DefaultAppContainer( private val context: Context) : AppContainer {
    override val airRepository: AirportRepository by lazy {
       OfflineFavouriteRepository(airportDao = AirportDatabase.getAirportItems(context).airportDao)
    }

}