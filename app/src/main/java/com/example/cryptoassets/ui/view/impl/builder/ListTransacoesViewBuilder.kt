package com.example.cryptoassets.ui.view.impl.builder

import com.example.cryptoassets.core.domain.Transacao
import com.example.cryptoassets.ui.view.ListTransacoesView
import com.example.cryptoassets.ui.view.impl.ListTransacoesViewImpl

class ListTransacoesViewBuilder {

    private var transacoes : MutableList<Transacao> = mutableListOf<Transacao>()

    fun ativos(transacoes: MutableList<Transacao>) = apply {
        this.transacoes = transacoes
    }

    fun build(): ListTransacoesView {
        return ListTransacoesViewImpl(transacoes)
    }
}