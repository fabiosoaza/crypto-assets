package com.example.cryptoassets.ui.view.listview.impl

import com.example.cryptoassets.core.model.entidade.Carteira
import com.example.cryptoassets.core.model.entidade.Cotacao
import com.example.cryptoassets.core.model.entidade.Ticker
import com.example.cryptoassets.core.model.calculador.CalculadorCarteira
import com.example.cryptoassets.util.FormatadorUtils
import com.example.cryptoassets.ui.view.listview.AtivoCarteiraListViewItem
import com.example.cryptoassets.ui.view.listview.ListAtivosCarteiraView
import org.javamoney.moneta.Money
import java.math.BigDecimal

class ListAtivosCarteiraViewImpl(private val carteira: Carteira, private val cotacoes : List<Cotacao>) :
    ListAtivosCarteiraView {

    override fun getSaldoFormatado(): String {
        return formatar(carteira.calcularValorTotalPago())
    }

    override fun getVariacaoTotalFormatada(): String {
        return formatar(
            CalculadorCarteira()
                .calcularVariacaoValor(carteira, cotacoes))
    }

    override fun getVariacaoPorcentagemFormatada(): String {
        return formatar(
            CalculadorCarteira()
                .calcularVariacaoPorcentagem(carteira, cotacoes))
    }

    private fun formatar(valor: Money) = FormatadorUtils.formatarValor(valor)
    private fun formatar(valor: BigDecimal) = FormatadorUtils.formatarValor(valor)


    override fun getAtivos(): List<AtivoCarteiraListViewItem> {
        return carteira.investimentos.map { ativo ->
            AtivoCarteiraListViewItemImpl(
                ativo,
                cotacao(ativo.ativo.ticker)
            )
        }
    }

    private fun cotacao(ticker: Ticker) : Cotacao {
        return cotacoes.filter { cotacao -> cotacao.ticker == ticker }[0]
    }
}