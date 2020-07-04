package com.example.cryptoassets.ui.view.listview

interface AtivoCarteiraListViewItem {

    fun getCodigoTicker(): String
    fun getQuantidadeFormatada(): String
    fun getPrecoMedioFormatado(): String
    fun getValorTotalFormatado(): String
    fun getValorCotacaoFormatada():String
    fun getVariacaoValorTotalPagoFormatada(): String
    fun getVariacaoPorcentagemFormatada(): String
    fun getVariacaoPrecoMedioFormatada():String

}