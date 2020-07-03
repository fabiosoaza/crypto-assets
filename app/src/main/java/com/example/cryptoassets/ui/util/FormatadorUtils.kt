package com.example.cryptoassets.ui.util

import com.example.cryptoassets.util.MoneyUtils
import org.javamoney.moneta.Money
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.Temporal
import java.util.*

class FormatadorUtils {
    companion object {
        fun formatarValor(valor: BigDecimal,  pattern : String ?= "#.##"): String {
            val decimalFormatSymbols: DecimalFormatSymbols = DecimalFormatSymbols.getInstance(Locale.getDefault())
            return DecimalFormat(pattern, decimalFormatSymbols).format(valor)
        }

        fun formatarValor(valor: Money) : String {
                return MoneyUtils.getFormat().format(valor)
        }

        fun formatarData(instant: Temporal) : String {
            return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(instant)
        }
    }

}