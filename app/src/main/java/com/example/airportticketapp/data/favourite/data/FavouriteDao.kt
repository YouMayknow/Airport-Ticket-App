package com.example.airportticketapp.data.favourite.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favourite: Favourite)

    @Delete
    suspend fun delete(favourite: Favourite)

    @Query("SELECT * FROM Favourite ORDER BY destination_code ")
     fun getAllItems () : Flow<List<Favourite>>
}