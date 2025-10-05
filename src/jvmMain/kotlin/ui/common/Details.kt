package ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Details(
    information: String?,
    entropy: Double,
    currentFileName: String?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        if (currentFileName != null) {
            Text("File: $currentFileName")
        }
        Text("Entropy: %.4f".format(entropy))
        Text(information ?: "")
    }
}