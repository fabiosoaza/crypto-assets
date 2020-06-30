package com.example.cryptoassets.ui.view.impl

import com.example.cryptoassets.core.domain.Transacao
import com.example.cryptoassets.ui.util.FormatadorUtils
import com.example.cryptoassets.ui.view.TransacaoListViewItem

class TransacaoListViewItemImpl(private val transacao: Transacao):TransacaoListViewItem {

    override fun data(): String {
        return FormatadorUtils.formatarData(transacao.data)
    }

    override fun tipoTransacao(): String {
        return transacao.tipo.toString()
    }

    override fun nomeAtivo(): String {
        return transacao.ativo.ativo.nome
    }

    override fun getQuantidadeFormatada(): String {
        return FormatadorUtils.formatarValor(transacao.ativo.quantidade)
    }

    override fun getPrecoMedioFormatado(): String {
        return FormatadorUtils.formatarValor(transacao.ativo.precoMedio)
    }

    override fun getValorTotalFormatado(): String {
        return FormatadorUtils.formatarValor(transacao.ativo.calcularTotalPago())
    }
}