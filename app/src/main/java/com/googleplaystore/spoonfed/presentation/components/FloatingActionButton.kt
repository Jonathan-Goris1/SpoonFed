package com.googleplaystore.spoonfed.presentation.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun ScrollToTopButton(goToTop: () -> Unit){
    FloatingActionButton(
        shape = CircleShape,
        onClick = { goToTop() }
    ) {
        Icon(imageVector = Icons.Filled.KeyboardArrowUp , contentDescription = "Back to top")
    }
}

