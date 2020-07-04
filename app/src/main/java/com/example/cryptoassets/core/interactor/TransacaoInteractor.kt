package com.example.cryptoassets.core.interactor

import android.content.Context
import android.text.TextUtils.isEmpty
import com.example.cryptoassets.R
import com.example.cryptoassets.core.interactor.listener.OnSalvarTransacao
import com.example.cryptoassets.core.model.entidade.AtivoCarteira
import com.example.cryptoassets.core.model.entidade.Ticker
import com.example.cryptoassets.core.model.entidade.TipoTransacao
import com.example.cryptoassets.core.model.entidade.Transacao
import com.example.cryptoassets.core.repository.AtivoCarteiraRepository
import com.example.cryptoassets.core.repository.AtivoRepository
import com.example.cryptoassets.core.repository.TransacaoRepository
import com.example.cryptoassets.util.BigDecimalUtils
import com.example.cryptoassets.util.MoneyUtils
import com.example.cryptoassets.util.ResourceUtil
import java.text.MessageFormat
import java.time.LocalDateTime

class TransacaoInteractor(
    private val context: Context,
    private val transacaoRepository: TransacaoRepository,
    private val ativoRepository: AtivoRepository,
    private val ativoCarteiraRepository: AtivoCarteiraRepository
) {



    fun salvar(codigoTicker: String?, precoMedio: String?, quantidade: String?, data:LocalDateTime?, tipoTransacao: TipoTransacao?, listener: OnSalvarTransacao){
        val valido = validar(codigoTicker, precoMedio, quantidade, data, tipoTransacao, listener)
        if(valido){
            val ticker = Ticker.criar(codigoTicker!!)!!
            val ativo = ativoRepository.findByTicker(ticker)!!
            val preco = MoneyUtils.of(BigDecimalUtils.ofString(precoMedio!!))
            val qtd = BigDecimalUtils.ofString(quantidade!!)
            val ativoCarteira =
                AtivoCarteira(
                    ativo,
                    qtd,
                    preco
                )
            val transacao =
                Transacao(
                    ativoCarteira,
                    data!!,
                    tipoTransacao!!
                )

            transacaoRepository.salvar(transacao)
            ativoCarteiraRepository.salvar(carteiraSalvar(ativoCarteira, tipoTransacao))

            val resourceId = if (tipoTransacao == TipoTransacao.COMPRA) R.string.msgTransacaoCompraSucesso
            else R.string.msgTransacaoVendaSucesso
            val mensagem = formatarMensagemSucesso(resourceId, ativo.nome)
            listener.onSuccess(mensagem)

        }
    }

    private fun carteiraSalvar(
        ativoCarteira: AtivoCarteira,
        tipoTransacao: TipoTransacao
    ) : AtivoCarteira {
        val carteiraAtualizar = ativoCarteiraRepository.buscar(ativoCarteira.ativo.ticker)
        if (carteiraAtualizar == null) {
           return ativoCarteira
        }
        else{
            val novaQuantidade = if(tipoTransacao == TipoTransacao.COMPRA) carteiraAtualizar.quantidade + ativoCarteira.quantidade
                                 else carteiraAtualizar.quantidade - ativoCarteira.quantidade
            val somaPrecoMedio = if(tipoTransacao == TipoTransacao.COMPRA) carteiraAtualizar.precoMedio.add(ativoCarteira.precoMedio)
                                 else  carteiraAtualizar.precoMedio.subtract(ativoCarteira.precoMedio)
            val novoPrecoMedio = somaPrecoMedio.divide(novaQuantidade)
            val novaCarteira =
                AtivoCarteira(
                    carteiraAtualizar.id,
                    carteiraAtualizar.ativo,
                    novaQuantidade,
                    novoPrecoMedio
                )
            return novaCarteira
        }
    }


    private fun validar(codigoTicker: String?, precoMedio: String?, quantidade: String?, data:LocalDateTime?, tipoTransacao: TipoTransacao?, listener: OnSalvarTransacao):Boolean{
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