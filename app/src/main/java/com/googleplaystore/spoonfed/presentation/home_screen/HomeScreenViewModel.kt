package com.googleplaystore.spoonfed.presentation.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.googleplaystore.spoonfed.domain.repository.RecipeRepository
import com.googleplaystore.spoonfed.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {
    var state by mutableStateOf(HomeScreenState())

    init {
        getRandomRecipe()
    }
     fun updateSearchQuery(query: String){
        state = state.copy(searchQuery = query)
    }

    private fun getRandomRecipe() {
        viewModelScope.launch {
            when (
                val result = repository.getRandomRecipes()
            ) {
                is Resource.Success -> {
                    result.data?.let { recipe ->
                        state = state.copy(
                            isLoading = false,
                            recipes = recipe.recipes,
                            errorMessage = null
                        )
                    }

                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        errorMessage = result.message,
                        hasError = true

                    )
                }


            }
        }

    }

    fun getQueryRecipe(query: String) {
        viewModelScope.launch {
            when (
                val result = repository.getQueryRecipes(query = query)
            ) {
                is Resource.Success -> {
                    result.data?.let { recipe ->
                        state = state.copy(
                            isLoading = false,
                            recipes = recipe.results,
                            errorMessage = null
                        )
                    }

                }

                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        errorMessage = result.message,
                        hasError = true
                    )
                }

            }
        }

    }
}