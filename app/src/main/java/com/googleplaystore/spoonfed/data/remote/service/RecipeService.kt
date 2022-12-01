package com.googleplaystore.spoonfed.data.remote.service

import com.googleplaystore.spoonfed.data.remote.responses.RecipesResponse
import com.googleplaystore.spoonfed.util.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {

    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("apiKey") apiKey: String = API_KEY ,
        @Query("limitLicense") limitLicense: Boolean = true,
        @Query("tags") tags: String,
        @Query("number") number: Int
    ): RecipesResponse
}