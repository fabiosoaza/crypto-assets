package com.example.cryptoassets.ui.view.listview.impl.builder

import com.example.cryptoassets.core.model.entidade.Transacao
import com.example.cryptoassets.ui.view.listview.ListTransacoesView
import com.example.cryptoassets.ui.view.listview.impl.ListTransacoesViewImpl

class ListTransacoesViewBuilder {

    private var transacoes : MutableList<Transacao> = mutableListOf<Transacao>()

    fun ativos(transacoes: MutableList<Transacao>) = apply {
        this.transacoes = transacoes
    }

    fun build(): ListTransacoesView {
        return ListTransacoesViewImpl(transacoes)
    }
}