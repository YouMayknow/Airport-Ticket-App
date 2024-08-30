package com.example.airportticketapp.data.favourite.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(version = 1 , views = [Favourite::class] , exportSchema = false)
 abstract  class FavouriteDatabase : RoomDatabase()  {
  abstract  val favouriteDao : FavouriteDao
  companion object {
   @Volatile
   private  var Instance : FavouriteDatabase? = null
   fun getFavouriteItems(context: Context) : FavouriteDatabase {
    return Instance ?: synchronized(this){
     Room.databaseBuilder( context = context , klass =  FavouriteDatabase :: class.java ,   name = "flight_search")
      .fallbackToDestructiveMigration()
      .createFromAsset("database/flight_search.db")
      .build()
      .also { Instance = it  }
    }
   }
  }
}