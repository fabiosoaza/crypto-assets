package com.example.cryptoassets.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoassets.R
import com.example.cryptoassets.ui.view.listview.AdaptableListItemsView
import com.example.cryptoassets.ui.view.listview.AtivoListViewItem
import com.example.cryptoassets.ui.view.listview.ListAtivosView
import kotlinx.android.synthetic.main.ativo_item.view.*
import kotlinx.android.synthetic.main.ativo_item_footer.view.*

class ListAtivosAdapter(private var listAtivosView: ListAtivosView) :
    BaseListItemsAdapter() {

    private val TYPE_FOOTER: Int = 0
    private val TYPE_ITEM: Int = 1

    override fun update(view: AdaptableListItemsView) {
        this.listAtivosView = view as ListAtivosView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = layoutInflater(parent, viewType)
        return if (viewType == TYPE_FOOTER) {
            AtivoFooterViewHolder(
                itemView
            )
        } else {
            AtivoItemViewHolder(
                itemView
            )
        }
    }

    private fun layoutInflater(parent: ViewGroup, viewType: Int): View {
        return if (viewType == TYPE_FOOTER) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.ativo_item_footer, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.ativo_item, parent, false)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_FOOTER
        } else {
            TYPE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return listAtivosView.getAtivos().size + 1
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is AtivoItemViewHolder) {
            val holder: AtivoItemViewHolder? = viewHolder
            val item = listAtivosView.getAtivos()[position - 1]
            holder?.update(item)
        } else if (viewHolder is AtivoFooterViewHolder) {
            val holder: AtivoFooterViewHolder? = viewHolder
            holder?.update(listAtivosView)
        }
    }


    class AtivoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtNomeAtivo: TextView = itemView.txtNomeAtivo
        var txtTickerAtivo: TextView = itemView.txtTickerAtivo

        fun update(ativo: AtivoListViewItem) {
            updateTextViewCounter(this.txtNomeAtivo, ativo.nome())
            updateTextViewCounter(this.txtTickerAtivo, ativo.ticker())

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

    class AtivoFooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var txtTotalAtivos: TextView = itemView.txtTotalAtivos

        fun update(listItemsView: ListAtivosView) {
            updateTextViewCounter(
                this.txtTotalAtivos,
                listItemsView.getAtivos().size.toString()
            )
        }

        private fun updateTextViewCounter(viewTotal: TextView?, value: String?) {
            viewTotal?.text = value ?: "-"
        }

    }

}