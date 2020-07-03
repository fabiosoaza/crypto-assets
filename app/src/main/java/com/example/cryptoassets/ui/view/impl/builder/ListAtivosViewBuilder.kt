package com.example.cryptoassets.ui.view.impl.builder

import com.example.cryptoassets.core.model.entidade.Ativo
import com.example.cryptoassets.ui.view.ListAtivosView
import com.example.cryptoassets.ui.view.impl.ListAtivosViewImpl

class ListAtivosViewBuilder {

    private var ativos: MutableList<Ativo> = mutableListOf<Ativo>()

    fun ativos(ativos: MutableList<Ativo>) = apply {
        this.ativos = ativos
    }

    fun build(): ListAtivosView {
        return ListAtivosViewImpl(ativos)
    }
}