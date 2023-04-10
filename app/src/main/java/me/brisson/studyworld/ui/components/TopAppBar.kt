package me.brisson.studyworld.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import me.brisson.studyworld.ui.theme.StudyWorldTheme

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    onTrailingIcon: () -> Unit,
    trailingIcon: @Composable () -> Unit = {
        IconButton(onClick = onTrailingIcon) {
            Icon(imageVector = Icons.Default.Person, contentDescription = null)
        }
    },
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor),

        ) {

    }
}

@Preview
@Composable
private fun PreviewTopAppBar() {
    StudyWorldTheme {

    }
}