package com.googleplaystore.spoonfed.data.remote.service

import com.googleplaystore.spoonfed.data.remote.responses.RecipesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {

    @GET("recipe/{recipe_id}")
    suspend fun getRecipe(
        @Query("limitLicense") limitLicense: Boolean = true,
        @Query("tags") tags: String,
        @Query("number") number: Int
    ): RecipesResponse
}