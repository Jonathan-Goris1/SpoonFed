package com.googleplaystore.spoonfed.data.repository

import com.googleplaystore.spoonfed.data.mapper.toDomainModel
import com.googleplaystore.spoonfed.data.remote.service.RecipeService
import com.googleplaystore.spoonfed.domain.models.Recipe
import com.googleplaystore.spoonfed.domain.models.Recipes
import com.googleplaystore.spoonfed.domain.repository.RecipeRepository
import com.googleplaystore.spoonfed.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val apiService: RecipeService,
    private val iODispatcher: CoroutineDispatcher
) : RecipeRepository {

    override suspend fun getRandomRecipes(): Result<Recipes> {
        return withContext(iODispatcher) {
            try {
                val result = apiService.getRandomRecipes(number = 100)

                Result.Success(result.toDomainModel())

            } catch (e: IOException) {
                e.printStackTrace()
                Result.Error(
                    message = "No Internet Connection"
                )

            } catch (e: HttpException) {
                e.printStackTrace()
                Result.Error(
                    message = "${e.message.toString()} HttpException"
                )

            }
        }

    }

    override suspend fun getQueryRecipes(query: String): Result<Recipes> {
        return withContext(iODispatcher) {
            try {
                val result = apiService.getQueryRecipes(number = 100, query = query)
                Result.Success(result.toDomainModel())
            } catch (e: IOException) {
                e.printStackTrace()
                Result.Error(
                    message = "${e.message.toString()} IOException"
                )

            } catch (e: HttpException) {
                e.printStackTrace()
                Result.Error(
                    message = "${e.message.toString()} HttpException"
                )

            }
        }
    }

    override suspend fun getRecipeWithID(recipeID: Int): Result<Recipe> {
        return withContext(iODispatcher) {
            try {
                val result = apiService.getRecipeByID(id = recipeID)
                Result.Success(result.toDomainModel())
            } catch (e: IOException) {
                e.printStackTrace()
                Result.Error(
                    message = "${e.message.toString()} IOException"
                )

            } catch (e: HttpException) {
                e.printStackTrace()
                Result.Error(
                    message ="${e.message.toString()} HttpException"
                )

            }
        }

    }
}