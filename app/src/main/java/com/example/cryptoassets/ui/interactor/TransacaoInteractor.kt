package com.example.cryptoassets.ui.interactor

import android.content.Context
import android.text.TextUtils.isEmpty
import com.example.cryptoassets.R
import com.example.cryptoassets.core.domain.AtivoCarteira
import com.example.cryptoassets.core.domain.Ticker
import com.example.cryptoassets.core.domain.TipoTransacao
import com.example.cryptoassets.core.domain.Transacao
import com.example.cryptoassets.core.repository.AtivoCarteiraRepository
import com.example.cryptoassets.core.repository.AtivoRepository
import com.example.cryptoassets.core.repository.TransacaoRepository
import com.example.cryptoassets.core.util.BigDecimalUtils
import com.example.cryptoassets.core.util.MoneyUtils
import com.example.cryptoassets.util.ResourceUtil
import java.text.MessageFormat
import java.time.LocalDateTime

class TransacaoInteractor(
    private val context: Context,
    private val transacaoRepository: TransacaoRepository,
    private val ativoRepository: AtivoRepository,
    private val ativoCarteiraRepository: AtivoCarteiraRepository
) {

    interface  OnCliqueSalvar{
        fun onErrorQuantidadeInvalida(msg: String)
        fun onErrorPrecoMedioInvalido(msg: String)
        fun onErrorTickerInvalido(msg: String)
        fun onErrorTipoTransacaoInvalida(msg: String)
        fun onErrorDataTransacaoInvalida(msg: String)
        fun onSuccess(msg: String)
    }

    fun salvar(codigoTicker: String?, precoMedio: String?, quantidade: String?, data:LocalDateTime?, tipoTransacao:TipoTransacao?,  listener: TransacaoInteractor.OnCliqueSalvar){
        val valido = validar(codigoTicker, precoMedio, quantidade, data, tipoTransacao, listener)
        if(valido){
            val ticker = Ticker.criar(codigoTicker!!)!!
            val ativo = ativoRepository.findByTicker(ticker)!!
            val preco = MoneyUtils.of(BigDecimalUtils.ofString(precoMedio!!))
            val qtd = BigDecimalUtils.ofString(quantidade!!)
            val ativoCarteira = AtivoCarteira(ativo, qtd, preco)
            val transacao = Transacao(ativoCarteira, data!!, tipoTransacao!!)

            transacaoRepository.salvar(transacao)
            ativoCarteiraRepository.salvar(carteiraSalvar(ativoCarteira))

            val resourceId = if (tipoTransacao == TipoTransacao.COMPRA) R.string.msgTransacaoCompraSucesso
            else R.string.msgTransacaoVendaSucesso
            val mensagem = formatarMensagemSucesso(resourceId, ativo.nome)
            listener.onSuccess(mensagem)

        }
    }

    private fun carteiraSalvar(ativoCarteira: AtivoCarteira) : AtivoCarteira {
        val carteiraAtualizar = ativoCarteiraRepository.buscar(ativoCarteira.ativo.ticker)
        if (carteiraAtualizar == null) {
           return ativoCarteira
        }
        else{
            val novaQuantidade = carteiraAtualizar.quantidade + ativoCarteira.quantidade
            val somaPrecoMedio = carteiraAtualizar.precoMedio.add(ativoCarteira.precoMedio)
            val novoPrecoMedio = somaPrecoMedio.divide(novaQuantidade)
            val novaCarteira = AtivoCarteira(
                carteiraAtualizar.id,
                carteiraAtualizar.ativo,
                novaQuantidade,
                novoPrecoMedio
            )
            return novaCarteira
        }
    }


    private fun validar(codigoTicker: String?, precoMedio: String?, quantidade: String?, data:LocalDateTime?, tipoTransacao:TipoTransacao?,  listener: TransacaoInteractor.OnCliqueSalvar):Boolean{
        if(isEmpty(codigoTicker)){
            listener.onErrorTickerInvalido(formatarMensagemErro(R.string.labelTickerAtivo))
            return false
        }
        if(isEmpty(precoMedio)){
            listener.onErrorPrecoMedioInvalido(formatarMensagemErro(R.string.labelValor))
            return false
        }
        if(isEmpty(quantidade)){
            listener.onErrorQuantidadeInvalida(formatarMensagemErro(R.string.labelQuantidade))
            return false
        }
        if(data == null){
            listener.onErrorDataTransacaoInvalida(formatarMensagemErro(R.string.labelDataTransacao))
            return false
        }
        if(tipoTransacao == null){
            listener.onErrorTipoTransacaoInvalida(formatarMensagemErro(R.string.labelTipoTransacao))
            return false
        }

        return true

    }


    private fun formatarMensagemErro(fieldResourceId:Int) : String{
        val campo = ResourceUtil.getString(context, fieldResourceId)
        val message = ResourceUtil.getString(context, R.string.labelMessageErrorMandatory)
        return  MessageFormat.format(message!!, campo!!)
    }

    private fun formatarMensagemSucesso(messageResourceId:Int, campo: String ) : String{
        val message = ResourceUtil.getString(context, messageResourceId)
        return  MessageFormat.format(message!!, campo)
    }

}