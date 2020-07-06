package com.example.cryptoassets.ui.view.listview.impl.builder

import android.content.Context
import com.example.cryptoassets.core.model.entidade.Ativo
import com.example.cryptoassets.presenter.EdicaoAtivoPresenter
import com.example.cryptoassets.ui.view.listview.ListAtivosView
import com.example.cryptoassets.ui.view.listview.impl.ListAtivosViewImpl

class ListAtivosViewBuilder {

    private var ativos: MutableList<Ativo> = mutableListOf<Ativo>()
    private var context:Context?=null
    private var presenter:EdicaoAtivoPresenter?=null

    fun ativos(ativos: MutableList<Ativo>) = apply {
        this.ativos = ativos
    }

    fun context(context:Context)= apply {
        this.context = context
    }

    fun presenter(presenter:EdicaoAtivoPresenter) = apply  {
        this.presenter = presenter
    }

    fun build(): ListAtivosView {
        return ListAtivosViewImpl(ativos, context!!, presenter!!)
    }
}