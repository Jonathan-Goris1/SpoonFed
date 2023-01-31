package com.googleplaystore.spoonfed.data.mapper

import com.googleplaystore.spoonfed.data.remote.responses.RecipeResponse
import com.googleplaystore.spoonfed.domain.models.Recipe

fun RecipeResponse.toDomainModel(): Recipe = Recipe(

    aggregateLikes = aggregateLikes,
    analyzedInstructions = analyzedInstructions?.map { it.toDomainModel() },
    cheap = cheap,
    cookingMinutes = cookingMinutes,
    creditsText = creditsText,
    cuisines = cuisines,
    dairyFree = dairyFree,
    diets = diets,
    dishTypes = dishTypes,
    extendedIngredients = extendedIngredients?.map { it.toDomainModel() },
    gaps = gaps,
    glutenFree = glutenFree,
    healthScore = healthScore,
    id = id,
    image = image,
    imageType = imageType,
    instructions = instructions,
    license = license,
    lowFodmap = lowFodmap,
    occasions = occasions,
    originalId = originalId,
    preparationMinutes = preparationMinutes,
    pricePerServing = pricePerServing,
    readyInMinutes = readyInMinutes,
    servings = servings,
    sourceName = sourceName,
    sourceUrl = sourceUrl,
    spoonacularSourceUrl = spoonacularSourceUrl,
    summary = summary,
    sustainable = sustainable,
    title = title,
    vegan = vegan,
    vegetarian = vegetarian,
    veryHealthy = veryHealthy,
    veryPopular = veryPopular,
    weightWatcherSmartPoints = weightWatcherSmartPoints,
    nutrition = nutrition?.toDomainModel()
)