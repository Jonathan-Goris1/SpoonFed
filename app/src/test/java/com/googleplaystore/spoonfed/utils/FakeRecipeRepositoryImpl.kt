package com.googleplaystore.spoonfed.utils

import com.googleplaystore.spoonfed.domain.models.Recipe
import com.googleplaystore.spoonfed.domain.models.Recipes
import com.googleplaystore.spoonfed.domain.repository.RecipeRepository
import com.googleplaystore.spoonfed.util.Resource
import javax.inject.Inject

class FakeRecipeRepositoryImpl @Inject constructor() : RecipeRepository {
    val recipeByID = Recipe(
        image = "", title = "Burger1"
    )


    private val fakeRecipe: List<Recipe> = listOf(
        Recipe(
            image = "", title = "Burger1"
        ),
        Recipe(
            image = "", title = "Burger2"
        ),
        Recipe(
            image = "", title = "Burger3"
        ),
        Recipe(
            image = "", title = "Burger4"
        ),
        Recipe(
            image = "", title = "Burger5"
        ),
    )
    val randomRecipe: Recipes = Recipes(
        recipes = fakeRecipe, results = fakeRecipe
    )

    override suspend fun getRandomRecipes(): Resource<Recipes> {
        return Resource.Success(data = randomRecipe)
    }

    override suspend fun getQueryRecipes(query: String): Resource<Recipes> {
        return Resource.Success(data = randomRecipe)
    }

    override suspend fun getRecipeWithID(recipeID: Int): Resource<Recipe> {
        return Resource.Success(recipeByID)
    }
}