package com.example.airportticketapp.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.room.Database
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserSearchRepository (
    private val dataStore  :  DataStore<Preferences>
) {
    companion object {
        val USER_SEARCH = stringPreferencesKey("user_search")
    }

    suspend fun saveUserSearch (search : String) {
        dataStore.edit {
            it[USER_SEARCH] = search
        }
    }
    val userSearch : Flow<String> = dataStore.data
        .catch {
            if (it is IOException){
                Log.e("UserSearchRepo" , "Problem at user search at the repo That is IO Exception ")
            emit(emptyPreferences())
            }
            else {
                throw  it
            }
        }.map {
            it[USER_SEARCH] ?: ""
        }
}