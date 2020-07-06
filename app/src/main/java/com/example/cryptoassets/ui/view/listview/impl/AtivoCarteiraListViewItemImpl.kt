package com.example.cryptoassets.ui.view.listview.impl

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.cryptoassets.R
import com.example.cryptoassets.core.model.calculador.CalculadorVariacaoCotacao
import com.example.cryptoassets.core.model.entidade.AtivoCarteira
import com.example.cryptoassets.core.model.entidade.Cotacao
import com.example.cryptoassets.core.model.entidade.Ticker
import com.example.cryptoassets.ui.view.listview.AtivoCarteiraListViewItem
import com.example.cryptoassets.util.FormatadorUtils
import com.example.cryptoassets.util.MoneyUtils
import com.example.cryptoassets.util.ResourceUtils
import kotlinx.android.synthetic.main.moeda_item.view.*
import org.javamoney.moneta.Money
import java.math.BigDecimal

class AtivoCarteiraListViewItemImpl(private val ativoCarteira: AtivoCarteira, private val cotacao: Cotacao) :
    AtivoCarteiraListViewItem {

    private fun getQuantidadeFormatada(): String {
        return formatar(ativoCarteira.quantidade)
    }

    private fun getPrecoMedioFormatado(): String {
        return formatar(ativoCarteira.precoMedio)
    }

    private fun getValorTotalFormatado(): String {
        return formatar(ativoCarteira.calcularTotalPago())
    }

    private fun getVariacaoValorTotalPagoFormatada(): String {
        val variacaoPrecoPago = formatar(
            variacaoPrecoPago()
        )
        val variacaoPorcentagem = formatar(variacaoPorcentagem())

        return "$variacaoPrecoPago($variacaoPorcentagem%)"
    }

    private fun variacaoPrecoPago() = CalculadorVariacaoCotacao()
        .calcularVariacaoValorTotalPago(ativoCarteira, cotacao)

    private fun variacaoPorcentagem() = CalculadorVariacaoCotacao()
        .calcularVariacaoPorcentagem(ativoCarteira, cotacao)

    private fun getValorCotacaoFormatada(): String {
        return formatar(cotacao.valor)
    }


    override fun update(itemView: View) {
        val txtNomeAtivo: TextView = itemView.txtNomeAtivo
        val txtCotacaoMoeda: TextView = itemView.txtCotacaoMoeda
        val txtValorTotalPago: TextView = itemView.txtValorTotalPago
        val txtQuantidadeMoeda: TextView = itemView.txtQuantidadeMoeda
        val txtPrecoMedioPago: TextView = itemView.txtPrecoMedioPago
        val txtVariacaoTotalPrecoMedioMoeda: TextView = itemView.txtVariacaoTotalPrecoMedioMoeda
        val iconeAtivo : ImageView = itemView.iconeAtivo

        iconeAtivo.setImageResource(ResourceUtils.getAssetImageResourceIdByTicker(ativoCarteira.ativo.ticker))

        updateTextViewCounter(txtNomeAtivo, getNomeAtivo())
        updateTextViewCounter(txtCotacaoMoeda, getValorCotacaoFormatada())
        updateTextViewCounter(txtQuantidadeMoeda, getQuantidadeFormatada())
        updateTextViewCounter(txtPrecoMedioPago, getPrecoMedioFormatado())
        updateTextViewCounter(txtValorTotalPago, getValorTotalFormatado())
        updateTextViewCounter(txtVariacaoTotalPrecoMedioMoeda, getVariacaoValorTotalPagoFormatada())



        var textStyle = R.style.txtAtivoCarteiraLabelVariacaoNeutra
        if(  variacaoPrecoPago().isNegative){
            textStyle =  R.style.txtAtivoCarteiraLabelVariacaoNegativa
        }
        else if(variacaoPrecoPago().isPositive){
            textStyle =  R.style.txtAtivoCarteiraLabelVariacaoPositiva
        }
        txtVariacaoTotalPrecoMedioMoeda.setTextAppearance( textStyle)


            /* CasosCovidUtil.updateContentDecriptionSummary(
              holder?.itemView?.context,
              holder?.viewGroupCaseByStateItem,
              caso,
              holder?.itemView?.context?.getString(R.string.contentDescriptionItemList)
          )*/



    }



    private fun getNomeAtivo() = "${ativoCarteira.ativo.ticker.name} - ${ativoCarteira.ativo.nome}"

    private fun updateTextViewCounter(viewTotal: TextView?, value: String?) {
        viewTotal?.text = value ?: "-"
    }


    private fun formatar(valor: Money) : String {
        return MoneyUtils.getFormatWithSymbol().format(valor)
    }

    private fun formatar(valor: BigDecimal) : String {
        return FormatadorUtils.formatarValor(valor)
    }
}