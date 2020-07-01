package com.example.cryptoassets.core.util

import org.javamoney.moneta.Money
import org.javamoney.moneta.format.CurrencyStyle
import java.math.BigDecimal
import java.util.*
import javax.money.CurrencyUnit
import javax.money.Monetary
import javax.money.format.AmountFormatQueryBuilder
import javax.money.format.MonetaryAmountFormat
import javax.money.format.MonetaryFormats


class MoneyUtils {

    companion object {

        fun zero(): Money {

            return Money.zero(getCurrency())
        }

        fun getCurrency(): CurrencyUnit {
            return getCurrency("USD")
        }

        fun getCurrency(currencyCode:String): CurrencyUnit {
            return Monetary.getCurrency(currencyCode)
        }

        fun getFormat(): MonetaryAmountFormat{
            return MonetaryFormats.getAmountFormat(Locale.getDefault())
        }

        fun getFormatWithoutSymbol(): MonetaryAmountFormat{

            return MonetaryFormats.getAmountFormat(
                AmountFormatQueryBuilder.
                of(Locale.getDefault())

                    .build()


            )
        }

        fun of(amount:String, currency:CurrencyUnit): Money {
            return of(BigDecimalUtils.ofString(amount), currency)
        }

        fun of(amount:String): Money {
            return of(amount, getCurrency())
        }

        fun of(amount:Double, currency:CurrencyUnit): Money {
            return Money.of(amount, currency)
        }

        fun of(amount:Double): Money {
            return Money.of(amount, getCurrency())
        }

        fun of(amount:BigDecimal, currency:CurrencyUnit): Money {
            return Money.of(amount, currency)
        }

        fun of(amount:BigDecimal): Money {
            return Money.of(amount, getCurrency())
        }


    }
}