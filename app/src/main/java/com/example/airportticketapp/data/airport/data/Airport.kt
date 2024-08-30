package com.example.airportticketapp.data.airport.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport" )
data class Airport (
    @PrimaryKey(autoGenerate =  true)
    val  id : Int  = 1 ,
    val iata_code : String ,
    val name : String ,
    val passengers : Int
)

