package com.example.cryptoassets.core.model.calculador

import com.example.cryptoassets.core.model.entidade.*
import com.example.cryptoassets.util.BigDecimalUtils
import com.example.cryptoassets.util.MoneyUtils
import org.javamoney.moneta.Money
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class CalculadorVariacaoCotacaoTest {

    val cotacao = Cotacao(Ticker.BTC,  MoneyUtils.of("50000"))

    @Test
    fun deveCalcularVariacaoPrecoMedio(){

        val valores = mutableListOf(
            Triple(BigDecimalUtils.ofString("0.5"), MoneyUtils.of("50050"), MoneyUtils.of("-50")),
            Triple(BigDecimalUtils.ofString("0.5"), MoneyUtils.of("50000"), MoneyUtils.of("0")),
            Triple(BigDecimalUtils.ofString("0.5"), MoneyUtils.of("49000"), MoneyUtils.of("1000"))

        )
        for (valor in valores) {
            val (quantidade: BigDecimal, precoMedio: Money, valorEsperado) = valor
            val ativoCarteira =
                AtivoCarteira(
                    Ativo(Ticker.BTC, Ticker.BTC.name),
                    quantidade,
                    precoMedio
                )
            val calculo = CalculadorVariacaoCotacao().calcularVariacaoPrecoMedio(ativoCarteira, cotacao)
            assertEquals(valorEsperado, calculo)
        }

    }

    @Test
    fun deveCalcularVariacaoValorTotalPago(){

        val valores = mutableListOf(
            Triple(BigDecimalUtils.ofString("0.5"), MoneyUtils.of("50050"), MoneyUtils.of("-25")),
            Triple(BigDecimalUtils.ofString("0.5"), MoneyUtils.of("50000"), MoneyUtils.of("0")),
            Triple(BigDecimalUtils.ofString("0.5"), MoneyUtils.of("49000"), MoneyUtils.of("500")),
            Triple(BigDecimalUtils.ofString("1"), MoneyUtils.of("49000"), MoneyUtils.of("1000"))

        )
        for (valor in valores) {
            val (quantidade: BigDecimal, precoMedio: Money, valorEsperado) = valor
            val ativoCarteira =
                AtivoCarteira(
                    Ativo(Ticker.BTC, Ticker.BTC.name),
                    quantidade,
                    precoMedio
                )
            val calculo = CalculadorVariacaoCotacao().calcularVariacaoValorTotalPago(ativoCarteira, cotacao)
            assertEquals(valorEsperado, calculo)
        }
    }

    @Test
    fun deveCalcularVariacaoPorcentagem(){

        val valores = mutableListOf(
            Pair(MoneyUtils.of("50050"), BigDecimalUtils.ofString("-0.09990009")),
            Pair(MoneyUtils.of("50000"), BigDecimalUtils.ofString("0")),
            Pair(MoneyUtils.of("49000"), BigDecimalUtils.ofString("2.04081632"))

        )
        for (valor in valores) {
            val (precoMedio: Money, valorEsperado:BigDecimal) = valor
            val ativoCarteira =
                AtivoCarteira(
                    Ativo(Ticker.BTC, Ticker.BTC.name),
                    BigDecimal.ONE,
                    precoMedio
                )
            val calculo = CalculadorVariacaoCotacao().calcularVariacaoPorcentagem(ativoCarteira, cotacao)
            assertEquals(valorEsperado, calculo)
        }
    }
}