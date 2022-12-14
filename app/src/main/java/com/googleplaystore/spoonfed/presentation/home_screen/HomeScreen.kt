package com.googleplaystore.spoonfed.presentation.home_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.googleplaystore.spoonfed.R
import com.googleplaystore.spoonfed.presentation.home_screen.components.RecipeCard

private const val TAG: String = "HOME_SCREEN"


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Log.d(TAG, "HomeScreen: ${viewModel.state.isLoading}")

        OutlinedTextField(
            value = viewModel.state.searchQuery,
            onValueChange = { viewModel.updateSearchQuery(it) },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.White),
            placeholder = {
                Text(
                    text = "Search for recipes"
                )
            },
            keyboardActions = KeyboardActions { viewModel.getQueryRecipe(query = viewModel.state.searchQuery) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_search_24),
                    contentDescription = stringResource(id = R.string.searchDescription)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                placeholderColor = Color.LightGray,
                containerColor = Color.Transparent
            ),
            shape = CircleShape,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)

        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            ) {
            items(viewModel.state.recipes ?: emptyList()) { recipe ->
                RecipeCard(recipe = recipe) {

                }
            }
        }
        if (viewModel.state.isLoading) {
            Loading()
        }
        if (viewModel.state.hasError) {
            ErrorState(errorMessage = viewModel.state.errorMessage.toString())
        }

    }
}

@Composable
fun ErrorState(
    errorMessage: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = errorMessage)
    }
}

@Preview
@Composable
fun Loading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(64.dp),
            color = Color.White,
            strokeWidth = 8.dp
        )
    }

}
