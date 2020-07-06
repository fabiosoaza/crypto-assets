package com.example.cryptoassets.core.interactor

import android.content.Context
import com.example.cryptoassets.R
import com.example.cryptoassets.core.interactor.listener.OnExcluirAtivo
import com.example.cryptoassets.core.interactor.listener.OnSalvarAtivo
import com.example.cryptoassets.core.model.entidade.Ativo
import com.example.cryptoassets.core.model.entidade.Ticker
import com.example.cryptoassets.core.repository.AtivoCarteiraRepository
import com.example.cryptoassets.core.repository.AtivoRepository
import com.example.cryptoassets.core.repository.TransacaoRepository
import com.example.cryptoassets.util.ResourceUtils
import java.text.MessageFormat

class AtivoInteractor(
    private val context: Context,
    private val repository: AtivoRepository,
    private val transacaoRepository: TransacaoRepository,
    private val ativoCarteiraRepository: AtivoCarteiraRepository
) {

    fun excluir(ticker: String?, listener: OnExcluirAtivo) {
        try {
            val ativo = repository.findByTicker(Ticker.criar(ticker!!)!!)
            ativoCarteiraRepository.excluir(ativo!!)
            transacaoRepository.excluirTodas(ativo)
            repository.excluir(ativo)
            val mensagemSucesso = mensagemExclusaoSucesso(ativo!!.nome)
            listener.onSuccess(mensagemSucesso)
        }catch(ex:Exception){
            val mensagemErro = mensagemExclusaoErro(ticker)
            listener.onError(mensagemErro)
        }

    }

    fun salvar(ticker: String?, nomeAtivo: String?, listener: OnSalvarAtivo){
        val valido = validar(ticker, nomeAtivo, listener)
        if(valido){
            val ativo = Ativo(
                Ticker.criar(ticker!!)!!, nomeAtivo!!
            )
            repository.salvar(ativo)
            listener.onSuccess(ResourceUtils.getString(context,R.string.msgAtivoSalvoComSucesso)!!)
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
            listener.onErrorAtivoJaCadastrado(ResourceUtils.getString(context,R.string.msgErroSalvarAtivoJaCadastrado)!!)
            return false
        }
        return true
    }

    private fun isEmpty(valor: String?) = valor == null || valor == ""

    private fun formatarMensagem(fieldResourceId:Int) : String{
        val campo = ResourceUtils.getString(context, fieldResourceId)
        val message = ResourceUtils.getString(context, R.string.labelMessageErrorMandatory)
        return  MessageFormat.format(message!!, campo!!)
    }

    private fun mensagemExclusaoSucesso(ativo:String) : String{
        val message = ResourceUtils.getString(context, R.string.mensagemExclusaoSucesso)
        return  MessageFormat.format(message!!, ativo)
    }
    private fun mensagemExclusaoErro(ativo:String?="") : String{
        val message = ResourceUtils.getString(context, R.string.mensagemExclusaoErro)
        return  MessageFormat.format(message!!, ativo)
    }



}