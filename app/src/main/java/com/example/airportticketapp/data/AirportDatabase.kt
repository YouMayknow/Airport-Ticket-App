package com.example.airportticketapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.airportticketapp.model.Airport
import com.example.airportticketapp.model.Favourite
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(version =  1 , entities = [Airport::class , Favourite::class] , exportSchema =  false )
abstract  class AirportDatabase : RoomDatabase() {
    abstract val airportDao : AirportDao
    companion object {
        @Volatile
       private var initializer : AirportDatabase? = null
                @OptIn(InternalCoroutinesApi::class)
                fun getAirportItems(context: Context)  : AirportDatabase {
                    synchronized(lock = this){
                      return   initializer ?:  Room.databaseBuilder(
                            name = "airport_database" , context = context , klass =  AirportDatabase::class.java
                        ).createFromAsset("database/flight_search.db")
                            .build()
                            .also { initializer = it   }
                    }
                }
    }
}