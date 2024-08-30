package com.example.airportticketapp.data.favourite.repository

import com.example.airportticketapp.data.favourite.data.Favourite
import com.example.airportticketapp.data.favourite.data.FavouriteDao


class OfflineFavouriteRepository(
    private  val favouriteDao: FavouriteDao
) : FavouriteRepository{
    override suspend fun update(favourite: Favourite) = favouriteDao.insert(favourite)

    override suspend fun delete(favourite: Favourite) = favouriteDao.delete(favourite)

    override fun getAllItems() = favouriteDao.getAllItems()


}