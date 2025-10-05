package domain.filter

import utils.Tools.unaccent

enum class Filter(val label: String, val enabled: Boolean = false, val f: (String) -> String = { it }) {
    CAPITAL("Case Sensitive") {
        override fun apply(str: String): String = str.lowercase()
    },
    SIGNAL("Ignore Accents") {
        override fun apply(str: String): String = str.unaccent()
    },
    ALPHABET("Only Alphabetic") {
        override fun apply(str: String): String = if (str.all { it.isLetter() }) str else ""
    };

    abstract fun apply(str: String): String
}