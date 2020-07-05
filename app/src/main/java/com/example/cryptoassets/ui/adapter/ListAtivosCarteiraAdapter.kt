package com.example.cryptoassets.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoassets.R
import com.example.cryptoassets.ui.view.listview.AdaptableListItemsView
import com.example.cryptoassets.ui.view.listview.AtivoCarteiraListViewItem
import com.example.cryptoassets.ui.view.listview.ListAtivosCarteiraView

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

        fun update(viewItem: AtivoCarteiraListViewItem) {
            viewItem.update(itemView)

        }

    }

    class AtivoHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
             fun update(listAtivosCarteiraView: ListAtivosCarteiraView){
            listAtivosCarteiraView.update(itemView)
        }
    }

}