package com.example.cryptoassets.ui.view.impl

import com.example.cryptoassets.core.util.CalculadorTransacoes
import com.example.cryptoassets.core.domain.Transacao
import com.example.cryptoassets.core.util.MoneyUtils
import com.example.cryptoassets.ui.util.FormatadorUtils
import com.example.cryptoassets.ui.view.ListTransacoesView
import com.example.cryptoassets.ui.view.TransacaoListViewItem

class ListTransacoesViewImpl(private val transacoes: List<Transacao>) : ListTransacoesView {

    override fun getValorTotalFormatado(): String {
        val valor = if (transacoes.isEmpty()) {
            MoneyUtils.zero()
        } else {
            CalculadorTransacoes().calcularValorTotal(transacoes)
        }
        return FormatadorUtils.formatarValor(valor)
    }

    override fun getAtivos(): List<TransacaoListViewItem> {
        return transacoes.map { transacao -> TransacaoListViewItemImpl(transacao) }
    }


}