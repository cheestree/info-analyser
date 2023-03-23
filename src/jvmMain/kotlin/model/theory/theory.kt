package model.theory

import model.vals.*
import java.io.File
import java.math.RoundingMode
import java.text.Normalizer
import java.util.regex.Pattern
import kotlin.math.log2

fun CharSequence.unaccent(): String {
    val nfdNormalizedString = Normalizer.normalize(this, Normalizer.Form.NFD)
    val pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
    val output = pattern.matcher(nfdNormalizedString).replaceAll("")
    return output
}

fun convertToHistogram(file: File) : HashMap<String, Int>{
    currFile = file.name
    val hash = hashMapOf<String, Int>()
    file.forEachLine { line ->
        val lineArr = line.split("")
        lineArr.forEach {
            if(it != "") {
                var lowerOrNot = it
                if(isCapitalSame && isSignalSame) lowerOrNot = it.lowercase().unaccent()
                if(isSignalSame && !isCapitalSame) lowerOrNot = it.unaccent()
                if(!isSignalSame && isCapitalSame) lowerOrNot = it.lowercase()
                if(it != " " || !isSpaceCounted){
                    if(isAlphabet && it.all { it.isLetter() } || !isAlphabet){
                        hash.putIfAbsent(lowerOrNot, 0)
                        hash[lowerOrNot] = hash[lowerOrNot]!! + 1
                    }
                }
            }
        }
    }
    return hash
}

fun charinfomaker(hash : HashMap<String, Int>) : List<CharInfo>{
    var newList = mutableListOf<CharInfo>()
    var size = hash.values.sum()
    hash.forEach { (t, u) ->
        newList.add(CharInfo(t, u, size))
    }
    return newList
}

data class CharInfo(val char : String, val occ : Int, val pop : Int){
    var fmp = ((occ.toFloat() / pop)*100).toBigDecimal().setScale(4, RoundingMode.UP).toFloat()
    var selfinfo = -log2(occ.toFloat()/pop).toBigDecimal().setScale(4, RoundingMode.UP).toFloat()

    override fun toString(): String {
        return "$char occurs $occ times, with $fmp probability and $selfinfo self info."
    }
}