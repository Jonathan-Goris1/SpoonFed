package com.googleplaystore.spoonfed.domain.repository

import com.googleplaystore.spoonfed.domain.models.Recipe
import com.googleplaystore.spoonfed.domain.models.Recipes
import com.googleplaystore.spoonfed.util.Result

interface RecipeRepository {

    suspend fun getRandomRecipes(): Result<Recipes>
    suspend fun getQueryRecipes(query: String): Result<Recipes>
    suspend fun getRecipeWithID(recipeID: Int): Result<Recipe>
}