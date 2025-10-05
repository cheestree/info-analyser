package ui.screen

import co.touchlab.kermit.Logger
import domain.filter.Filter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import utils.Tools.toHistogram
import javax.swing.JFileChooser
import kotlin.math.log2

class MainScreenViewModel {
    private val logger = Logger.withTag("MainScreenViewModel")

    private val _path = MutableStateFlow("")
    val path: StateFlow<String> = _path

    private val _currentFileName = MutableStateFlow("")
    val currentFileName: StateFlow<String> = _currentFileName

    private val _entropy = MutableStateFlow(0.0)
    val entropy: StateFlow<Double> = _entropy

    private val _charMap = MutableStateFlow<HashMap<String, Int>>(hashMapOf())
    val charMap: StateFlow<HashMap<String, Int>> = _charMap

    private val _filters = MutableStateFlow(
        listOf(
            Filter.CAPITAL,
            Filter.SIGNAL,
            Filter.ALPHABET
        )
    )
    val filters: StateFlow<List<Filter>> = _filters

    fun onFilterChange(filter: Filter) {
        _filters.value = _filters.value.map {
            if (it.name == filter.name) it.enabled != filter.enabled
            it
        }
        logger.i { "filter ${filter.name} changed to ${filter.enabled}" }
    }

    fun onPathChange(newPath: String) {
        _path.value = newPath
        logger.i { "path changed to $newPath" }
    }

    fun onPickFile() {
        try {
            val chooser = JFileChooser()
            chooser.fileSelectionMode = JFileChooser.FILES_ONLY
            val result = chooser.showOpenDialog(null)
            if (result == JFileChooser.APPROVE_OPTION) {
                val selectedFile = chooser.selectedFile
                _path.value = selectedFile.absolutePath
                _currentFileName.value = selectedFile.name
                val histogram = selectedFile.toHistogram(_filters.value)
                _charMap.value = histogram
                _entropy.value = histogram.values.fold(0.0) { acc, freq ->
                    val p = freq.toDouble() / histogram.values.sum()
                    acc - p * log2(p)
                }
                logger.i { "File picked: ${selectedFile.absolutePath}" }
            }
        } catch (e: Exception) {
            logger.e(e) { "Error picking file" }
        }
    }
}
