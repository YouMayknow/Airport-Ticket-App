package com.example.airportticketapp.ui.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.airportticketapp.model.Airport
import java.util.Locale

@Composable
fun SuggestionScreen (
    onSuggestionChoose : (String) -> Unit ,
    airportSuggestions: List<Airport>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(items = airportSuggestions, key = { it.id})  {airport ->
        Row(modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 4.dp)
            .fillMaxWidth()
            .clickable {onSuggestionChoose(airport.iata_code)}
        ) {
                Text(text = airport.iata_code.uppercase(Locale.ROOT), Modifier.padding(horizontal = 4.dp ) , fontWeight = FontWeight.Bold)
            Text(text = airport.name ,Modifier.padding(horizontal =  4.dp) , maxLines =  1)
            }
            Divider(thickness = 1.dp)
        }
    }
}
