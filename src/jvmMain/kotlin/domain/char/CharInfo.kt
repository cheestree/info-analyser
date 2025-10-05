package domain.char

import java.math.RoundingMode
import kotlin.math.log2

data class CharInfo(val char: String, val occ: Int, val pop: Int) {
    val fmp = (occ.toFloat() / pop).toBigDecimal().setScale(4, RoundingMode.UP).toFloat()
    val selfInfo = -log2(occ.toFloat() / pop).toBigDecimal().setScale(4, RoundingMode.UP).toFloat()

    override fun toString(): String {
        return "$char occurs $occ times, with $fmp probability and $selfInfo self info."
    }

    companion object {
        fun hashToList(hash: HashMap<String, Int>): List<CharInfo> {
            val newList = mutableListOf<CharInfo>()
            val size = hash.values.sum()
            hash.forEach { (t, u) -> newList.add(CharInfo(t, u, size)) }
            return newList
        }
    }
}