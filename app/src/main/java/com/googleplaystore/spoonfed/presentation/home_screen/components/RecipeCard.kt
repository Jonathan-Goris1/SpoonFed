package com.googleplaystore.spoonfed.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.googleplaystore.spoonfed.domain.models.Recipe

@Composable
fun RecipeCard(
    modifier: Modifier = Modifier,
    recipe: Recipe?,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier
            .padding(0.dp, 8.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Image(
            modifier = modifier
                .width(150.dp)
                .height(150.dp)
                .clip(shape = RoundedCornerShape(5.dp)),
            painter = rememberAsyncImagePainter(recipe?.image),
            contentDescription = recipe?.title,
            contentScale = ContentScale.FillBounds
        )

        Text(
            fontSize = 12.sp,
            text = recipe?.title ?: "",
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 0.dp)
        )
    }

}