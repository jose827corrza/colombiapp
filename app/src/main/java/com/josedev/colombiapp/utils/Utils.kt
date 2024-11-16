package com.josedev.colombiapp.utils

import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import java.util.Locale

object Utils {
    fun formatter(n: Long): String {
        return DecimalFormat("#,###", DecimalFormatSymbols(Locale.UK)).format(n)
    }
}