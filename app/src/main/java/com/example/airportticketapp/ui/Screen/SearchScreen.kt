package com.example.airportticketapp.ui.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.airportticketapp.R
import com.example.airportticketapp.model.Airport
import com.example.airportticketapp.model.Favourite

@Composable
fun SearchScreen (
    onStarClicked: (String , String ) -> Unit ,
    isAddedToFavourite : Boolean ,
    arrivingList : List<Airport> ,
    selectedAirport : Airport ,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items = arrivingList , key = { it.id}){airport ,  ->
            ResultCard(departureAirport = selectedAirport, destinationAirport = airport , isAddedToFavourite = isAddedToFavourite , onStarClicked = { onStarClicked(selectedAirport.iata_code, airport.iata_code) })
        }
    }
}

@Composable
fun ResultCard (
    isAddedToFavourite : Boolean ,
    onStarClicked : ( ) -> Unit ,
    departureAirport : Airport ,
    destinationAirport: Airport ,
    modifier: Modifier  = Modifier
) {
    Column(modifier) {
        Row(Modifier.padding(horizontal = 4.dp , vertical = 2.dp)) {
            Text(text = departureAirport.iata_code , fontWeight = FontWeight.Bold, fontSize = 28.sp , modifier = Modifier.padding(end = 8.dp) )
            Text(text = departureAirport.name , fontSize = 20.sp)
        }
        Row(Modifier.padding(horizontal = 4.dp , vertical = 2.dp)) {
            Image(
                painter = painterResource(id = R.drawable.baseline_arrow_downward_24),
                contentDescription = null ,
            )
            Spacer(Modifier.weight(1f))
            IconButton(onClick = onStarClicked ) {
                if (isAddedToFavourite.equals(false)) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_star_border_24),
                        contentDescription = stringResource(id = R.string.add_to_favourite)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_star_24),
                        contentDescription = stringResource(id = R.string.remove_from_favourite)
                    )
                }
            }
        }
        Row(Modifier.padding(horizontal = 4.dp , vertical = 2.dp)) {
            Text(text = destinationAirport.iata_code, fontWeight = FontWeight.Bold, fontSize = 28.sp , modifier = Modifier.padding(end = 8.dp) )
            Text(text = destinationAirport.name , fontSize =  20.sp)
        }
        Divider(thickness = 2.dp)
    }
}