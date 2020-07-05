package com.example.cryptoassets.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoassets.R
import com.example.cryptoassets.ui.view.listview.AdaptableListItemsView
import com.example.cryptoassets.ui.view.listview.ListTransacoesView
import com.example.cryptoassets.ui.view.listview.TransacaoListViewItem

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

        fun update(view: TransacaoListViewItem) {
            view.update(itemView)
        }

    }

    class AtivoHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun update(listTransacoesView: ListTransacoesView) {

        }


    }

}