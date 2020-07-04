package com.example.cryptoassets.ui.view.listview

import com.example.cryptoassets.ui.view.listview.AdaptableListItemsView
import com.example.cryptoassets.ui.view.listview.AtivoCarteiraListViewItem

interface ListAtivosCarteiraView :
    AdaptableListItemsView {

    fun getSaldoFormatado(): String
    fun getVariacaoTotalFormatada(): String
    fun getVariacaoPorcentagemFormatada(): String
    fun getAtivos(): List<AtivoCarteiraListViewItem>

}