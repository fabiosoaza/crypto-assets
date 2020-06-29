package com.example.cryptoassets.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoassets.R
import com.example.cryptoassets.ui.view.AdaptableListItemsView
import com.example.cryptoassets.ui.view.ListTransacoesView
import com.example.cryptoassets.ui.view.TransacaoListViewItem
import kotlinx.android.synthetic.main.transacao_item.view.*
import kotlinx.android.synthetic.main.transacao_item_header.view.*

class ListTransacoesAdapter(private var listTransacoesView: ListTransacoesView) :
    BaseListItemsAdapter() {

    private val TYPE_HEADER: Int = 0
    private val TYPE_ITEM: Int = 1

    override fun update(view: AdaptableListItemsView) {
        this.listTransacoesView = view as ListTransacoesView
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
                .inflate(R.layout.transacao_item_header, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.transacao_item, parent, false)
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
        return listTransacoesView.getAtivos().size + 1
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is AtivoItemViewHolder) {
            val holder: AtivoItemViewHolder? = viewHolder
            val item = listTransacoesView.getAtivos()[position - 1]
            holder?.update(item)
        } else if (viewHolder is AtivoHeaderViewHolder) {
            val holder: AtivoHeaderViewHolder? = viewHolder
            holder?.update(listTransacoesView)
        }
    }


    class AtivoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtNomeAtivo: TextView = itemView.txtNomeAtivo
        var txtTipoTransacao: TextView = itemView.txtTipoTransacao
        var txtQuantidade: TextView = itemView.txtQuantidade
        var txtPrecoMedio: TextView = itemView.txtPrecoMedio
        var txtValorTotal: TextView = itemView.txtValorTotal
        var txtData: TextView = itemView.txtData

        fun update(ativo: TransacaoListViewItem) {
            updateTextViewCounter(this.txtNomeAtivo, ativo.nomeAtivo())
            updateTextViewCounter(this.txtTipoTransacao, ativo.tipoTransacao())
            updateTextViewCounter(this.txtQuantidade, ativo.getQuantidadeFormatada())
            updateTextViewCounter(this.txtPrecoMedio, ativo.getPrecoMedioFormatado())
            updateTextViewCounter(this.txtValorTotal, ativo.getValorTotalFormatado())
            updateTextViewCounter(this.txtData, ativo.data())


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
        private var txtTotalTransacionadoPeriodo: TextView = itemView.txtTotalTransacionadoPeriodo

        fun update(listTransacoesView: ListTransacoesView) {
            updateTextViewCounter(
                this.txtTotalTransacionadoPeriodo,
                listTransacoesView.getValorTotalFormatado()
            )
        }

        private fun updateTextViewCounter(viewTotal: TextView?, value: String?) {
            viewTotal?.text = value ?: "-"
        }

    }

}