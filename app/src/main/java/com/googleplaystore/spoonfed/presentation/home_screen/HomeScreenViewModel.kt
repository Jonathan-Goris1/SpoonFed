package com.googleplaystore.spoonfed.presentation.home_screen


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
class HomeScreenViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    var searchQuery by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState


    init {
        getRandomRecipe()
    }

    fun updateSearchQuery(query: String) {
        searchQuery = query

    }

    private fun getRandomRecipe() {
        viewModelScope.launch {
            when (
                val result = repository.getRandomRecipes()
            ) {
                is Result.Success -> {
                    _uiState.value = HomeUiState.Success(result.data?.recipes)

                }
                is Result.Error -> {
                    _uiState.value = HomeUiState.Error(result.message)
                }

            }

        }


    }

    //TODO figure out state and why error and loading state not updating with changes in network state
    fun getQueryRecipe(query: String) {
        viewModelScope.launch {
            when (
                val result = repository.getQueryRecipes(query = query)
            ){
                is Result.Success -> {
                    _uiState.value = HomeUiState.Success(result.data?.results)

                }
                is Result.Error -> {
                    _uiState.value = HomeUiState.Error(result.message)
                }

            }


        }
    }
}


sealed interface HomeUiState {
    data class Success(val recipes: List<Recipe>?) : HomeUiState
    data class Error(val message: String? = "") : HomeUiState
    object Loading : HomeUiState
}
