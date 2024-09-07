package com.example.airportticketapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.airportticketapp.model.Airport
import com.example.airportticketapp.model.Favourite
import kotlinx.coroutines.flow.Flow


@Dao
interface AirportDao {
    @Query("SELECT * FROM airport WHERE name LIKE :name OR iata_code LIKE :name " )
    fun getItemWithString(name : String? ) : Flow<List<Airport>>


    @Query("SELECT * FROM airport WHERE iata_code LIKE :name")
    fun getAirportWithIdCode(name: String) : Flow<Airport>

    @Query("SELECT * FROM airport ORDER BY passengers ")
    fun getAllAirportItems () : Flow<List<Airport>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favourite: Favourite )

    @Delete
    suspend fun delete(favourite: Favourite)

    @Query("SELECT * FROM favorite ORDER BY destination_code ")
    fun getAllFavouriteItems () : Flow<List<Favourite>>
}