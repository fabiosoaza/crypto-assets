package com.example.cryptoassets.ui.view

interface ListTransacoesView : AdaptableListItemsView {

    fun getValorTotalFormatado():String
    fun getAtivos(): List<TransacaoListViewItem>

}