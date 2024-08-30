package com.example.airportticketapp.data.airport.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(version =  1 , entities = [Airport::class] , exportSchema =  false )
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
                        ).createFromAsset("database/flight_search")
                            .fallbackToDestructiveMigration()
                            .build()
                            .also { initializer = it   }
                    }
                }

    }
}