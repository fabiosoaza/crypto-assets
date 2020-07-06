package com.example.cryptoassets.ui.view.listview.impl.builder

import android.content.Context
import com.example.cryptoassets.core.model.entidade.Transacao
import com.example.cryptoassets.presenter.EdicaoAtivoPresenter
import com.example.cryptoassets.presenter.EdicaoTransacaoPresenter
import com.example.cryptoassets.ui.view.listview.ListTransacoesView
import com.example.cryptoassets.ui.view.listview.impl.ListTransacoesViewImpl

class ListTransacoesViewBuilder {

    private var transacoes : MutableList<Transacao> = mutableListOf<Transacao>()
    private var context: Context?=null
    private var presenter: EdicaoTransacaoPresenter?=null

    fun ativos(transacoes: MutableList<Transacao>) = apply {
        this.transacoes = transacoes
    }

    fun context(context:Context)= apply {
        this.context = context
    }

    fun presenter(presenter:EdicaoTransacaoPresenter) = apply  {
        this.presenter = presenter
    }

    fun build(): ListTransacoesView {
        return ListTransacoesViewImpl(transacoes, context!!, presenter!!)
    }
}