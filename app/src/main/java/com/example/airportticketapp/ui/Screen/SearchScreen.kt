package com.example.airportticketapp.ui.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.airportticketapp.R
import com.example.airportticketapp.model.Airport
import com.example.airportticketapp.model.Favourite
import com.example.airportticketapp.ui.theme.AirportTicketAppTheme

@Composable
fun SearchScreen (
    onStarClicked: (String , String ) -> Unit ,
    isAddedToFavourite : Boolean ,
    arrivingList : List<Airport> ,
    selectedAirport : Airport ,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize() , verticalArrangement = Arrangement.spacedBy(2.dp)) {
        items(items = arrivingList , key = { it.id}){airport ,  ->
            ResultCard(departureAirport = selectedAirport, destinationAirport = airport , isAddedToFavourite = isAddedToFavourite , onStarClicked = { onStarClicked(selectedAirport.iata_code, airport.iata_code) })
        }
    }
}

@Composable
fun ResultCard (
    isDarktheme : Boolean = isSystemInDarkTheme() ,
    isAddedToFavourite : Boolean ,
    onStarClicked : ( ) -> Unit ,
    departureAirport : Airport ,
    destinationAirport: Airport ,
    modifier: Modifier  = Modifier
) {
    Column(modifier) {
        Row(Modifier.padding(start =  8.dp , end = 8.dp  ,  top = 2.dp)) {
            Text(text = departureAirport.iata_code , fontWeight = FontWeight.Bold, fontSize = 16.sp , modifier = Modifier.padding(end = 8.dp) , maxLines =  1 ,  )
            Text(text = departureAirport.name , fontSize = 14.sp , maxLines = 1)
        }
        Row(verticalAlignment = Alignment.CenterVertically , modifier = Modifier.padding(end = 16.dp)){
            Image(
                colorFilter = if (isDarktheme) ColorFilter.tint(Color.White) else ColorFilter.tint(Color.Black) ,
                modifier = modifier
                    .height(28.dp)
                    .padding(horizontal = 12.dp),
                painter = painterResource(id = R.drawable.baseline_arrow_downward_24),
                contentDescription = null ,

            )
            Spacer(Modifier.weight(1f))
            IconButton(onClick = onStarClicked , Modifier.size(28.dp) ) {
                if (isAddedToFavourite.equals(false)) {
                    Image(
                        colorFilter = if (isDarktheme) ColorFilter.tint(Color.White) else ColorFilter.tint(Color.Black),
                        painter = painterResource(id = R.drawable.baseline_star_outline_24),
                        contentDescription = stringResource(id = R.string.add_to_favourite)
                    )
                } else {
                    Image(
                        colorFilter = if (isDarktheme) ColorFilter.tint(Color.White) else ColorFilter.tint(Color.Black),
                        painter = painterResource(id = R.drawable.baseline_star_24),
                        contentDescription = stringResource(id = R.string.remove_from_favourite)
                    )
                }
            }
        }
        Row(Modifier.padding(start =  8.dp , end = 8.dp  ,  bottom = 2.dp)) {
            Text(text = destinationAirport.iata_code, fontWeight = FontWeight.Bold, fontSize = 16.sp , modifier = Modifier.padding(end = 8.dp) , maxLines =  1 )
            Text(text = destinationAirport.name , fontSize =  14.sp , maxLines =  1 )
        }
        Divider(thickness = 2.dp)
    }
}
//
//@Composable
//@Preview()
//fun ResultCardPreview () {
//    AirportTicketAppTheme(darkTheme = false){
//        ResultCard(
//            isAddedToFavourite = true  ,
//            onStarClicked = { /*TODO*/ },
//            departureAirport = Airport(name = "Indira gandhi international airport " , iata_code = "DSF", passengers =  5434),
//            destinationAirport = Airport(name = "ShriMahraja international airport " , iata_code = "R34" , passengers = 4234)
//        )
//    }
//
//}