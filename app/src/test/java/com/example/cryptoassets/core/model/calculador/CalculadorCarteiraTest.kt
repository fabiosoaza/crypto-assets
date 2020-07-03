package com.example.cryptoassets.core.model.calculador

import com.example.cryptoassets.core.model.entidade.*
import com.example.cryptoassets.util.BigDecimalUtils
import com.example.cryptoassets.util.MoneyUtils
import org.javamoney.moneta.Money
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class CalculadorCarteiraTest {

    private val cotacoes = mutableListOf<Cotacao>(
        Cotacao(Ticker.BTC, MoneyUtils.of("50050.00000000")),
        Cotacao(Ticker.LTC, MoneyUtils.of("223.00000000")),
        Cotacao(Ticker.ETH, MoneyUtils.of("1225.00000000"))

    )

    @Test
    fun deveCalcularVariacaoValor() {

        val elements = arrayOf(
            AtivoCarteira(
                Ativo(Ticker.BTC, Ticker.BTC.name),
                BigDecimalUtils.ofString("0.5"),
                MoneyUtils.of("50000")
            ),
            AtivoCarteira(
                Ativo(Ticker.LTC, Ticker.LTC.name),
                BigDecimalUtils.ofString("0.5"),
                MoneyUtils.of("226.00000000")
            ),
            AtivoCarteira(
                Ativo(Ticker.ETH, Ticker.ETH.name),
                BigDecimalUtils.ofString("0.5"),
                MoneyUtils.of("1220.00000000")
            )
        )
        val ativosCarteira = mutableListOf<AtivoCarteira>()
        ativosCarteira.addAll(elements)

        val carteira = Carteira(ativosCarteira)
        val calculo = CalculadorCarteira().calcularVariacaoValor(carteira, cotacoes)

        //https://www.wolframalpha.com/input/?i=%5B%2850050+-+50000%29*0.5%5D+%2B+%5B%28223+-+226%29*0.5%5D%2B%5B%281225+-+1220%29*0.5%5D+
        val valorEsperado = MoneyUtils.of("26")
        assertEquals(valorEsperado, calculo)
    }

    @Test
    fun deveCalcularVariacaoPorcentagem() {

        val elements = arrayOf(
            AtivoCarteira(
                Ativo(Ticker.BTC, Ticker.BTC.name),
                BigDecimalUtils.ofString("0.5"),
                MoneyUtils.of("50000")
            ),
            AtivoCarteira(
                Ativo(Ticker.LTC, Ticker.LTC.name),
                BigDecimalUtils.ofString("0.5"),
                MoneyUtils.of("226.00000000")
            ),
            AtivoCarteira(
                Ativo(Ticker.ETH, Ticker.ETH.name),
                BigDecimalUtils.ofString("0.5"),
                MoneyUtils.of("1220.00000000")
            )
        )
        val ativosCarteira = mutableListOf<AtivoCarteira>()
        ativosCarteira.addAll(elements)

        val carteira = Carteira(ativosCarteira)
        val calculo = CalculadorCarteira().calcularVariacaoPorcentagem(carteira, cotacoes)

        //https://www.wolframalpha.com/input/?i=N%5B%2826.00000000+is+what+percentage+of++25723.00000000%29%2C+9%5D
        val valorEsperado = BigDecimalUtils.ofString("0.10107685")
        assertEquals(valorEsperado, calculo)
    }

}