package com.googleplaystore.spoonfed.presentation.detail_screen

import android.os.Build
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.googleplaystore.spoonfed.R
import com.googleplaystore.spoonfed.domain.models.*
import com.googleplaystore.spoonfed.presentation.components.HeaderRow
import com.googleplaystore.spoonfed.presentation.components.HeaderText
import com.googleplaystore.spoonfed.presentation.components.ImageLoader
import com.googleplaystore.spoonfed.presentation.components.ScrollToTopButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val TAG: String = "DetailScreen"

@RequiresApi(Build.VERSION_CODES.M)
@Composable
internal fun DetailRoute(
    onBackClick: () -> Unit,
    modifier: Modifier,
    detailViewModel: DetailScreenViewModel = hiltViewModel()
) {
    val detailUIState = detailViewModel.uiState.collectAsState().value

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }
    Log.d(TAG, "DetailRoute: Screen created")
    DetailScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        detailUIState = detailUIState,
        isExpanded = detailViewModel.isExpanded,
        isExpandedText = detailViewModel.expandedText,
        expandEvent = { detailViewModel.isExpandedView(!detailViewModel.isExpanded) },
        showButton = showButton,
        listState = listState,
        coroutineScope = coroutineScope


    )
}

@RequiresApi(Build.VERSION_CODES.M)
@Composable
internal fun DetailScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    detailUIState: DetailUiState,
    isExpanded: Boolean,
    isExpandedText: String,
    expandEvent: () -> Unit,
    showButton: Boolean,
    listState: LazyListState,
    coroutineScope: CoroutineScope
) {
    when (detailUIState) {
        is DetailUiState.Loading -> {
            Log.d(TAG, "DetailScreen: Loading")

            DetailLoadingWheel(modifier = modifier)
        }
        is DetailUiState.Error -> {
            Log.d(TAG, "DetailScreen: Error")

            DetailErrorState(errorMessage = detailUIState.message, modifier = modifier)
        }
        is DetailUiState.Success -> {
            Log.d(TAG, "DetailScreen: Success")

            DetailItem(
                modifier = modifier,
                onBackClick = onBackClick,
                recipe = detailUIState.recipes,
                isExpanded = isExpanded,
                isExpandedText = isExpandedText,
                expandEvent = expandEvent,
                showButton = showButton,
                listState = listState,
                coroutineScope = coroutineScope
            )
        }
    }

}

@RequiresApi(Build.VERSION_CODES.M)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailItem(
    modifier: Modifier,
    onBackClick: () -> Unit,
    recipe: Recipe?,
    isExpanded: Boolean,
    isExpandedText: String,
    expandEvent: () -> Unit,
    showButton: Boolean,
    listState: LazyListState,
    coroutineScope: CoroutineScope
) {
    Scaffold(
        floatingActionButton = {
            AnimatedVisibility(visible = showButton, enter = fadeIn(), exit = fadeOut()) {
                ScrollToTopButton(goToTop = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                })

            }
        },

        topBar = {
            TopAppBar(
                title = {

                },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.GoBack)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent,

                    ),

                actions = {
                    IconButton(
                        modifier = modifier
                            .clip(CircleShape)
                            .background(Color.White)
                            .size(30.dp, 30.dp),
                        onClick = { /* doSomething() */ }) {
                        Icon(
                            modifier = modifier.size(20.dp, 20.dp),
                            imageVector = Icons.Filled.Share,
                            contentDescription = stringResource(id = R.string.ShareRecipeContentDescription)
                        )
                    }


                }
            )
        }


    ) { paddingValues ->
        LazyColumn(
            state = listState,
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = 80.dp),
        ) {
            item {
                ImageProfile(
                    title = recipe?.title ?: "",
                    image = recipe?.image ?: "",
                    modifier = modifier
                )
            }

            ingredientContent(
                ingredientsList = recipe?.extendedIngredients ?: emptyList(),
                modifier = modifier,
                recipe = recipe
            )

            item {
                HeaderRow(
                    text1 = stringResource(id = R.string.NutritionHeaderText),
                    text2 = isExpandedText,
                    onClick = { expandEvent() })
            }
            if (isExpanded) {
                nutrientsContent(
                    nutrientList = recipe?.nutrition?.nutrients ?: emptyList(),
                    modifier = modifier
                )
            }


            preparationContent(
                instructions = recipe?.analyzedInstructions ?: emptyList(),
                modifier = modifier
            )

        }

    }
}

