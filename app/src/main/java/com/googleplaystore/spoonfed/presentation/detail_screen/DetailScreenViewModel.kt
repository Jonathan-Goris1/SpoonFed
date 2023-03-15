package com.googleplaystore.spoonfed.presentation.detail_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.googleplaystore.spoonfed.domain.models.Recipe
import com.googleplaystore.spoonfed.domain.repository.RecipeRepository
import com.googleplaystore.spoonfed.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val repository: RecipeRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var expandedText by mutableStateOf("View Info +")
        private set

    var isExpanded by mutableStateOf(false)
        private set

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState

     val recipeID: Int = checkNotNull(savedStateHandle["recipeId"])

    init {
        getRecipeByID()
    }
    fun isExpandedView(notExpanded: Boolean){
        isExpanded = notExpanded
        expandedText = if(notExpanded){
            "Hide Info -"
        }else{
            "View Info +"
        }
    }

    private fun getRecipeByID() {
        viewModelScope.launch {
            when (
                val result = repository.getRecipeWithID(recipeID = recipeID)
            ) {
               is Result.Success -> {
                   _uiState.value = DetailUiState.Success(result.data)
               }
                is Result.Error -> {
                    _uiState.value = DetailUiState.Error(result.message)
                }

            }
        }

    }
}

sealed interface DetailUiState {
    data class Success(val recipes: Recipe?) : DetailUiState
    data class Error(val message: String? = "") : DetailUiState
    object Loading : DetailUiState
}