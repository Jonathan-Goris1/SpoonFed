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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    modifier: Modifier,
    homeViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val homeUiState = homeViewModel.uiState.collectAsState()
    val searchQuery = homeViewModel.searchQuery.collectAsState()
    Log.d("HomeRoute", "HomeRoute: Screen Created")


    HomeScreen(
        modifier = modifier,
        foodName = searchQuery.value,
        onFoodNameChange = { input -> homeViewModel.updateSearchQuery(input) },
        getQueryRecipe = { homeViewModel.getQueryRecipe(searchQuery.value) },
        homeUiState = homeUiState.value,
        onRecipeClick = onRecipeClick
    )


}


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


            when (homeUiState) {
                is HomeUiState.Loading -> {
                    Log.d("HomeScreen", "HomeScreen: Loading")

                    HomeLoadingWheel(modifier = modifier)
                }
                is HomeUiState.Error -> {
                    Log.d("HomeScreen", "HomeScreen: Error")

                    HomeErrorState(modifier = modifier, errorMessage = homeUiState.message)
                }
                is HomeUiState.Success -> {
                    Log.d("HomeScreen", "HomeScreen: Success")

                    SearchBar(
                        modifier = modifier,
                        foodName = foodName,
                        onFoodNameChange = { onFoodNameChange(it) },
                        getQueryRecipe = { getQueryRecipe(foodName) },
                        scrollToTop = { coroutineScope.launch { listState.scrollToItem(0) } })

                    RecipeItem(
                        modifier = modifier,
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
    modifier: Modifier,
    recipe: List<Recipe>?,
    listState: LazyGridState,
    onRecipeClick: (Int) -> Unit,
) {

    LazyVerticalGrid(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        state = listState,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(bottom = 80.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),

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
        onValueChange = { onFoodNameChange(it) },
        maxLines = 1,
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyMedium,
        placeholder = {
            Text(
                fontSize = 18.sp,
                text = stringResource(id = R.string.SearchForRecipesText),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        },

        keyboardActions = KeyboardActions {
            if (foodName.isNotBlank()) {
                getQueryRecipe(foodName); scrollToTop()
            } else {
                TODO("Add SnackBar message here")
            }
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(id = R.string.searchDescription),
                tint = Color.White
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.Transparent
        ),
        shape = CircleShape,
        modifier = modifier
            .height(70.dp)
            .fillMaxWidth()
            .padding(8.dp)

    )
}

@Composable
fun HomeErrorState(
    modifier: Modifier,
    errorMessage: String?
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            modifier = modifier.size(100.dp),
            imageVector = ImageVector.vectorResource(R.drawable.baseline_signal_wifi_statusbar_connected_no_internet_4_24),
            contentDescription = "No Internet",

            )
        Text(
            text = errorMessage ?: "No Internet",
            style = MaterialTheme.typography.titleLarge
        )
    }
}


@Composable
fun HomeLoadingWheel(
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = modifier.size(64.dp),
            color = MaterialTheme.colorScheme.onSurface,
            strokeWidth = 4.dp
        )
    }

}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(
        modifier = Modifier,
        foodName = "",
        onFoodNameChange = {},
        getQueryRecipe = {},
        scrollToTop = {}
    )
}

@Preview
@Composable
fun ErrorStatePreview() {
    HomeErrorState(errorMessage = "No Internet available", modifier = Modifier)
}

@Preview
@Composable
fun LoadingPreview() {
    HomeLoadingWheel(modifier = Modifier)
}
