package com.example.airportticketapp.ui.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.airportticketapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHomeScreen(
    modifier: Modifier = Modifier
) {
     Scaffold(
         topBar = { TopAppBar() } ,
         modifier = modifier.fillMaxSize()
     ) {
         Column(
             modifier = Modifier
                 .fillMaxSize()
                 .padding(it)
         ) {
             TextField(

                 shape = AbsoluteRoundedCornerShape(20.dp),
                 label = { Text(text = "adj") },
                 value = "value",
                 onValueChange = { },
                 modifier = Modifier
                     .padding(
                         top = 8.dp ,
                         start = 4.dp ,
                         end = 4.dp
                         )
                     .fillMaxWidth() ,
                 leadingIcon = {
                     IconButton(onClick = { }) {
                         Icon(
                             imageVector = Icons.Default.Search,
                             contentDescription = "Search Icon"
                         )
                     }
                 },
                 trailingIcon = {
                     IconButton(onClick = { /*TODO*/ }) {
                         Icon(
                             painter = painterResource(id = R.drawable.baseline_mic_none_24),
                             contentDescription = "Voice Search "
                         )
                     }
                 },

                 )
            SuggestionScreen(modifier = Modifier.padding())
         }
     }
}

@Composable
fun TopAppBar (
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Color(
                    0xFF3b5b7e
                )
            )
        ,

    ) {
        Text(
            text = "Flight Search",
            fontSize = 32.sp,
            modifier = Modifier.padding(
                start = 12.dp ,
                top = 20.dp ,
                bottom = 12.dp
            ) ,
           // fontWeight = FontWeight.SemiBold ,
            color = Color.White ,
        )
    }
}