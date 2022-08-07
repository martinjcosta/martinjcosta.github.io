import kotlin.js.Date

fun goToPreviousMonth(date: Date): Date {
    return if (date.getMonth().previous().second) {
        Date(date.getFullYear() - 1, date.getMonth().previous().first, 1)
    } else {
        Date(date.getFullYear(), date.getMonth().previous().first, 1)
    }
}

fun goToNextMonth(date: Date): Date {
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
