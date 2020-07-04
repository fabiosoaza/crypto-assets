package com.example.cryptoassets.ui.view.listview.impl

import com.example.cryptoassets.core.model.entidade.AtivoCarteira
import com.example.cryptoassets.core.model.calculador.CalculadorVariacaoCotacao
import com.example.cryptoassets.core.model.entidade.Cotacao
import com.example.cryptoassets.util.FormatadorUtils
import com.example.cryptoassets.ui.view.listview.AtivoCarteiraListViewItem
import org.javamoney.moneta.Money
import java.math.BigDecimal

class AtivoCarteiraListViewItemImpl(private val ativoCarteira: AtivoCarteira, private val cotacao: Cotacao) :
    AtivoCarteiraListViewItem {

    override fun getCodigoTicker(): String {
        return ativoCarteira.ativo.ticker.name
    }

    override fun getQuantidadeFormatada(): String {
        return formatar(ativoCarteira.quantidade)
    }

    override fun getPrecoMedioFormatado(): String {
        return formatar(ativoCarteira.precoMedio)
    }

    override fun getValorTotalFormatado(): String {
        return formatar(ativoCarteira.calcularTotalPago())
    }

    override fun getVariacaoValorTotalPagoFormatada(): String {
        return formatar(
            CalculadorVariacaoCotacao()
                .calcularVariacaoValorTotalPago(ativoCarteira, cotacao))
    }

    override fun getVariacaoPorcentagemFormatada(): String {
        return formatar(
            CalculadorVariacaoCotacao()
                .calcularVariacaoPorcentagem(ativoCarteira, cotacao))
    }

    override fun getVariacaoPrecoMedioFormatada(): String {
        return formatar(
            CalculadorVariacaoCotacao()
                .calcularVariacaoPrecoMedio(ativoCarteira, cotacao))
    }
    override fun getValorCotacaoFormatada(): String {
        return formatar(cotacao.valor)
    }

    private fun formatar(valor: Money) : String {
        return FormatadorUtils.formatarValor(valor)
    }

    private fun formatar(valor: BigDecimal) : String {
        return FormatadorUtils.formatarValor(valor)
    }
}