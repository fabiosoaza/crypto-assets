package com.example.cryptoassets.ui.view.listview

interface TransacaoListViewItem {

    fun data():String
    fun tipoTransacao():String
    fun nomeAtivo():String
    fun getQuantidadeFormatada(): String
    fun getPrecoMedioFormatado(): String
    fun getValorTotalFormatado(): String
}