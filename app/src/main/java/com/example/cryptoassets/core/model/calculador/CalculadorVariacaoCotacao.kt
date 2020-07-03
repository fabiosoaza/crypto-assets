package com.example.cryptoassets.core.model.calculador

import com.example.cryptoassets.core.model.entidade.AtivoCarteira
import com.example.cryptoassets.core.model.entidade.Cotacao
import com.example.cryptoassets.util.BigDecimalUtils
import com.example.cryptoassets.util.MoneyUtils
import org.javamoney.moneta.Money
import java.math.BigDecimal

class CalculadorVariacaoCotacao {

    fun calcularVariacaoValorTotalPago(ativoCarteira: AtivoCarteira, cotacaoAtual: Cotacao) : Money {
        if(ativoCarteira.quantidade == BigDecimal.ZERO){
            return MoneyUtils.zero()
        }
        return  calcularVariacaoPrecoMedio(ativoCarteira, cotacaoAtual).multiply(ativoCarteira.quantidade)
    }

    fun calcularVariacaoPrecoMedio(ativoCarteira: AtivoCarteira, cotacao: Cotacao) : Money{
        if(ativoCarteira.quantidade == BigDecimal.ZERO){
            return MoneyUtils.zero()
        }
        return cotacao.valor.subtract(ativoCarteira.precoMedio)
    }

    fun calcularVariacaoPorcentagem(ativoCarteira: AtivoCarteira, cotacaoAtual: Cotacao) : BigDecimal{
        if(ativoCarteira.quantidade == BigDecimal.ZERO){
            return BigDecimal.ZERO
        }
        val precoMedio = BigDecimalUtils.ofString(ativoCarteira.precoMedio.number.toString())
        val diferenca = calcularVariacaoPrecoMedio(ativoCarteira, cotacaoAtual).number.toString()
        val valor = BigDecimalUtils.ofString(diferenca).multiply(BigDecimalUtils.ofInt(100))
        return BigDecimalUtils.divide(valor,precoMedio)
    }

}