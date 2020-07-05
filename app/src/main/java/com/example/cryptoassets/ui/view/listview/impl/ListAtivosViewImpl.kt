package com.example.cryptoassets.ui.view.listview.impl

import android.view.View
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import com.example.cryptoassets.core.model.entidade.Ativo
import com.example.cryptoassets.ui.view.listview.AtivoListViewItem
import com.example.cryptoassets.ui.view.listview.ListAtivosView
import kotlinx.android.synthetic.main.ativo_item_header.view.*

class ListAtivosViewImpl(private val items: List<Ativo>) :  ListAtivosView {

    override fun update(itemView: View){
        val txtTotalAtivos: TextView = itemView.txtTotalAtivos
        updateTextViewCounter(txtTotalAtivos,getAtivos().size.toString())
    }


    override fun getAtivos(): List<AtivoListViewItem> {
        return items.map { item -> AtivoListViewItemImpl(item) }
    }

    private fun updateTextViewCounter(viewTotal: TextView?, value: String?) {
        viewTotal?.text = value ?: "-"
    }

}