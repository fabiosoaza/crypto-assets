package com.example.cryptoassets.ui.view.impl

import com.example.cryptoassets.ui.view.AtivoCarteiraView
import com.example.cryptoassets.ui.interactor.AtivoCarteiraInteractor
import com.example.cryptoassets.ui.util.FormatadorUtils
import org.javamoney.moneta.Money
import java.math.BigDecimal

class AtivoCarteiraViewImpl(private val ativoCarteiraInteractor: AtivoCarteiraInteractor) : AtivoCarteiraView{

    override fun getCodigoTicker(): String {
        return ativoCarteiraInteractor.codigoTicker
    }

    override fun getQuantidadeFormatada(): String {
        return formatar(ativoCarteiraInteractor.quantidade)
    }

    override fun getPrecoMedioFormatado(): String {
        return formatar(ativoCarteiraInteractor.precoMedio)
    }

    override fun getValorTotalFormatado(): String {
        return formatar(ativoCarteiraInteractor.valorTotalPago)
    }

    override fun getVariacaoValorTotalPagoFormatada(): String {
        return formatar(ativoCarteiraInteractor.variacaoValorTotalPago)
    }

    override fun getVariacaoPorcentagemFormatada(): String {
        return formatar(ativoCarteiraInteractor.variacaoPorcentagem)
    }

    override fun getVariacaoPrecoMedioFormatada(): String {
        return formatar(ativoCarteiraInteractor.variacaoPrecoMedio)
    }
    override fun getValorCotacaoFormatada(): String {
        return formatar(ativoCarteiraInteractor.valorCotacao)
    }

    private fun formatar(valor: Money) : String {
        return FormatadorUtils.formatarValor(valor)
    }

    private fun formatar(valor: BigDecimal) : String {
        return FormatadorUtils.formatarValor(valor)
    }
}