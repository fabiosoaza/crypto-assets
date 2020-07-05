package com.example.cryptoassets.ui.view.listview.impl

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.cryptoassets.core.model.entidade.Transacao
import com.example.cryptoassets.ui.view.listview.TransacaoListViewItem
import com.example.cryptoassets.util.FormatadorUtils
import com.example.cryptoassets.util.ResourceUtils
import kotlinx.android.synthetic.main.transacao_item.view.*

class TransacaoListViewItemImpl(private val transacao: Transacao):
    TransacaoListViewItem {

    override fun update(itemView:View){
        val txtNomeAtivo: TextView = itemView.txtNomeAtivo
        val txtTipoTransacao: TextView = itemView.txtTipoTransacao
        val txtQuantidade: TextView = itemView.txtQuantidade
        val txtPrecoMedio: TextView = itemView.txtPrecoMedio
        val txtValorTotal: TextView = itemView.txtValorTotal
        val txtData: TextView = itemView.txtData
        val iconeAtivo : ImageView = itemView.iconeAtivo

        iconeAtivo.setImageResource(ResourceUtils.getAssetImageResourceIdByTicker(transacao.ativo.ativo.ticker))

        updateTextViewCounter(txtNomeAtivo, nomeAtivo())
        updateTextViewCounter(txtTipoTransacao, tipoTransacao())
        updateTextViewCounter(txtQuantidade, getQuantidadeFormatada())
        updateTextViewCounter(txtPrecoMedio, getPrecoMedioFormatado())
        updateTextViewCounter(txtValorTotal, getValorTotalFormatado())
        updateTextViewCounter(txtData, data())


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

    private fun data(): String {
        return FormatadorUtils.formatarData(transacao.data)
    }

    private fun tipoTransacao(): String {
        return transacao.tipo.toString()
    }

    private fun nomeAtivo(): String {
        return "${transacao.ativo.ativo.ticker.name} - ${transacao.ativo.ativo.nome}"
    }

    private fun getQuantidadeFormatada(): String {
        return FormatadorUtils.formatarValor(transacao.ativo.quantidade)
    }

    private fun getPrecoMedioFormatado(): String {
        return FormatadorUtils.formatarValor(transacao.ativo.precoMedio)
    }

    private fun getValorTotalFormatado(): String {
        return FormatadorUtils.formatarValor(transacao.ativo.calcularTotalPago())
    }
}