package com.example.cryptoassets.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.cryptoassets.R
import com.example.cryptoassets.core.domain.Ativo
import com.example.cryptoassets.ui.adapter.ListAtivosAdapter
import com.example.cryptoassets.ui.view.ListAtivosView
import com.example.cryptoassets.ui.view.impl.builder.ListAtivosViewBuilder


class ListagemAtivosFragment : ListItemsFragmentBase() {


    private var listAdapter = ListAtivosAdapter(
        ListAtivosViewBuilder()
            .build()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)
        val btn = root?.findViewById<Button>(R.id.btnNovoAtivo)
        btn?.setOnClickListener {
           showFragment(AdicaoAtivoFragment())
        };

        return root
    }

    override fun recyclerViewId() = R.id.rvListagemAtivos

    override fun fragmentLayoutId() = R.layout.fragment_listagem_ativos

    override fun listAdapter() = listAdapter

    override fun dataSet(): ListAtivosView {
        return ListAtivosViewBuilder()
            .ativos(ativos())
            .build()
    }

    private fun ativos(): MutableList<Ativo> {
        return beanFactory().ativoRepository().ativos().toMutableList()
    }


}
