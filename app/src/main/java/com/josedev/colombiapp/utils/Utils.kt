package com.josedev.colombiapp.utils

import android.content.Context
import android.content.Intent
import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import java.util.Locale

object Utils {
    fun formatter(n: Long): String {
        return DecimalFormat("#,###", DecimalFormatSymbols(Locale.UK)).format(n)
    }

    fun linkToWebPage(context: Context, url: String){
        val openUrl = Intent(Intent.ACTION_VIEW)
        openUrl.data = Uri.parse(url)
        startActivity(context, openUrl, null)
    }
}