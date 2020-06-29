package com.example.cryptoassets.ui.interactor

import com.example.cryptoassets.core.domain.AtivoCarteira
import com.example.cryptoassets.core.domain.CalculadorVariacaoCotacao
import com.example.cryptoassets.core.domain.Cotacao
import org.javamoney.moneta.Money
import java.math.BigDecimal

class AtivoCarteiraInteractor(private val ativoCarteira:AtivoCarteira, private val cotacao: Cotacao) {

    val codigoTicker : String get() = ativoCarteira.ativo.nome
    val quantidade : BigDecimal get() = ativoCarteira.quantidade
    val precoMedio: Money get() = ativoCarteira.precoMedio
    val valorTotalPago: Money get() = ativoCarteira.calcularTotalPago()
    val valorCotacao: Money get() = cotacao.valor
    val variacaoValorTotalPago: Money get(){
        return CalculadorVariacaoCotacao().calcularVariacaoValorTotalPago(ativoCarteira, cotacao)
    }
    val variacaoPorcentagem: BigDecimal get(){
        return CalculadorVariacaoCotacao().calcularVariacaoPorcentagem(ativoCarteira, cotacao)
    }
    val variacaoPrecoMedio: Money get(){
        return CalculadorVariacaoCotacao().calcularVariacaoPrecoMedio(ativoCarteira, cotacao)
    }

}