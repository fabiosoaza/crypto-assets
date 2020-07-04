package com.example.cryptoassets.ui.view.listview.impl

import com.example.cryptoassets.core.model.entidade.Ativo
import com.example.cryptoassets.ui.view.listview.AtivoListViewItem
import com.example.cryptoassets.ui.view.listview.ListAtivosView

class ListAtivosViewImpl(private val items: List<Ativo>) :
    ListAtivosView {

    override fun getAtivos(): List<AtivoListViewItem> {
        return items.map { item -> AtivoListViewItemImpl(item) }
    }

}