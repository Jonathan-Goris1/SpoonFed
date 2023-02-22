package com.googleplaystore.spoonfed.data.remote.service

import com.googleplaystore.spoonfed.data.remote.responses.RecipeResponse
import com.googleplaystore.spoonfed.data.remote.responses.RecipesResponse
import com.googleplaystore.spoonfed.util.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeService {

    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("limitLicense") limitLicense: Boolean = true,
        @Query("number") number: Int
    ): RecipesResponse

    @GET("recipes/complexSearch")
    suspend fun getQueryRecipes(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("query") query: String = "",
        @Query("number") number: Int
    ): RecipesResponse

    @GET("recipes/{id}/information?includeNutrition=true")
    suspend fun getRecipeByID(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String = API_KEY,
    ): RecipeResponse
}