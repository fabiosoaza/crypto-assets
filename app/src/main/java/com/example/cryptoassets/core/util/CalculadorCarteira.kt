package com.example.cryptoassets.core.util

import com.example.cryptoassets.core.domain.Carteira
import com.example.cryptoassets.core.domain.Cotacao
import com.example.cryptoassets.core.domain.Ticker
import org.javamoney.moneta.Money
import java.math.BigDecimal

class CalculadorCarteira {

    fun calcularVariacaoValor(carteira: Carteira, cotacoes: List<Cotacao>) : Money {
        if (carteira.investimentos.isEmpty()){
            return MoneyUtils.zero()
        }
        val calculadorVariacaoCotacao =
            CalculadorVariacaoCotacao()
        return carteira.investimentos.map { ativoCarteira ->
            calculadorVariacaoCotacao.calcularVariacaoValorTotalPago(
                ativoCarteira,
                cotacao(cotacoes, ativoCarteira.ativo.ticker)
            )
        }.groupingBy { 0 }.aggregate { _, accumulator: Money?, element: Money?, first ->
            if (first) element else accumulator?.add(element)
        }[0] ?: MoneyUtils.zero()

    }

    fun calcularVariacaoPorcentagem(carteira: Carteira, cotacoes: List<Cotacao>): BigDecimal {
        return if (carteira.calcularValorTotalPago().isZero)
            BigDecimal.ZERO
        else {
            val ofString = BigDecimalUtils.ofString(calcularVariacaoValor(carteira, cotacoes).number.toString())
            val ofInt = BigDecimalUtils.ofInt(100)
            val multiply = BigDecimalUtils.ofBigDecimal(ofString.multiply(ofInt))
            BigDecimalUtils.divide(multiply, calcularValorTotalCarteira(carteira))
        }

    }

    private fun cotacao(cotacoes: List<Cotacao>, ticker:Ticker) : Cotacao {
        return cotacoes.filter { cotacao -> cotacao.ticker == ticker }[0]
    }


    private fun calcularValorTotalCarteira(carteira: Carteira) =
        BigDecimalUtils.ofString(carteira.calcularValorTotalPago().number.toString())

}