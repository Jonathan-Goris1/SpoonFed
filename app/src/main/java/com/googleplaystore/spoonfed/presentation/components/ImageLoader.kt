package com.googleplaystore.spoonfed.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.googleplaystore.spoonfed.R

@Composable
fun ImageLoader(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.FillBounds

) {
    AsyncImage(
        model = model,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        error = painterResource(R.drawable.baseline_broken_image_24)


    )
}