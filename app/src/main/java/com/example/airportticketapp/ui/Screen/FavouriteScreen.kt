package com.example.airportticketapp.ui.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.airportticketapp.model.Airport
import com.example.airportticketapp.model.Favourite
import kotlin.system.measureTimeMillis

@Composable
fun FavouriteScreen (
    viewModel: AppHomeScreenViewModel ,
    modifier: Modifier = Modifier
) {
    val favoriteList by  viewModel.favouriteList.collectAsState()
    LazyColumn(modifier = modifier , verticalArrangement = Arrangement.spacedBy(2.dp)) {
        items(items = favoriteList.list ){
            val departureAirport by viewModel.getAirportByIdCode(it.destination_code).collectAsState()
            val destinationAirport by viewModel.getAirportByIdCode(it.departure_code).collectAsState()
            ResultCard(
                departureAirport =departureAirport?.airport ?: defaultAirport ,
                destinationAirport = destinationAirport?.airport ?: defaultAirport ,
                onStarClicked = { viewModel.removeFromFavouriteList(it)
                } ,
                isAddedToFavourite = true
            )
        }
    }
}

val mockListOfFavourite = listOf(Favourite(destination_code =  "OPO", departure_code ="ARN"))