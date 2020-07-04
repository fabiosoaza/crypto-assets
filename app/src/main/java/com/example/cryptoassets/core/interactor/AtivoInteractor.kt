package com.example.cryptoassets.core.interactor

import android.content.Context
import com.example.cryptoassets.R
import com.example.cryptoassets.core.interactor.listener.OnSalvarAtivo
import com.example.cryptoassets.core.model.entidade.Ativo
import com.example.cryptoassets.core.model.entidade.Ticker
import com.example.cryptoassets.core.repository.AtivoRepository
import com.example.cryptoassets.util.ResourceUtil
import java.text.MessageFormat

class AtivoInteractor(private val repository: AtivoRepository, private val context: Context) {


    fun salvar(ticker: String?, nomeAtivo: String?, listener: OnSalvarAtivo){
        val valido = validar(ticker, nomeAtivo, listener)
        if(valido){
            val ativo = Ativo(
                Ticker.criar(ticker!!)!!, nomeAtivo!!
            )
            repository.salvar(ativo)
            listener.onSuccess(ResourceUtil.getString(context,R.string.msgAtivoSalvoComSucesso)!!)
        }
    }

    private fun validar(ticker: String?, nomeAtivo: String?, listener: OnSalvarAtivo):Boolean{
        if(isEmpty(ticker)){
            listener.onErrorTickerInvalido(formatarMensagem(R.string.labelTickerAtivo))
            return false
        }
        if(isEmpty(nomeAtivo)){
            listener.onErrorNomeInvalido(formatarMensagem(R.string.labelNomeAtivo))
            return false
        }
        val encontrado = repository.findByTicker(Ticker.criar(ticker!!)!!)
        if(encontrado!=null){
            listener.onErrorAtivoJaCadastrado(ResourceUtil.getString(context,R.string.msgErroSalvarAtivoJaCadastrado)!!)
            return false
        }
        return true
    }

    private fun isEmpty(valor: String?) = valor == null || valor == ""

    private fun formatarMensagem(fieldResourceId:Int) : String{
        val campo = ResourceUtil.getString(context, fieldResourceId)
        val message = ResourceUtil.getString(context, R.string.labelMessageErrorMandatory)
        return  MessageFormat.format(message!!, campo!!)
    }

}