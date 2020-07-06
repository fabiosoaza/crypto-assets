package com.example.cryptoassets.presenter

import com.example.cryptoassets.core.interactor.TransacaoInteractor
import com.example.cryptoassets.core.interactor.listener.OnExcluirTransacao
import com.example.cryptoassets.core.interactor.listener.OnSalvarTransacao
import com.example.cryptoassets.core.model.entidade.TipoTransacao
import com.example.cryptoassets.core.model.entidade.Transacao
import java.time.LocalDateTime

class EdicaoTransacaoPresenter(private val interactor: TransacaoInteractor) {

    fun salvar(codigoTicker: String?, precoMedio: String?, quantidade: String?, data: LocalDateTime?, tipoTransacao: TipoTransacao?, listener: OnSalvarTransacao){
        interactor.salvar(codigoTicker, precoMedio, quantidade, data, tipoTransacao, listener)
    }

    fun excluir(transacao: Transacao, listener: OnExcluirTransacao){
        interactor.excluir(transacao, listener)
    }

}