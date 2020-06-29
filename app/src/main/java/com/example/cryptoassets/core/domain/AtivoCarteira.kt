package com.example.cryptoassets.core.domain

import com.example.cryptoassets.core.util.BigDecimalUtils
import org.javamoney.moneta.Money
import java.math.BigDecimal
import javax.money.MonetaryAmount

data class AtivoCarteira(val ativo: Ativo, val quantidade: BigDecimal, val precoMedio: Money) {

    fun calcularTotalPago(): Money {
        return precoMedio.multiply(quantidade.toDouble())
    }

}