package com.example.cryptoassets.ui.view.listview

import com.example.cryptoassets.ui.view.listview.AdaptableListItemsView
import com.example.cryptoassets.ui.view.listview.TransacaoListViewItem

interface ListTransacoesView :
    AdaptableListItemsView {

    fun getValorTotalFormatado():String
    fun getAtivos(): List<TransacaoListViewItem>

}