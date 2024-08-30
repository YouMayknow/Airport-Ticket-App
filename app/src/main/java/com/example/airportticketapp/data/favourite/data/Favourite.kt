package com.example.airportticketapp.data.favourite.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favourite")
 data class Favourite(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1 ,
    val departure_code: String,
    val destination_code: String
)