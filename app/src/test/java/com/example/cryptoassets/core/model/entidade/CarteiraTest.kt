package com.example.cryptoassets.core.model.entidade

import com.example.cryptoassets.core.model.entidade.Ativo
import com.example.cryptoassets.core.model.entidade.AtivoCarteira
import com.example.cryptoassets.core.model.entidade.Carteira
import com.example.cryptoassets.core.model.entidade.Ticker
import com.example.cryptoassets.util.BigDecimalUtils
import com.example.cryptoassets.util.MoneyUtils
import org.javamoney.moneta.Money
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class CarteiraTest {

    @Test
    fun deveCalcularTotalPago() {
        val valores = mutableListOf(
            Triple(
                BigDecimalUtils.ofString("0.010"), MoneyUtils.of("49497.87523000"),
                Ativo(
                    Ticker.BTC,
                    Ticker.BTC.name
                )
            ),
            Triple(
                BigDecimalUtils.ofString("3"), MoneyUtils.of("223.50000000"),
                Ativo(
                    Ticker.LTC,
                    Ticker.LTC.name
                )
            ),
            Triple(
                BigDecimalUtils.ofString("0"), MoneyUtils.of("0"),
                Ativo(
                    Ticker.BCH,
                    Ticker.BCH.name
                )
            ),
            Triple(
                BigDecimalUtils.ofString("0.8"), MoneyUtils.of("1232.00416000"),
                Ativo(
                    Ticker.ETH,
                    Ticker.ETH.name
                )
            ),
            Triple(
                BigDecimalUtils.ofString("134"), MoneyUtils.of("5.43000000"),
                Ativo(
                    Ticker.USDC,
                    Ticker.USDC.name
                )
            ),
            Triple(
                BigDecimalUtils.ofString("4865.16"), MoneyUtils.of("0.95000000"),
                Ativo(
                    Ticker.XRP,
                    Ticker.XRP.name
                )
            )
        )

        val ativos = arrayListOf<AtivoCarteira>()
        for (valor in valores) {
            val (quantidade: BigDecimal, precoMedio: Money, ativo: Ativo) = valor
            val ativoCarteira =
                AtivoCarteira(
                    ativo,
                    quantidade,
                    precoMedio
                )
            ativos.add(ativoCarteira)
        }
        val carteira =  Carteira(ativos)
        val valorEsperado = MoneyUtils.of("7500.6040803")
        assertEquals(valorEsperado, carteira.calcularValorTotalPago())

    }

    @Test
    fun deveCalcularTotalAtivosZerado(){
        val ativos = Ticker.values().map { ticker ->
            AtivoCarteira(
                Ativo(ticker, ticker.name),
                BigDecimalUtils.zero(),
                MoneyUtils.zero()
            )
        }.toMutableList()
        val carteira =  Carteira(ativos)
        val valorEsperado = MoneyUtils.zero()
        assertEquals(valorEsperado, carteira.calcularValorTotalPago())
    }

}