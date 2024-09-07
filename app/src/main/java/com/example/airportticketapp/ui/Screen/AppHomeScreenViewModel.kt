package com.example.airportticketapp.ui.Screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.airportticketapp.AppDataProvider
import com.example.airportticketapp.model.Airport
import com.example.airportticketapp.model.Favourite
import com.example.airportticketapp.repository.AirportRepository
import com.example.airportticketapp.repository.UserSearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val STOP_TIME_MILLIS = 5_000L

class AppHomeScreenViewModel(
    val airportRepository: AirportRepository ,
    val  userSearchRepository: UserSearchRepository ,
     savedStateHandle : SavedStateHandle ,
) : ViewModel() {
   private  val _airportUiState = MutableStateFlow(AirportUiState())
     val airportUiState : StateFlow<AirportUiState> = _airportUiState

     val getUserSearch : StateFlow<UserSearchUiState> = userSearchRepository.userSearch.map { userSearch ->
          UserSearchUiState(userSearch)
     }.stateIn(viewModelScope , SharingStarted.WhileSubscribed(STOP_TIME_MILLIS) , UserSearchUiState() )


     fun saveUserSearch(search : String ) = viewModelScope.launch {
            userSearchRepository.saveUserSearch(search)
     }

      fun searchSuggestion(name : String) : StateFlow<AirportUiState> {
        val searchName =  if (name == "") {
             ""
        }
          else "%$name%"

         return airportRepository.getAirportWithString(searchName)
               .map {
                    AirportUiState(searchList = it)
               }.stateIn(viewModelScope , SharingStarted.WhileSubscribed(STOP_TIME_MILLIS ) , AirportUiState())
     }

     val fullAirportList : StateFlow<AirportUiState> = airportRepository.getAllAirportItems().map {
          AirportUiState(fullList =  it)
     }.filterNotNull()
          .stateIn(viewModelScope, SharingStarted.WhileSubscribed(STOP_TIME_MILLIS ) , AirportUiState())



    val getSelectedAirport: StateFlow<AirportUiState> = _airportUiState
        .map { it.idCode }
        .filter { it.isNotBlank() }
        .transformLatest { idCode ->
            emit(AirportUiState(selectedAirport = airportRepository.getAirportWithIdCode(idCode).firstOrNull()))
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(STOP_TIME_MILLIS), AirportUiState())



fun updateSelectedAirport (id : String){
   _airportUiState.update {
       it.copy(idCode = id)
   }
}

    val favouriteList : StateFlow<FavouriteUiState> = airportRepository.getAllFavouriteItems()
        .filterNotNull()
        .map {
            FavouriteUiState(list = it)
    }.stateIn(viewModelScope , SharingStarted.WhileSubscribed(STOP_TIME_MILLIS)  , FavouriteUiState()  )


    fun createFavouriteList (favourite : Favourite ) : Pair<StateFlow<Airport> , StateFlow<Airport>> {
        val airport1  = getAirportByIdCode(favourite.departure_code)
        val airport2 =getAirportByIdCode(favourite.destination_code)
      return Pair(airport1, airport2)
    }

    fun getAirportByIdCode (idCode : String) : StateFlow<Airport> {
        return airportRepository.getAirportWithIdCode(idCode).filterNotNull()
            .map {
                it
            }.stateIn(viewModelScope , SharingStarted.WhileSubscribed(STOP_TIME_MILLIS)  , defaultAirport)
    }

    fun addToFavoriteList(favourite : Favourite) {
        viewModelScope.launch{
            airportRepository.insert(favourite)
        }
    }
    fun removeFromFavouriteList (favourite: Favourite ) {
        viewModelScope.launch {
            airportRepository.delete(favourite)
        }
    }

     companion object{
          val factory : ViewModelProvider.Factory = viewModelFactory {
               initializer {
                    val application  = (this [APPLICATION_KEY] as AppDataProvider)
                    val airportRepository = application.appContainter.airRepository
                    val userSearchRepository = application.userSearchRepository
                    AppHomeScreenViewModel(
                         userSearchRepository = userSearchRepository,
                         airportRepository = airportRepository ,
                         savedStateHandle = this.createSavedStateHandle()
                    )
               }
          }
     }



}

data class AirportUiState(
     val searchList : List<Airport> = listOf(),
     val favouriteList : List<Favourite> = listOf(),
     val fullList : List<Airport> = listOf() ,
     var  searchWord : String = "" ,
     var idCode : String = "" ,
    var selectedAirport : Airport? = defaultAirport
)
 val defaultAirport : Airport = Airport(
    iata_code = " " ,
    name = " " ,
    passengers = -1
)

data class UserSearchUiState(val search : String = "" )
data class FavouriteUiState (val list : List<Favourite> = emptyList())
