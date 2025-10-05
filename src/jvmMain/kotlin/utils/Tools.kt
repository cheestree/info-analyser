package utils

import domain.filter.Filter
import java.io.File
import java.text.Normalizer
import java.util.regex.Pattern

object Tools {
    fun CharSequence.unaccent(): String {
        val nfdNormalizedString = Normalizer.normalize(this, Normalizer.Form.NFD)
        val pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
        val output = pattern.matcher(nfdNormalizedString).replaceAll("")
        return output
    }

    fun File.toHistogram(
        options: List<Filter>
    ): HashMap<String, Int> {
        val hash = hashMapOf<String, Int>()
        this.forEachLine(charset = Charsets.ISO_8859_1) { line ->
            line.forEach { char ->
                var value = char.toString()
                options.forEach { filter -> value = filter.apply(value) }
                hash[value] = hash.getOrDefault(value, 0) + 1
            }
        }
        return hash
    }
}