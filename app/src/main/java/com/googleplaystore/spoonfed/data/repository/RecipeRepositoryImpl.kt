package com.googleplaystore.spoonfed.data.repository

import com.googleplaystore.spoonfed.data.mapper.toDomainModel
import com.googleplaystore.spoonfed.data.remote.service.RecipeService
import com.googleplaystore.spoonfed.domain.models.Recipe
import com.googleplaystore.spoonfed.domain.models.Recipes
import com.googleplaystore.spoonfed.domain.repository.RecipeRepository
import com.googleplaystore.spoonfed.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RecipeRepositoryImpl@Inject constructor(
    private val apiService: RecipeService,
    ): RecipeRepository {

    override suspend fun getRandomRecipes(): Resource<Recipes> {
        return try {
            val result = apiService.getRandomRecipes(number = 10, tags = "Dessert")
            Resource.Success(result.toDomainModel())
        } catch (e: IOException){
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load company info"
            )

        } catch (e: HttpException){
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load company info"
            )

        }
    }
}