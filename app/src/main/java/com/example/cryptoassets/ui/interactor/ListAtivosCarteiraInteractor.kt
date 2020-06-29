package com.example.cryptoassets.ui.interactor

import com.example.cryptoassets.core.domain.Ativo
import com.example.cryptoassets.core.domain.CalculadorVariacaoCotacao
import com.example.cryptoassets.core.domain.Carteira
import com.example.cryptoassets.core.domain.Cotacao
import com.example.cryptoassets.core.util.BigDecimalUtils
import com.example.cryptoassets.core.util.MoneyUtils
import org.javamoney.moneta.Money
import java.math.BigDecimal

class ListAtivosCarteiraInteractor(private val carteira: Carteira, private val cotacoes: List<Cotacao>) {

    val valorSaldo: Money get() = carteira.calcularValorTotalPago()
    val variacaoValor: Money get() = calcularVariacaoValor()
    val variacaoPorcentagem: BigDecimal get() = calcularVariacaoPorcentagem()
    val ativoCarteiras : List<AtivoCarteiraInteractor> get(){
        return carteira.investimentos.map { ativoCarteira ->
            AtivoCarteiraInteractor(
                ativoCarteira,
                cotacao(ativoCarteira.ativo)
            )
        }
    }

     private fun calcularVariacaoValor() : Money{
         if (carteira.investimentos.isEmpty()){
             return MoneyUtils.zero()
         }
         val calculadorVariacaoCotacao = CalculadorVariacaoCotacao()
         val calculo = carteira.investimentos.map { ativoCarteira ->
             calculadorVariacaoCotacao.calcularVariacaoValorTotalPago(
                 ativoCarteira,
                 cotacao(ativoCarteira.ativo)
             )
         }.groupingBy { 0 }.aggregate { _, accumulator: Money?, element: Money?, first ->
             if (first) element else accumulator?.add(element)
         }[0] ?: MoneyUtils.zero()
         return calculo

     }

    private fun calcularVariacaoPorcentagem(): BigDecimal {
        return if (carteira.calcularValorTotalPago().isZero)
            BigDecimal.ZERO
        else {
            val ofString = BigDecimalUtils.ofString(calcularVariacaoValor().number.toString())
            val ofInt = BigDecimalUtils.ofInt(100)
            val multiply = BigDecimalUtils.ofBigDecimal(ofString.multiply(ofInt))
            BigDecimalUtils.divide(multiply, calcularValorTotalCarteira())
        }

    }

    private fun calcularValorTotalCarteira() =
        BigDecimalUtils.ofString(carteira.calcularValorTotalPago().number.toString())

    private fun cotacao(ativo: Ativo) : Cotacao{
        return cotacoes.filter { cotacao -> cotacao.ativo == ativo }[0]
    }

}