package ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import domain.char.CharInfo
import domain.filter.Filter
import ui.common.CharList
import ui.common.Details
import ui.common.Histogram
import ui.common.Options

@Composable
fun MainScreen(
    path: String,
    charList: List<CharInfo>,
    entropy: Double,
    filters: List<Filter>,
    currentFileName: String?,
    onFilterChange: (Filter) -> Unit,
    onPathChange: (String) -> Unit,
    onPickFile: () -> Unit,
    modifier: Modifier = Modifier
) {
    var information by remember { mutableStateOf("") }
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
        ) {
            Options(
                path = path,
                filters = filters,
                onFilterChange = { onFilterChange(it) },
                onPathChange = { onPathChange(it) },
                onPickFile = {  onPickFile() }
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Row(modifier = Modifier.weight(1f)) {
                Details(
                    information = information,
                    entropy = entropy,
                    currentFileName = currentFileName,
                    modifier = Modifier.weight(1f)
                )
                CharList(
                    charList = charList,
                    modifier = Modifier.weight(1f)
                )
            }
            Histogram(
                charList = charList,
                onStringChange = { information = it },
                modifier = Modifier.fillMaxWidth().weight(1f)
            )
        }
    }
}