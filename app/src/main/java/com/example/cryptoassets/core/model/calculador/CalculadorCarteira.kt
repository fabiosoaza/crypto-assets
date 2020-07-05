package com.example.cryptoassets.core.model.calculador

import com.example.cryptoassets.core.model.entidade.Carteira
import com.example.cryptoassets.core.model.entidade.Cotacao
import com.example.cryptoassets.core.model.entidade.Ticker
import com.example.cryptoassets.util.BigDecimalUtils
import com.example.cryptoassets.util.MoneyUtils
import org.javamoney.moneta.Money
import java.math.BigDecimal

class CalculadorCarteira {

    fun calculadorValorAtualCarteira(carteira: Carteira, cotacoes: List<Cotacao>): Money{
        if (carteira.investimentos.isEmpty()){
            return MoneyUtils.zero()
        }
        return carteira.investimentos.map { ativoCarteira ->
                cotacao(cotacoes, ativoCarteira.ativo.ticker)
                .valor.multiply(ativoCarteira.quantidade)
        }.groupingBy { 0 }.aggregate { _, accumulator: Money?, element: Money?, first ->
            if (first) element else accumulator?.add(element)
        }[0] ?: MoneyUtils.zero()
    }

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
            val variacao =
                BigDecimalUtils.ofString(
                    calcularVariacaoValor(carteira, cotacoes).number.toString()
                )
            val percent =
                BigDecimalUtils.ofInt(
                    100
                )
            val multiply =
                BigDecimalUtils.ofBigDecimal(
                    variacao.multiply(percent)
                )
            BigDecimalUtils.divide(
                multiply,
                calcularValorTotalCarteira(carteira)
            )
        }

    }

    private fun cotacao(cotacoes: List<Cotacao>, ticker: Ticker) : Cotacao {
        return cotacoes.filter { cotacao -> cotacao.ticker == ticker }[0]
    }


    private fun calcularValorTotalCarteira(carteira: Carteira) =
        BigDecimalUtils.ofString(
            carteira.calcularValorTotalPago().number.toString()
        )

}