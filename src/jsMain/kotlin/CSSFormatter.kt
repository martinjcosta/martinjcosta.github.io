import org.jetbrains.compose.web.css.keywords.CSSAutoKeyword

operator fun CSSAutoKeyword.times(n: Int): String {
    return this.toString() * n
}

private operator fun String.times(n: Int): String {
    var final = ""
    val withSpace = "$this "
    for (i in 0 until n) {
        final += withSpace
    }
    return final.removeSuffix(" ")
}

