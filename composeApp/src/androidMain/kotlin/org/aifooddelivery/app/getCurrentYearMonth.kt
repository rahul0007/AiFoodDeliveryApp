package org.aifooddelivery.app

import android.icu.util.Calendar

actual fun getCurrentYearMonth(): Pair<Int, Int>? {
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.YEAR) to (calendar.get(Calendar.MONTH) + 1)
}