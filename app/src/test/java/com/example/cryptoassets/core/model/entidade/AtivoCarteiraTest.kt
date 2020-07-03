package com.example.cryptoassets.core.model.entidade

import com.example.cryptoassets.core.model.entidade.Ativo
import com.example.cryptoassets.core.model.entidade.AtivoCarteira
import com.example.cryptoassets.core.model.entidade.Ticker
import com.example.cryptoassets.util.BigDecimalUtils
import com.example.cryptoassets.util.MoneyUtils
import org.javamoney.moneta.Money
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class AtivoCarteiraTest {

    private val ativo = Ativo(
        Ticker.BTC,
        Ticker.BTC.name
    )

    @Test
    fun deveCalcularTotalPago() {
        val valores = mutableListOf(
            Triple(BigDecimalUtils.ofString("0.5"), MoneyUtils.of("50000"), MoneyUtils.of("25000")),
            Triple(BigDecimalUtils.ofString("0"), MoneyUtils.of("50000"), MoneyUtils.of("0")),
            Triple(BigDecimalUtils.ofString("0.5"), MoneyUtils.of("0"), MoneyUtils.of("0")),
            Triple(BigDecimalUtils.ofString("0"), MoneyUtils.of("0"), MoneyUtils.of("0"))
        )

        for (valor in valores) {
            val (quantidade: BigDecimal, precoMedio: Money, valorEsperado: Money) = valor
            val ativoCarteira =
                AtivoCarteira(
                    ativo,
                    quantidade,
                    precoMedio
                )
            assertEquals(valorEsperado, ativoCarteira.calcularTotalPago())
        }

    }


}