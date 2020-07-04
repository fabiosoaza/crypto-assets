package com.example.cryptoassets.fragment

import com.example.cryptoassets.R
import com.example.cryptoassets.core.model.entidade.Transacao
import com.example.cryptoassets.ui.adapter.ListTransacoesAdapter
import com.example.cryptoassets.ui.view.listview.ListTransacoesView
import com.example.cryptoassets.ui.view.listview.impl.builder.ListTransacoesViewBuilder


class ListagemTransacoesFragment : ListItemsFragmentBase() {


    private var listAdapter =
        ListTransacoesAdapter(
            ListTransacoesViewBuilder()
                .build()
        )

    override fun recyclerViewId() = R.id.rvListagemTransacoes

    override fun fragmentLayoutId() = R.layout.fragment_listagem_transacoes

    override fun listAdapter() = listAdapter

    override fun dataSet(): ListTransacoesView {
        return ListTransacoesViewBuilder()
            .ativos(transacoes())
            .build()
    }

    private fun transacoes(): MutableList<Transacao> {
        return beanFactory().transacaoRepository().transacoes().toMutableList()
    }


}
