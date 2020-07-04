package com.example.cryptoassets.ui.view.listview.impl

import com.example.cryptoassets.core.model.calculador.CalculadorTransacoes
import com.example.cryptoassets.core.model.entidade.Transacao
import com.example.cryptoassets.util.MoneyUtils
import com.example.cryptoassets.util.FormatadorUtils
import com.example.cryptoassets.ui.view.listview.ListTransacoesView
import com.example.cryptoassets.ui.view.listview.TransacaoListViewItem

class ListTransacoesViewImpl(private val transacoes: List<Transacao>) :
    ListTransacoesView {

    override fun getValorTotalFormatado(): String {
        val valor = if (transacoes.isEmpty()) {
            MoneyUtils.zero()
        } else {
            CalculadorTransacoes()
                .calcularValorTotal(transacoes)
        }
        return FormatadorUtils.formatarValor(valor)
    }

    override fun getAtivos(): List<TransacaoListViewItem> {
        return transacoes.map { transacao -> TransacaoListViewItemImpl(transacao) }
    }


}