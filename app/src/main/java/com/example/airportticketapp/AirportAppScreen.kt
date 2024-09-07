package com.example.airportticketapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.airportticketapp.ui.Screen.AppHomeScreen

@Composable
fun AirportAppScreen(
){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        AppHomeScreen(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
        )

    }
}