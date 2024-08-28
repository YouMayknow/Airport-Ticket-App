package com.example.airportticketapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.airportticketapp.ui.Screen.AppHomeScreen


@Composable
fun AirportAppScreen (

) {
    AppHomeScreen(
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
@Preview
fun AirportAppScreenPreview () {
    AirportAppScreen()
}