package com.googleplaystore.spoonfed.presentation.home_screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.googleplaystore.spoonfed.domain.repository.RecipeRepository
import com.googleplaystore.spoonfed.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {


    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState: StateFlow<HomeScreenState> = _uiState.asStateFlow()


    init {
        getRandomRecipe()
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }

    }

    private fun getRandomRecipe() {
        viewModelScope.launch {
            when (
                val result = repository.getRandomRecipes()
            ) {
                is Resource.Success -> {
                    result.data?.let { recipe ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                recipes = recipe.recipes,
                                errorMessage = null
                            )
                        }
                    }

                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.message,
                            hasError = true
                        )
                    }
                }

            }

        }


    }

    //TODO figure out state and why error and loading state not updating with changes in network state
    fun getQueryRecipe(query: String) {
        viewModelScope.launch {
            when (
                val result = repository.getQueryRecipes(query = query)
            ) {
                is Resource.Success -> {
                    result.data?.let { recipe ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                recipes = recipe.results,
                                errorMessage = null
                            )
                        }
                    }

                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.message,
                            hasError = true
                        )
                    }
                }

            }


        }
    }
}



