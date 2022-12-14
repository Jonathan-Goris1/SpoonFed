package com.googleplaystore.spoonfed.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
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
            .padding(horizontal = 0.dp, vertical = 8.dp)
            .clickable(onClick = onClick)
            .clip(shape = RectangleShape),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Image(
            modifier = modifier
                .width(120.dp)
                .height(100.dp),
            painter = rememberAsyncImagePainter(recipe?.image),
            contentDescription = recipe?.title,
            contentScale = ContentScale.FillBounds
        )

        Text(
            fontSize = 12.sp,
            text = recipe?.title ?: "",
            maxLines = 3,
            lineHeight = 1.em,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 4.dp)
                .width(120.dp)
        )
    }

}