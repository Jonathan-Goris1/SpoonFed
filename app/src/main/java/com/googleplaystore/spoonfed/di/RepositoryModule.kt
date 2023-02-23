package com.googleplaystore.spoonfed.di

import com.googleplaystore.spoonfed.data.remote.service.RecipeService
import com.googleplaystore.spoonfed.data.repository.RecipeRepositoryImpl
import com.googleplaystore.spoonfed.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun bindRecipeRepository(
        apiService: RecipeService,
        @Dispatcher(NiaDispatchers.IO) ioDispatcher: CoroutineDispatcher,
    ): RecipeRepository {
        return RecipeRepositoryImpl(apiService, ioDispatcher)
    }
}
