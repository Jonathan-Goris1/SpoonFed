package com.googleplaystore.spoonfed.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun HeaderRow(
    text1: String = "",
    text2: String = "",
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {

        HeaderText(
            textAlign = TextAlign.Start,
            text = text1,
            maxLines = 1
        )
        Spacer(Modifier.weight(1f))

        if (text2.isNotBlank()) {
            HeaderText(
                textAlign = TextAlign.End,
                text = text2,
                maxLines = 1
            )
        }


    }
}