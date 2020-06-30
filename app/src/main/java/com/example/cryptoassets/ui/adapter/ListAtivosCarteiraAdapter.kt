package com.example.cryptoassets.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoassets.R
import com.example.cryptoassets.ui.view.AdaptableListItemsView
import com.example.cryptoassets.ui.view.AtivoCarteiraListViewItem
import com.example.cryptoassets.ui.view.ListAtivosCarteiraView
import kotlinx.android.synthetic.main.moeda_item.view.*
import kotlinx.android.synthetic.main.moeda_item_header.view.*

class ListAtivosCarteiraAdapter(private var listAtivosCarteiraView: ListAtivosCarteiraView) :
    BaseListItemsAdapter() {

    private val TYPE_HEADER: Int = 0
    private val TYPE_ITEM: Int = 1

    override fun update(view: AdaptableListItemsView) {
        this.listAtivosCarteiraView = view as ListAtivosCarteiraView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = layoutInflater(parent, viewType)
        return if (viewType == TYPE_HEADER) {
            AtivoHeaderViewHolder(
                itemView
            )
        } else {
            AtivoItemViewHolder(
                itemView
            )
        }
    }

    private fun layoutInflater(parent: ViewGroup, viewType: Int): View {
        return if (viewType == TYPE_HEADER) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.moeda_item_header, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.moeda_item, parent, false)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_HEADER
        } else {
            TYPE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return listAtivosCarteiraView.getAtivos().size + 1
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is AtivoItemViewHolder) {
            val holder: AtivoItemViewHolder? = viewHolder
            val item = listAtivosCarteiraView.getAtivos()[position - 1]
            holder?.update(item)
        }
        else if(viewHolder is AtivoHeaderViewHolder){
            val holder: AtivoHeaderViewHolder? = viewHolder
            holder?.update(listAtivosCarteiraView)
        }
    }


    class AtivoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtLabelNomeMoeda: TextView = itemView.txtLabelNomeMoeda
        var txtCotacaoMoeda: TextView = itemView.txtCotacaoMoeda
        var txtQuantidadeMoeda: TextView = itemView.txtQuantidadeMoeda
        var txtPrecoMedioPago: TextView = itemView.txtPrecoMedioPago
        var txtValorTotalPago: TextView = itemView.txtValorTotalPago
        var txtVariacaoTotalPrecoMedioMoeda: TextView = itemView.txtVariacaoTotalPrecoMedioMoeda

        fun update(ativo: AtivoCarteiraListViewItem) {
            updateTextViewCounter(this.txtLabelNomeMoeda, ativo.getCodigoTicker())
            updateTextViewCounter(this.txtCotacaoMoeda, ativo.getValorCotacaoFormatada())
            updateTextViewCounter(this.txtQuantidadeMoeda, ativo.getQuantidadeFormatada())
            updateTextViewCounter(this.txtPrecoMedioPago, ativo.getPrecoMedioFormatado())
            updateTextViewCounter(this.txtValorTotalPago, ativo.getValorTotalFormatado())
            updateTextViewCounter(
                this.txtVariacaoTotalPrecoMedioMoeda,
                ativo.getVariacaoValorTotalPagoFormatada()
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

    }

    class AtivoHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTotalBalance: TextView = itemView.txtTotalBalance
        var txtVariacaoTotalPrecoMedio: TextView = itemView.txtVariacaoTotalPrecoMedio
        var txtVariacaoPorcentagemPrecoMedio: TextView = itemView.txtVariacaoPorcentagemPrecoMedio

        fun update(listAtivosCarteiraView:ListAtivosCarteiraView){
            updateTextViewCounter(this.txtTotalBalance, listAtivosCarteiraView.getSaldoFormatado())
            updateTextViewCounter(this.txtVariacaoTotalPrecoMedio, listAtivosCarteiraView.getVariacaoTotalFormatada())
            updateTextViewCounter(this.txtVariacaoPorcentagemPrecoMedio, listAtivosCarteiraView.getVariacaoPorcentagemFormatada())

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

}