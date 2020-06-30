package com.example.cryptoassets.ui.view

interface ListAtivosCarteiraView : AdaptableListItemsView {

    fun getSaldoFormatado(): String
    fun getVariacaoTotalFormatada(): String
    fun getVariacaoPorcentagemFormatada(): String
    fun getAtivos(): List<AtivoCarteiraListViewItem>

}