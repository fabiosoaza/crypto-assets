package com.example.cryptoassets.fragment

import com.example.cryptoassets.R
import com.example.cryptoassets.core.model.entidade.Ativo
import com.example.cryptoassets.presenter.EdicaoAtivoPresenter
import com.example.cryptoassets.ui.adapter.ListAtivosAdapter
import com.example.cryptoassets.ui.view.listview.ListAtivosView
import com.example.cryptoassets.ui.view.listview.impl.builder.ListAtivosViewBuilder


class ListagemAtivosFragment : ListItemsFragmentBase() {


    private lateinit var listAdapter :ListAtivosAdapter

    override fun onPreCreateView() {
        super.onPreCreateView()
        listAdapter = ListAtivosAdapter(
            builder().build())


    }

    override fun recyclerViewId() = R.id.rvListagemAtivos

    override fun fragmentLayoutId() = R.layout.fragment_listagem_ativos

    override fun listAdapter() = listAdapter

    override fun dataSet(): ListAtivosView {
        return builder()
            .build()
    }

    private fun builder(): ListAtivosViewBuilder {
        return ListAtivosViewBuilder()
            .ativos(ativos())
            .context(fragmentContext())
            .presenter(
                EdicaoAtivoPresenter(
                    beanFactory().ativoInteractor()
                )
            )
    }

    private fun ativos(): MutableList<Ativo> {
        return beanFactory().ativoRepository().ativos().toMutableList()
    }


}
