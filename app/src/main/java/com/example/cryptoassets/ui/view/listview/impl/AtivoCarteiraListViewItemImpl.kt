package com.example.cryptoassets.ui.view.listview.impl

import android.view.View
import android.widget.TextView
import com.example.cryptoassets.core.model.calculador.CalculadorVariacaoCotacao
import com.example.cryptoassets.core.model.entidade.AtivoCarteira
import com.example.cryptoassets.core.model.entidade.Cotacao
import com.example.cryptoassets.ui.view.listview.AtivoCarteiraListViewItem
import com.example.cryptoassets.util.FormatadorUtils
import kotlinx.android.synthetic.main.moeda_item.view.*
import org.javamoney.moneta.Money
import java.math.BigDecimal

class AtivoCarteiraListViewItemImpl(private val ativoCarteira: AtivoCarteira, private val cotacao: Cotacao) :
    AtivoCarteiraListViewItem {

    private fun getCodigoTicker(): String {
        return ativoCarteira.ativo.ticker.name
    }

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
        return formatar(
            CalculadorVariacaoCotacao()
                .calcularVariacaoValorTotalPago(ativoCarteira, cotacao))
    }

    private fun getVariacaoPorcentagemFormatada(): String {
        return formatar(
            CalculadorVariacaoCotacao()
                .calcularVariacaoPorcentagem(ativoCarteira, cotacao))
    }

    private fun getVariacaoPrecoMedioFormatada(): String {
        return formatar(
            CalculadorVariacaoCotacao()
                .calcularVariacaoPrecoMedio(ativoCarteira, cotacao))
    }

    override fun update(itemView: View) {
        val txtLabelNomeMoeda: TextView = itemView.txtLabelNomeMoeda
        val txtCotacaoMoeda: TextView = itemView.txtCotacaoMoeda
        val txtQuantidadeMoeda: TextView = itemView.txtQuantidadeMoeda
        val txtPrecoMedioPago: TextView = itemView.txtPrecoMedioPago
        val txtValorTotalPago: TextView = itemView.txtValorTotalPago
        val txtVariacaoTotalPrecoMedioMoeda: TextView = itemView.txtVariacaoTotalPrecoMedioMoeda

            updateTextViewCounter(txtLabelNomeMoeda, getCodigoTicker())
            updateTextViewCounter(txtCotacaoMoeda, getValorCotacaoFormatada())
            updateTextViewCounter(txtQuantidadeMoeda, getQuantidadeFormatada())
            updateTextViewCounter(txtPrecoMedioPago, getPrecoMedioFormatado())
            updateTextViewCounter(txtValorTotalPago, getValorTotalFormatado())
            updateTextViewCounter(txtVariacaoTotalPrecoMedioMoeda,getVariacaoValorTotalPagoFormatada()
            )

            /* CasosCovidUtil.updateContentDecriptionSummary(
              holder?.itemView?.context,
              holder?.viewGroupCaseByStateItem,
              caso,
              holder?.itemView?.context?.getString(R.string.contentDescriptionItemList)
          )*/



    }

    private fun updateTextViewCounter(viewTotal: TextView?, value: String?) {
        viewTotal?.text = value ?: "-"
    }
    private fun getValorCotacaoFormatada(): String {
        return formatar(cotacao.valor)
    }

    private fun formatar(valor: Money) : String {
        return FormatadorUtils.formatarValor(valor)
    }

    private fun formatar(valor: BigDecimal) : String {
        return FormatadorUtils.formatarValor(valor)
    }
}