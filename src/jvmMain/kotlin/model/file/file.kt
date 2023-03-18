package model.file

import model.vals.fileList
import model.vals.isFileWindow
import java.io.File

fun checkFiles(pathToFile : String){
    if(pathToFile.isEmpty()) return
    val files = File(pathToFile)
    fileList.clear()
    files.listFiles { file ->
        file.extension == "txt"
    }?.forEach {
        fileList.add(it)
    }
    isFileWindow = !isFileWindow
}