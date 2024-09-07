package com.example.airportticketapp

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.airportticketapp.data.AppContainer
import com.example.airportticketapp.data.DefaultAppContainer
import com.example.airportticketapp.repository.UserSearchRepository


private const val USER_SEARCH_PREFERENCE = "search_preferences"
private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = USER_SEARCH_PREFERENCE
)
class AppDataProvider : Application() {
    lateinit var userSearchRepository: UserSearchRepository
     lateinit var appContainter : AppContainer
    override fun onCreate() {
        super.onCreate()
        appContainter = DefaultAppContainer(context =  this )
        userSearchRepository = UserSearchRepository(dataStore = dataStore)
    }
}