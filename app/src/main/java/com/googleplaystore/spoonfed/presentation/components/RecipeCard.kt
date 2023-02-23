package com.googleplaystore.spoonfed.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    Column(modifier = modifier
           .padding(horizontal = 0.dp, vertical = 8.dp)
            .clickable(onClick = onClick)) {

        ImageLoader(
            modifier = modifier
                .width(120.dp)
                .height(100.dp),
            model = recipe?.image,
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