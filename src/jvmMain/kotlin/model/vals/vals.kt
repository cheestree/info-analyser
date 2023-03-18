package model.vals

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.io.File

// Windows


// File window
var isFileWindow by mutableStateOf(false)
var fileList = mutableListOf<File>()
var currFile : String? by mutableStateOf(null)

// Histogram
var charMap = hashMapOf<String, Int>()
var fmpMap = hashMapOf<String, Float>()
var selfinfoMap = hashMapOf<String, Float>()
var histogramWidth by mutableStateOf(0)
var histogramHeight by mutableStateOf(0)

// Canvas
var canvasWidth by mutableStateOf(0f)
var canvasHeight by mutableStateOf(0f)