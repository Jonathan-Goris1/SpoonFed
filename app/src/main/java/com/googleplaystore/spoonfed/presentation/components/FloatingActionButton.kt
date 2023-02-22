package com.googleplaystore.spoonfed.presentation.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.googleplaystore.spoonfed.R

@Composable
fun ScrollToTopButton(goToTop: () -> Unit) {
    FloatingActionButton(
        shape = CircleShape,
        containerColor = Color.Black,
        contentColor = Color.Red,
        onClick = { goToTop() }
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = stringResource(id = R.string.FAB_ContentDescription)
        )
    }
}

