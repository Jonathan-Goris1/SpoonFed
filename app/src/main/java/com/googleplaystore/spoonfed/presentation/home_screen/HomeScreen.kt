package com.googleplaystore.spoonfed.presentation.home_screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.googleplaystore.spoonfed.R
import com.googleplaystore.spoonfed.domain.models.Recipe
import com.googleplaystore.spoonfed.presentation.components.RecipeCard
import com.googleplaystore.spoonfed.presentation.components.ScrollToTopButton
import kotlinx.coroutines.launch

private const val TAG: String = "HOME_SCREEN"


@Composable
internal fun HomeRoute(
    onRecipeClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val homeUiState = homeViewModel.uiState.collectAsState().value
    HomeScreen(
        modifier = modifier,
        foodName = homeViewModel.searchQuery,
        onFoodNameChange = { homeViewModel.updateSearchQuery(homeViewModel.searchQuery) },
        getQueryRecipe = { homeViewModel.getQueryRecipe(homeViewModel.searchQuery) },
        homeUiState = homeUiState,
        onRecipeClick = onRecipeClick
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    modifier: Modifier,
    foodName: String,
    onFoodNameChange: (String) -> Unit,
    getQueryRecipe: (String) -> Unit,
    homeUiState: HomeUiState,
    onRecipeClick: (Int) -> Unit
) {

    val listState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()
    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }


    Scaffold(
        floatingActionButton = {
            AnimatedVisibility(visible = showButton, enter = fadeIn(), exit = fadeOut()) {
                ScrollToTopButton {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                }

            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            SearchBar(
                modifier = modifier,
                foodName = foodName,
                onFoodNameChange = { onFoodNameChange(foodName) },
                getQueryRecipe = { getQueryRecipe(foodName) },
                scrollToTop = { coroutineScope.launch { listState.scrollToItem(0) } })
            
            when (homeUiState) {
                is HomeUiState.Loading -> {
                    HomeLoadingWheel()
                }
                is HomeUiState.Error -> {
                    HomeErrorState(homeUiState.message)
                }
                is HomeUiState.Success -> {
                    RecipeItem(
                        recipe = homeUiState.recipes,
                        listState = listState,
                        onRecipeClick = onRecipeClick
                    )
                }

            }


        }

    }


}


@Composable
fun RecipeItem(
    recipe: List<Recipe>?,
    listState: LazyGridState,
    onRecipeClick: (Int) -> Unit,
) {

    LazyVerticalGrid(
        state = listState,
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ) {

        items(recipe ?: emptyList()) { recipe ->
            RecipeCard(recipe = recipe) {
                onRecipeClick(recipe.id ?: 0)
                Log.d(TAG, "RecipeItem: ${recipe.id}")
            }
        }


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier,
    foodName: String,
    onFoodNameChange: (String) -> Unit,
    getQueryRecipe: (String) -> Unit,
    scrollToTop: () -> Unit

) {
    OutlinedTextField(
        value = foodName,
        onValueChange = onFoodNameChange,
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(color = Color.White),
        placeholder = {
            Text(
                text = stringResource(id = R.string.SearchForRecipesText)
            )
        },
        //TODO When triggering KeyboardAction, the list doesn't scroll back to top position.
        keyboardActions = KeyboardActions { getQueryRecipe(foodName); scrollToTop() },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(id = R.string.searchDescription)
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            placeholderColor = Color.LightGray,
            containerColor = Color.Transparent
        ),
        shape = CircleShape,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)

    )
}

@Composable
fun HomeErrorState(
    errorMessage: String?
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = errorMessage ?: "No Internet")
    }
}


@Composable
fun HomeLoadingWheel(

) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(64.dp),
            color = Color.White,
            strokeWidth = 4.dp
        )
    }

}

@Preview
@Composable
fun ErrorStatePreview() {
    HomeErrorState(errorMessage = "No Internet available")
}

@Preview
@Composable
fun LoadingPreview() {
    HomeLoadingWheel()
}
