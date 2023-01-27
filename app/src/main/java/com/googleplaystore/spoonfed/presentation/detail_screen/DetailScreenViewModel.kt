package com.googleplaystore.spoonfed.presentation.detail_screen

import androidx.lifecycle.SavedStateHandle
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
class DetailScreenViewModel @Inject constructor(
    private val repository: RecipeRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(DetailScreenState(isLoading = true))
    val uiState: StateFlow<DetailScreenState> = _uiState.asStateFlow()
    private val recipeID: Int = savedStateHandle.get<Int>("recipeId")!!


    init {
        getRecipeByID()
    }

    private fun getRecipeByID() {
        viewModelScope.launch {
            when (
                val result = repository.getRecipeWithID(recipeID = recipeID)
            ) {
                is Resource.Success -> {
                    result.data?.let { recipe ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                recipe = recipe,
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