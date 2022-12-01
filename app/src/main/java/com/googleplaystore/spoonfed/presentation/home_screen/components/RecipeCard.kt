package com.googleplaystore.spoonfed.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.googleplaystore.spoonfed.domain.models.Recipe

@Composable
fun RecipeCard(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier
            .padding(0.dp, 8.dp)
            .clickable(onClick = onClick)
    ) {
        Image(
            modifier = modifier
                .width(100.dp)
                .height(100.dp)
                .padding(8.dp)
                .clip(shape = RoundedCornerShape(1.dp)),
            painter = rememberAsyncImagePainter(recipe.image),
            contentDescription = recipe.title,
        )

        Text(
            fontSize = 12.sp,
            text = recipe.title
        )
    }

}