import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import model.vals.isFileWindow
import ui.file.fileApp
import ui.main.mainApp

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Information Analysis"
    ) {
        mainApp()
    }
    if(isFileWindow){
        Window(
            onCloseRequest = { isFileWindow = !isFileWindow},
            state = WindowState(size = DpSize.Unspecified),
            title = "Choose a file..."
        ){
            fileApp()
        }
    }
}
