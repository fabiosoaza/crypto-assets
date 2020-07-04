package com.example.cryptoassets.presenter

import com.example.cryptoassets.core.model.entidade.TipoTransacao
import com.example.cryptoassets.core.interactor.TransacaoInteractor
import com.example.cryptoassets.ui.view.TransacaoView
import java.time.LocalDateTime

class EdicaoTransacaoPresenter(private val view: TransacaoView, private val interactor: TransacaoInteractor) {

    fun salvar(codigoTicker: String?, precoMedio: String?, quantidade: String?, data: LocalDateTime?, tipoTransacao: TipoTransacao?){
        interactor.salvar(codigoTicker, precoMedio, quantidade, data, tipoTransacao, view)
    }

}