package model.theory

import model.vals.currFile
import java.io.File
import java.math.RoundingMode
import kotlin.math.log2

fun convertToHistogram(file: File) : HashMap<String, Int>{
    currFile = file.name
    val hash = hashMapOf<String, Int>()
    file.forEachLine { line ->
        val lineArr = line.split("")
        lineArr.forEach {
            if(it != "") {
                hash.putIfAbsent(it, 0)
                hash[it] = hash[it]!! + 1
            }
        }
    }
    return hash
}

fun fmp(hash : HashMap<String, Int>) : HashMap<String, Float>{
    val newHash = hashMapOf<String, Float>()
    val population = hash.values.sum().toFloat()
    hash.forEach {
        newHash.putIfAbsent(it.key, 0f)
        newHash[it.key] = ((it.value / population) * 100f).toBigDecimal().setScale(4, RoundingMode.UP).toFloat()
    }
    return newHash
}

fun selfinfo(hash: HashMap<String, Float>) : HashMap<String, Float>{
    val newHash = hashMapOf<String, Float>()
    hash.forEach {
        newHash.putIfAbsent(it.key, 0f)
        newHash[it.key] = -log2(it.value/100f).toBigDecimal().setScale(4, RoundingMode.HALF_UP).toFloat()
    }
    return newHash
}

fun entropy(fmp: HashMap<String, Float>, selfinfoMap: HashMap<String, Float>) : Float {
    var entropy = 0f
    fmp.forEach{
        entropy += selfinfoMap[it.key]!! * it.value
    }
    entropy = (entropy/100).toBigDecimal().setScale(4, RoundingMode.HALF_UP).toFloat()
    return entropy
}