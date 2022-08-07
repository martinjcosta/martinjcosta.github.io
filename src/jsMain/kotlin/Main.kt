import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.keywords.CSSAutoKeyword
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import kotlin.js.Date


fun main() {
    renderComposable(rootElementId = "root") {
        var date by remember { mutableStateOf(Date()) }

        val rows = 6
        val columns = 7

        val monthName = date.getMonth().let {
            when (it) {
                0 -> "January"
                1 -> "February"
                2 -> "March"
                3 -> "April"
                4 -> "May"
                5 -> "June"
                6 -> "July"
                7 -> "August"
                8 -> "September"
                9 -> "October"
                10 -> "November"
                11 -> "December"
                else -> "Error"
            }
        }

        val yearName = date.getFullYear()

        Div(
            {
                style {
                    width(100.percent)
                    marginBottom(100.px)
                    textAlign("center")
                    fontSize(36.pt)
                    fontStyle("bold")
                    fontFamily("Helvetica")
                }
            }
        ) {
            Div({
                style {
                    display(DisplayStyle.Inline)
                    //width(33.percent)
                    marginRight(200.px)
                }
            }) {
                var color by remember {
                    mutableStateOf(rgba(0, 0, 0, 255))
                }

                Button(attrs = {
                    style {
                        fontSize(48.pt)
                        color(color)
                        backgroundColor(rgba(255, 255, 255, 0))
                        border(0.px)
                    }
                    onMouseEnter {
                        color = rgba(100, 100, 100, 255)
                    }
                    onMouseLeave {
                        color = rgba(0, 0, 0, 255)
                    }
                    onClick {
                        date = goToPreviousMonth(date)
                    }
                }) {
                    Text("<")
                }
            }

            Div({
                style {
                    display(DisplayStyle.InlineBlock)
                    width(1000.px)
                }
            }) {
                Text("$monthName $yearName")
            }

            Div({
                style {
                    display(DisplayStyle.Inline)
                    //width(33.percent)
                    marginLeft(200.px)
                }
            }) {
                var color by remember {
                    mutableStateOf(rgba(0, 0, 0, 255))
                }

                Button(attrs = {
                    style {
                        fontSize(48.pt)
                        color(color)
                        backgroundColor(rgba(255, 255, 255, 0))
                        border(0.px)
                    }
                    onMouseEnter {
                        color = rgba(100, 100, 100, 255)
                    }
                    onMouseLeave {
                        color = rgba(0, 0, 0, 255)
                    }
                    onClick {
                        date = goToNextMonth(date)
                    }
                }) {
                    Text(">")
                }
            }
        }

        // Calendar
        Div({
            style {
                display(DisplayStyle.Grid)
                gridTemplateColumns(auto * columns)
            }
        }) {
            var day = 1
            val firstOfMonth = Date(year = date.getFullYear(), month = date.getMonth(), day = 1)
            println("$date")
            println("$firstOfMonth ${firstOfMonth.getDate()} ${firstOfMonth.getDay()}")

            val dayOfFirst = firstOfMonth.getDay() // Day of the week 0-6
            day -= dayOfFirst

            val thirtyDays = setOf(3, 5, 8, 10)
            val maxDayForMonth = if (thirtyDays.contains(date.getMonth())) {
                30
            } else if (date.getMonth() == 1) {
                if (date.getFullYear() % 4 == 0 && (date.getFullYear() % 100 != 0 || date.getFullYear() % 400 == 0)) {
                    29
                } else {
                    28
                }
            } else {
                31
            }

            for (r in 0 until rows) {
                for (c in 0 until columns) {
                    // Day
                    Div({
                        style {
                            width(200.px)
                            height(72.px)
                            fontFamily("Helvetica")
                            padding(24.px)
                            marginBottom(6.px)
                            borderRadius(24.px)
                            if (day in 1..maxDayForMonth) background("lightblue")
                        }
                    }) {
                        if (day in 1..maxDayForMonth) Text("$day")
                        day++
                    }
                }
            }
        }
    }
}

private fun goToPreviousMonth(date: Date): Date {
    return if (date.getMonth().previous().second) {
        Date(date.getFullYear() - 1, date.getMonth().previous().first, 1)
    } else {
        Date(date.getFullYear(), date.getMonth().previous().first, 1)
    }
}

private fun goToNextMonth(date: Date): Date {
    return if (date.getMonth().next().second) {
        Date(date.getFullYear() + 1, date.getMonth().next().first, 1)
    } else {
        Date(date.getFullYear(), date.getMonth().next().first, 1)
    }
}

private fun Int.previous(): Pair<Int, Boolean> {
    return if (this == 0) Pair(11, true) else Pair(this - 1, false)
}

private fun Int.next(): Pair<Int, Boolean> {
    return if (this == 11) Pair(0, true) else Pair(this + 1, false)
}

private operator fun CSSAutoKeyword.times(n: Int): String {
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

