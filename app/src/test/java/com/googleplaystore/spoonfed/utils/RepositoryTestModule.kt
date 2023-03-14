package com.googleplaystore.spoonfed.utils

import com.googleplaystore.spoonfed.di.RepositoryModule
import com.googleplaystore.spoonfed.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
abstract class RepositoryTestModule {

    @Singleton
    @Binds
    abstract fun bindsFakeRecipesRepository(fakeRecipesRepositoryImpl: FakeRecipeRepositoryImpl): RecipeRepository

}