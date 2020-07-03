package com.example.cryptoassets.ui.view.impl

import com.example.cryptoassets.core.model.entidade.Ativo
import com.example.cryptoassets.ui.view.AtivoListViewItem
import com.example.cryptoassets.ui.view.ListAtivosView

class ListAtivosViewImpl(private val items: List<Ativo>) : ListAtivosView {

    override fun getAtivos(): List<AtivoListViewItem> {
        return items.map { item -> AtivoListViewItemImpl(item) }
    }

}