package ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.filter.Filter

@Composable
fun Options(
    path: String,
    filters: List<Filter>,
    onFilterChange: (Filter) -> Unit,
    onPathChange: (String) -> Unit,
    onPickFile: () -> Unit,
) {
    Row {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            TextField(
                value = path,
                onValueChange = { onPathChange(it) },
                modifier = Modifier.width(400.dp),
                maxLines = 1
            )
            Button(onClick = { onPickFile() }) { Text("Find...") }
        }
        filters.map { filter ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = filter.enabled,
                    onCheckedChange = { onFilterChange(filter) }
                )
                Text(filter.label)
            }
        }
    }
}