package com.example.cryptoassets.ui.view.listview.impl

import android.content.Context
import android.view.View
import com.example.cryptoassets.core.model.entidade.Ativo
import com.example.cryptoassets.presenter.EdicaoAtivoPresenter
import com.example.cryptoassets.ui.view.listview.AtivoListViewItem
import com.example.cryptoassets.ui.view.listview.ListAtivosView

class ListAtivosViewImpl(private val items: List<Ativo>, private val context:Context, private val presenter:EdicaoAtivoPresenter) :  ListAtivosView {

    override fun update(itemView: View){

    }

    override fun getAtivos(): List<AtivoListViewItem> {
        return items.map { item -> AtivoListViewItemImpl(item, context, presenter) }
    }



}