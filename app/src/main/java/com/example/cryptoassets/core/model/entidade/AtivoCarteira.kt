package com.example.cryptoassets.core.model.entidade

import org.javamoney.moneta.Money
import java.math.BigDecimal

data class AtivoCarteira(val id:Int?, val ativo: Ativo, val quantidade: BigDecimal, val precoMedio: Money) {

    constructor(ativo: Ativo, quantidade: BigDecimal, precoMedio: Money) : this(null, ativo, quantidade, precoMedio)

    fun calcularTotalPago(): Money {
        return precoMedio.multiply(quantidade.toDouble())
    }

}