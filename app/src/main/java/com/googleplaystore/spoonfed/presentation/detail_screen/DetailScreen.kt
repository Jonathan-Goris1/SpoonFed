package com.googleplaystore.spoonfed.presentation.detail_screen

import android.view.View
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.googleplaystore.spoonfed.R
import com.googleplaystore.spoonfed.domain.models.*
import com.googleplaystore.spoonfed.presentation.components.HeaderRow
import com.googleplaystore.spoonfed.presentation.components.ImageLoader


@Composable
fun DetailScreen(
    onNavigateBackToHomeScreen: () -> Boolean,
    detailViewModel: DetailScreenViewModel = hiltViewModel()
) {
    val state = detailViewModel.uiState.collectAsState().value

    DetailItem(recipe = state.recipe, onNavigateBackToHomeScreen = { onNavigateBackToHomeScreen() }, isExpanded = state.isExpanded, isExpandedText = state.expandedText, expandEvent = { detailViewModel.isExpandedView(notExpanded = !state.isExpanded) })

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailItem(
    onNavigateBackToHomeScreen: () -> Boolean,
    recipe: Recipe?,
    isExpanded: Boolean,
    isExpandedText: String,
    expandEvent: () -> Unit
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
                    IconButton(onClick = { onNavigateBackToHomeScreen() }) {
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
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            item { ImageProfile(title = recipe?.title ?: "", image = recipe?.image ?: "") }
            item {
                HeaderRow(
                    text1 = stringResource(id = R.string.IngredientsHeaderText),
                    text2 = "${recipe?.servings ?: 0} servings",
                )
            }
            ingredientContent(ingredientsList = recipe?.extendedIngredients ?: emptyList())

            item { HeaderRow(text1 = stringResource(id = R.string.NutritionHeaderText), text2 = isExpandedText, onClick = {expandEvent()}) }
            if(isExpanded){
                nutrientsContent(nutrientList = recipe?.nutrition?.nutrients ?: emptyList())
            }


            item { HeaderRow(text1 = stringResource(id = R.string.PreparationHeaderText)) }
            preparationContent(instructions = recipe?.analyzedInstructions ?: emptyList())

        }

    }
}

@Composable
fun ImageProfile(
    title: String,
    image: String
) {
    Text(text = title)

    ImageLoader(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        model = image,
        contentDescription = title,
        contentScale = ContentScale.FillBounds
    )
}


fun LazyListScope.ingredientContent(
    ingredientsList: List<ExtendedIngredient>
) {
    items(ingredientsList) { ingredient ->
        IngredientSection(ingredients = ingredient)
    }
}

@Composable
fun IngredientSection(
    ingredients: ExtendedIngredient
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            textAlign = TextAlign.Start,
            text = ingredients.name ?: ""
        )
        Spacer(Modifier.weight(1f))


        AndroidView(factory = { context ->
            TextView(context).apply {
                text = "${
                    HtmlCompat.fromHtml(
                        "${ingredients.amount}",
                        HtmlCompat.FROM_HTML_MODE_LEGACY
                    )
                } ${ingredients.unit}"
                setTextColor(resources.getColor(R.color.white))
                textSize = 16f
                textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            }
        })
    }
    Divider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = Color.DarkGray
    )
}


fun LazyListScope.nutrientsContent(
    nutrientList: List<NutrientX>
) {
    items(nutrientList) { nutrient ->
        NutrientsSection(nutrient = nutrient)
    }
}

@Composable
fun NutrientsSection(
    nutrient: NutrientX
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            textAlign = TextAlign.Start,
            text = nutrient.name ?: ""
        )
        Spacer(Modifier.weight(1f))

        Text(
            textAlign = TextAlign.End,
            text = " ${nutrient.amount} ${nutrient.unit}"
        )
    }
    Divider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = Color.DarkGray
    )
}

fun LazyListScope.preparationContent(
    instructions: List<AnalyzedInstruction>
) {
    items(instructions) { instruction ->
        for (element in instruction.steps!!) {
            PreparationSection(step = element)
        }


    }

}

@Composable
fun PreparationSection(
    step: Step
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)

    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
        ) {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "${step.number}"
            )
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))

            Text(
                text = step.step ?: "",

                )
        }

    }


}