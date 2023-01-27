package com.googleplaystore.spoonfed.presentation.detail_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.googleplaystore.spoonfed.domain.models.Recipe


@Composable
fun DetailScreen(
    recipeID: Int?,
    onNavigateBackToHomeScreen: () -> Boolean,
    detailViewModel: DetailScreenViewModel = hiltViewModel()
) {
    val state = detailViewModel.uiState.collectAsState().value
    
    DetailItem(recipe = state.recipe, onNavigateBackToHomeScreen = {onNavigateBackToHomeScreen()})

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailItem(
    onNavigateBackToHomeScreen: () -> Boolean,
    modifier: Modifier = Modifier,
    recipe: Recipe?,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = recipe?.title ?: "",
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {onNavigateBackToHomeScreen() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent,

                    ),

                actions = {
                    IconButton(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.White)
                            .size(30.dp, 30.dp),
                        onClick = { /* doSomething() */ }) {
                        Icon(
                            modifier = Modifier.size(20.dp, 20.dp),
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Share Recipe"
                        )
                    }


                }
            )


        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(text = recipe?.title ?: "")

            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .height(250.dp),
                painter = rememberAsyncImagePainter(recipe?.image),
                contentDescription = recipe?.title,
                contentScale = ContentScale.FillBounds
            )

            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                    Text(
                        textAlign = TextAlign.Start,
                        text = "Ingredients for")
                Spacer(Modifier.weight(1f))
                    Text(
                        textAlign = TextAlign.End,
                        text = " ${recipe?.servings.toString()} servings")


            }




            //TODO Figure out what to do with ingredients and hwo to display them.
        }

    }
}