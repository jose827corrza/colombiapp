package com.josedev.colombiapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josedev.colombiapp.client.ClientImpl
import com.josedev.colombiapp.client.responses.toTouristAttractionState
import com.josedev.colombiapp.domain.repository.PresidentsEvent
import com.josedev.colombiapp.domain.repository.TouristicAttractionsEvent
import com.josedev.colombiapp.domain.state.PresidentState
import com.josedev.colombiapp.domain.state.StatusState
import com.josedev.colombiapp.domain.state.TouristAttractionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TouristicAttractionsVM @Inject constructor(
    private val repo: ClientImpl
): ViewModel() {

    private val _state = MutableStateFlow(listOf(TouristAttractionState()))
    private val _status = MutableStateFlow(StatusState())

    val state = _state.asStateFlow()
    val status = _status.asStateFlow()

    init {
        viewModelScope.launch {
            onEvent(TouristicAttractionsEvent.TouristicAttractions)
        }
    }

    fun onEvent(event: TouristicAttractionsEvent){
        when(event){
            TouristicAttractionsEvent.TouristicAttractions -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _status.update { it.copy(isLoading = true) }
                    val response = repo.touristicAttractions()
                    if (response.message != null){
                        _status.update { it.copy(isLoading = false, error = response.message, isError = true) }
                        return@launch
                    }
                    if(response.data != null){
                        _state.update { response.data.map { it.toTouristAttractionState() } }
                    }
                    _status.update { it.copy(isLoading = false, error = null, isError = false) }
                }
            }
        }
    }
}