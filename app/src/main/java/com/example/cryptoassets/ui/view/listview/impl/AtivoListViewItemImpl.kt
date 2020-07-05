package com.example.cryptoassets.ui.view.listview.impl

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.cryptoassets.core.model.entidade.Ativo
import com.example.cryptoassets.ui.view.listview.AtivoListViewItem
import com.example.cryptoassets.util.ResourceUtils
import kotlinx.android.synthetic.main.ativo_item.view.*

class AtivoListViewItemImpl(private val ativo: Ativo) :  AtivoListViewItem {

    private fun nome(): String {
       return  "${ativo.ticker.name} - ${ativo.nome}"
    }

    override fun update(itemView: View) {
        val txtNomeAtivo: TextView = itemView.txtNomeAtivo
        val iconeAtivo : ImageView = itemView.iconeAtivo

        iconeAtivo.setImageResource(ResourceUtils.getAssetImageResourceIdByTicker(ativo.ticker))
        updateTextViewCounter(txtNomeAtivo, nome())

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

}