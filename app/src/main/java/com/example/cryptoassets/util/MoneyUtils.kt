package com.example.cryptoassets.util

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
            return getCurrency(
                "BRL"
            )
        }

        fun getCurrency(currencyCode:String): CurrencyUnit {
            return Monetary.getCurrency(currencyCode)
        }

        fun getFormat(): MonetaryAmountFormat{
            return MonetaryFormats.getAmountFormat(Locale("pt", "BR"))
        }

        fun getFormatWithSymbol(): MonetaryAmountFormat{
            return MonetaryFormats.getAmountFormat(
                AmountFormatQueryBuilder.
                of(Locale("pt", "BR"))
                    .set(CurrencyStyle.SYMBOL)
                    .build()
            )
        }

        fun of(amount:String, currency:CurrencyUnit): Money {
            return of(
                BigDecimalUtils.ofString(
                    amount
                ),
                currency
            )
        }

        fun of(amount:String): Money {
            return of(
                amount,
                getCurrency()
            )
        }

        fun of(amount:Double, currency:CurrencyUnit): Money {
            return Money.of(amount, currency)
        }

        fun of(amount:Double): Money {
            return of(amount, getCurrency()
            )
        }

        fun of(amount:BigDecimal, currency:CurrencyUnit): Money {
            val amount = Money.of(BigDecimalUtils.ofBigDecimal(amount), currency)
            //val mathContext = MathContext(BigDecimalUtils.PRECISAO, RoundingMode.HALF_EVEN)
            //val monetaryOperator = PrecisionScaleRoundedOperator.of(BigDecimalUtils.PRECISAO, mathContext)
            //val roundedValue = monetaryOperator.apply(amount)
            return amount
        }

        fun of(amount:BigDecimal): Money {
            return of(amount, getCurrency())
        }


    }
}