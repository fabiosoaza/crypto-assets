package com.example.cryptoassets.ui.view.listview

import android.view.View
import com.example.cryptoassets.ui.view.listview.AdaptableListItemsView
import com.example.cryptoassets.ui.view.listview.AtivoCarteiraListViewItem

interface ListAtivosCarteiraView :
    AdaptableListItemsView {

    fun getAtivos(): List<AtivoCarteiraListViewItem>
    fun update(viewToUpdate: View)
}