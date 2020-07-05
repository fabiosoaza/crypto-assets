package com.example.cryptoassets.ui.view.listview

import android.view.View
import com.example.cryptoassets.ui.view.listview.AdaptableListItemsView
import com.example.cryptoassets.ui.view.listview.AtivoListViewItem

interface ListAtivosView : AdaptableListItemsView {

    fun getAtivos(): List<AtivoListViewItem>
    fun update(itemView: View)
}