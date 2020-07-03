package com.example.cryptoassets.ui.view.impl

import com.example.cryptoassets.core.model.entidade.Ativo
import com.example.cryptoassets.ui.view.AtivoListViewItem

class AtivoListViewItemImpl(private val ativo: Ativo) : AtivoListViewItem {

    override fun ticker(): String {
        return ativo.ticker.name
    }

    override fun nome(): String {
       return  ativo.nome
    }


}