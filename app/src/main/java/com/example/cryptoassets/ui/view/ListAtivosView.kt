package com.example.cryptoassets.ui.view

interface ListAtivosView : AdaptableListItemsView {

    fun getAtivos(): List<AtivoListViewItem>

}