package com.googleplaystore.spoonfed.presentation.detail_screen

import androidx.lifecycle.ViewModel
import com.googleplaystore.spoonfed.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {
}