package com.example.airportticketapp.data.favourite.repository

import com.example.airportticketapp.data.favourite.data.Favourite
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {
    suspend fun update(favourite: Favourite)
    suspend fun delete(favourite: Favourite)
    fun getAllItems() : Flow<List<Favourite>>
}