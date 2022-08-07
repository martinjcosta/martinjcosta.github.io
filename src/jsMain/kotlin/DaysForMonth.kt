import kotlin.js.Date

private val thirtyDays = setOf(3, 5, 8, 10)

fun lastDayOfMonth(date: Date) = if (thirtyDays.contains(date.getMonth())) {
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