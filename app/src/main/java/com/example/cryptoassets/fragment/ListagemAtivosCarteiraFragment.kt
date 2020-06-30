package com.example.cryptoassets.fragment

import com.example.cryptoassets.R
import com.example.cryptoassets.core.domain.AtivoCarteira
import com.example.cryptoassets.core.domain.Cotacao
import com.example.cryptoassets.ui.adapter.ListAtivosCarteiraAdapter
import com.example.cryptoassets.ui.view.ListAtivosCarteiraView
import com.example.cryptoassets.ui.view.impl.builder.ListAtivosCarteiraViewBuilder


class ListagemAtivosCarteiraFragment : ListItemsFragmentBase() {

    private var listAdapter =
        ListAtivosCarteiraAdapter(
            ListAtivosCarteiraViewBuilder()
                .build()
        )

    override fun recyclerViewId() = R.id.rvListagemMoedas

    override fun fragmentLayoutId() = R.layout.fragment_resumo

    override fun listAdapter() = listAdapter

    override fun dataSet(): ListAtivosCarteiraView {
        return ListAtivosCarteiraViewBuilder()
            .cotacoes(cotacoes())
            .ativos(ativos())
            .build()
    }

    private fun cotacoes(): MutableList<Cotacao> {
        return beanFactory().cotacaoRepository().cotacoes().toMutableList()
    }

    private fun ativos(): MutableList<AtivoCarteira> {
        return beanFactory().ativoCarteiraRepository().ativos().toMutableList()
    }

}