@Composable
fun ImageProfile(
    modifier: Modifier,
    title: String,
    image: String
) {
    HeaderText(
        text = title,
        textAlign = TextAlign.Center,
        modifier = modifier.padding(start = 8.dp),
        maxLines = 4,
    )

    ImageLoader(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp),
        model = image,
        contentDescription = title,
        contentScale = ContentScale.FillBounds
    )
}


@RequiresApi(Build.VERSION_CODES.M)
fun LazyListScope.ingredientContent(
    modifier: Modifier,
    ingredientsList: List<ExtendedIngredient>,
    recipe: Recipe?
) {
    item {
        HeaderRow(
            text1 = stringResource(id = R.string.IngredientsHeaderText),
            text2 = "${recipe?.servings ?: 0} ${stringResource(id = R.string.ServingsHeaderText)}",
        )
    }
    items(ingredientsList) { ingredient ->
        IngredientSection(ingredients = ingredient, modifier = modifier)
    }
}

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun IngredientSection(
    modifier: Modifier,
    ingredients: ExtendedIngredient
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            textAlign = TextAlign.Start,
            text = ingredients.name ?: "",
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier.weight(1f))


        AndroidView(factory = { context ->
            TextView(context).apply {
                text = "${
                    HtmlCompat.fromHtml(
                        "${ingredients.amount} ",
                        HtmlCompat.FROM_HTML_MODE_LEGACY
                    )
                }"
                setTextColor(resources.getColor(R.color.white, resources.newTheme()))
                textSize = 16f
                textAlignment = View.TEXT_ALIGNMENT_TEXT_END

            }
        })

        Text(
            textAlign = TextAlign.Start,
            text = ingredients.unit ?: "",
            style = MaterialTheme.typography.labelLarge
        )
    }
    Divider(
        modifier = modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = Color.DarkGray
    )
}


fun LazyListScope.nutrientsContent(
    modifier: Modifier,
    nutrientList: List<NutrientX>,

    ) {
    val limitedNutrientsList = nutrientList.take(9)

    items(limitedNutrientsList) { nutrient ->
        NutrientsSection(nutrient = nutrient, modifier = modifier)
    }
}

@Composable
fun NutrientsSection(
    modifier: Modifier,
    nutrient: NutrientX
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            textAlign = TextAlign.Start,
            text = nutrient.name ?: "",
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier.weight(1f))

        Text(
            textAlign = TextAlign.End,
            text = " ${nutrient.amount} ${nutrient.unit}",
            style = MaterialTheme.typography.labelLarge
        )
    }
    Divider(
        modifier = modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = Color.DarkGray
    )
}

fun LazyListScope.preparationContent(
    modifier: Modifier,
    instructions: List<AnalyzedInstruction>
) {
    item { HeaderRow(text1 = stringResource(id = R.string.PreparationHeaderText)) }
    items(instructions) { instruction ->
        for (element in instruction.steps!!) {
            PreparationSection(step = element, modifier = modifier)
        }


    }

}

@Composable
fun PreparationSection(
    modifier: Modifier,
    step: Step
) {

    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                shape = RoundedCornerShape(4.dp)
            ),
    ) {
        Text(
            modifier = modifier.padding(start = 8.dp),
            text = "${step.number}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = modifier.padding(horizontal = 8.dp))

        Text(
            text = step.step ?: "N/A",
            style = MaterialTheme.typography.bodyLarge

        )
    }

}

@Composable
fun DetailLoadingWheel(
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = modifier.size(64.dp),
            color = Color.White,
            strokeWidth = 4.dp
        )
    }
}

@Composable
fun DetailErrorState(
    modifier: Modifier,
    errorMessage: String?
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = errorMessage ?: "No Internet")
    }
}

