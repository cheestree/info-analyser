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
var isCapitalSame by mutableStateOf(false)
var isSpaceCounted by mutableStateOf(false)
var isSignalSame by mutableStateOf(false)
var isAlphabet by mutableStateOf(false)

// Canvas
var canvasWidth by mutableStateOf(0f)
var canvasHeight by mutableStateOf(0f)