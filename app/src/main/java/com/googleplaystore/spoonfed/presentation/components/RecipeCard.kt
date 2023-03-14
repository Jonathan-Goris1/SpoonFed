package com.googleplaystore.spoonfed.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.googleplaystore.spoonfed.domain.models.Recipe

@Composable
fun RecipeCard(
    modifier: Modifier = Modifier,
    recipe: Recipe?,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
    ) {

        ImageLoader(
            modifier = modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = recipe?.image,
            contentDescription = recipe?.title,
            contentScale = ContentScale.FillBounds
        )

        Text(
            fontSize = 12.sp,
            text = recipe?.title ?: "",
            style = MaterialTheme.typography.labelLarge,
            maxLines = 3,
            lineHeight = 1.em,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(start = 4.dp, top = 4.dp, end = 0.dp, bottom = 0.dp)
                .fillMaxWidth()
        )
    }


}