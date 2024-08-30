package com.example.airportticketapp

import android.app.Application
import com.example.airportticketapp.data.favourite.data.AppContainer
import com.example.airportticketapp.data.favourite.data.DefaultAppContainer

class AppDataProvider : Application( ) {
     lateinit var appContainter : AppContainer
    override fun onCreate() {
        super.onCreate()
        appContainter = DefaultAppContainer(context =  this )
    }
}