package com.example.airportticketapp.ui.Screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.airportticketapp.model.Airport
import com.example.airportticketapp.model.Favourite

@Composable
fun FavouriteScreen (
    viewModel: AppHomeScreenViewModel ,
    modifier: Modifier = Modifier
) {
    val favoriteList = viewModel.favouriteList.collectAsState().value
    LazyColumn(modifier = modifier) {
        items(items = favoriteList.list , key = { it.id}){
            val (departure, destination) = viewModel.createFavouriteList(it)
            val departureAirport by departure.collectAsState()
            val destinationAirport by destination.collectAsState()
            ResultCard(
                departureAirport =departureAirport ,
                destinationAirport = destinationAirport ,
                onStarClicked = {
                } ,
                isAddedToFavourite = true
            )
        }
    }
}