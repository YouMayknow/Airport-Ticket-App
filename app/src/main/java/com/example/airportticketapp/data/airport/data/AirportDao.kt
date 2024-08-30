package com.example.airportticketapp.data.airport.data

import androidx.room.Dao
import androidx.room.Query
import com.example.airportticketapp.data.airport.data.Airport
import kotlinx.coroutines.flow.Flow


@Dao
interface AirportDao {
    @Query("SELECT * FROM airport WHERE name LIKE :name OR iata_code LIKE :name " )
    fun getItemWithString(name : String ) : Flow<List<Airport>>
}