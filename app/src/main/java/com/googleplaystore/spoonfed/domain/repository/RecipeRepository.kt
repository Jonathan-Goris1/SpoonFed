package com.googleplaystore.spoonfed.domain.repository

import com.googleplaystore.spoonfed.domain.models.Recipes
import com.googleplaystore.spoonfed.util.Resource

interface RecipeRepository {

    suspend fun getRandomRecipes(): Resource<Recipes>
    suspend fun getQueryRecipes(query: String): Resource<Recipes>
}