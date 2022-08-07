import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import kotlin.js.Date

private const val ROWS = 6
private const val COLUMNS = 7

fun main() {
    renderComposable(rootElementId = "root") {
        var date by remember { mutableStateOf(Date()) }

        val monthName = date.getMonth().mapToMonthName()
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
                gridTemplateColumns(auto * COLUMNS)
            }
        }) {
            var day = 1
            val firstOfMonth = Date(year = date.getFullYear(), month = date.getMonth(), day = 1)
            val dayOfFirst = firstOfMonth.getDay() // Day of the week 0-6
            day -= dayOfFirst

            val maxDayForMonth = lastDayOfMonth(date)

            for (r in 0 until ROWS) {
                for (c in 0 until COLUMNS) {
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