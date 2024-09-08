package com.example.airportticketapp.ui.Screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.airportticketapp.R
import com.example.airportticketapp.model.Airport
import com.example.airportticketapp.model.Favourite


@Composable
fun AppHomeScreen(
    navController : NavHostController = rememberNavController() ,
    modifier: Modifier = Modifier ,
    viewModel : AppHomeScreenViewModel = viewModel(factory = AppHomeScreenViewModel.factory)
) {
    val airportUiState = viewModel.airportUiState.collectAsState()
    var  localTerm by rememberSaveable { mutableStateOf(airportUiState.value.searchWord) }
    val fullAirportList = viewModel.fullAirportList.collectAsState()
    val getUserSearch by  viewModel.getUserSearch.collectAsState()
    val giveSearchSuggestions = viewModel.searchSuggestion(localTerm).collectAsState()
    val getSelectedAirport = viewModel.getSelectedAirport.collectAsState()
     Scaffold(
         topBar = { TopAppBar() } ,
         modifier = modifier.fillMaxSize()
     ) {
         Column(
             modifier = Modifier
                 .fillMaxSize()
                 .padding(it)
         ) {
             AppHomeScreenElements(
                 onValueChange = {
                         currentTerm -> localTerm = currentTerm
                     airportUiState.value.searchWord  = currentTerm
                     viewModel.saveUserSearch(currentTerm)
                 },
                 value = localTerm ,
                 valueFromDatastore = getUserSearch.search
             )
             if (true) {
                 detailedScreen(
                     navController = navController,
                     viewModel = viewModel ,
                     selectedAirport = getSelectedAirport.value.selectedAirport  ?: defaultAirport ,
                     airportSuggestions = giveSearchSuggestions.value.searchList ,
                     fullList = fullAirportList.value.fullList ,
                     onstarClicked = {
                                     departureCode , destinationCOde ->
                         viewModel.addToFavoriteList(Favourite(destination_code = destinationCOde , departure_code = departureCode))
                      },
                     isAddedToFavourite = false,
                 )
             }
              FavouriteScreen(viewModel = viewModel , modifier = Modifier)

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

@Composable
fun AppHomeScreenElements (
    valueFromDatastore : String ,
    modifier: Modifier = Modifier ,
    onValueChange : (String) -> Unit  ,
    value : String
) {
    Row(modifier) {
        TextField(
            shape = AbsoluteRoundedCornerShape(20.dp),
            label = { Text(text = "Search Flight ") },
            value = value ,
            onValueChange = onValueChange ,
            placeholder = { Text(text = valueFromDatastore )},
            modifier = Modifier
                .padding(
                    top = 8.dp,
                    start = 4.dp,
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
    }
}

enum class  Navigation {
     SuggestionScreen , SearchScreen()
}

@Composable
fun detailedScreen (
    isAddedToFavourite: Boolean ,
    onstarClicked : (String , String ) -> Unit ,
    airportSuggestions  : List<Airport> ,
    fullList : List<Airport> ,
    selectedAirport : Airport ,
    modifier : Modifier = Modifier  ,
    navController: NavHostController  ,
    viewModel: AppHomeScreenViewModel ,
) {
    NavHost(navController = navController, startDestination = Navigation.SuggestionScreen.name , modifier = modifier ) {
        composable(route = Navigation.SuggestionScreen.name) {
            AnimatedVisibility(visible = airportSuggestions.isNotEmpty() , enter = fadeIn()  , exit = fadeOut()) {
                SuggestionScreen(
                    modifier = Modifier.padding() ,
                    airportSuggestions = airportSuggestions ,
                    onSuggestionChoose = { iataCode  ->
                        viewModel.updateSelectedAirport(iataCode)
                        navController.navigate(Navigation.SearchScreen.name)
                    })
            }
        }
        composable(
            route = Navigation.SearchScreen.name,
        ){
            SearchScreen(
                onStarClicked = onstarClicked ,
                isAddedToFavourite = false,
                arrivingList = fullList ,
                selectedAirport = selectedAirport
            )
        }
    }
}
