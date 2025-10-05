import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import domain.char.CharInfo
import ui.screen.FileScreen
import ui.screen.MainScreen
import ui.screen.MainScreenViewModel

fun main() = application {
    val viewModel = remember { MainScreenViewModel() }

    val path by viewModel.path.collectAsState()
    val filters by viewModel.filters.collectAsState()
    val charList by viewModel.charMap.collectAsState()
    val currentFileName by viewModel.currentFileName.collectAsState()
    val entropy by viewModel.entropy.collectAsState()

    MaterialTheme {
        Window(
            onCloseRequest = ::exitApplication,
            state = WindowState(size = DpSize(1024.dp, 768.dp)),
            title = "Information Analysis"
        ) {
            MainScreen(
                path = path,
                charList = CharInfo.hashToList(charList).sortedByDescending { it.occ },
                entropy = entropy,
                currentFileName = currentFileName,
                filters = filters,
                onPathChange = { viewModel.onPathChange(it) },
                onFilterChange = { viewModel.onFilterChange(it) },
                onPickFile = { viewModel.onPickFile() },
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
