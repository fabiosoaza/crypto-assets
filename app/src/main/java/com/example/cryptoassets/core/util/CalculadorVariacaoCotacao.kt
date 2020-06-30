package com.example.cryptoassets.core.util

import com.example.cryptoassets.core.domain.AtivoCarteira
import com.example.cryptoassets.core.domain.Cotacao
import org.javamoney.moneta.Money
import java.math.BigDecimal

class CalculadorVariacaoCotacao {

    fun calcularVariacaoValorTotalPago(ativoCarteira: AtivoCarteira, cotacao: Cotacao) : Money {
        if(ativoCarteira.quantidade == BigDecimal.ZERO){
            return MoneyUtils.zero()
        }

        val totalCotacao =  cotacao.valor.multiply(ativoCarteira.quantidade)

        return totalCotacao.subtract(ativoCarteira.calcularTotalPago())
    }

    fun calcularVariacaoPrecoMedio(ativoCarteira: AtivoCarteira, cotacao: Cotacao) : Money{
        if(ativoCarteira.quantidade == BigDecimal.ZERO){
            return MoneyUtils.zero()
        }
        return cotacao.valor.subtract(ativoCarteira.precoMedio)
    }

    fun calcularVariacaoPorcentagem(ativoCarteira: AtivoCarteira, cotacao: Cotacao) : BigDecimal{
        if(ativoCarteira.quantidade == BigDecimal.ZERO){
            return BigDecimal.ZERO
        }
        val diferencaEmDinheiro = calcularVariacaoPrecoMedio(ativoCarteira, cotacao)
        val diferenca = BigDecimalUtils.ofString(diferencaEmDinheiro.number.toString())
        val precoMedio = BigDecimalUtils.ofString(ativoCarteira.precoMedio.number.toString())
        val multiply = diferenca.multiply(BigDecimalUtils.ofInt(100))
        return BigDecimalUtils.divide(multiply, precoMedio)
    }

}