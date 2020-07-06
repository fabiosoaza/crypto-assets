package com.example.cryptoassets.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoassets.R
import com.example.cryptoassets.fragment.AdicaoAtivoFragment
import com.example.cryptoassets.ui.view.listview.AdaptableListItemsView
import com.example.cryptoassets.ui.view.listview.AtivoListViewItem
import com.example.cryptoassets.ui.view.listview.ListAtivosView
import com.example.cryptoassets.util.UiUtils
import kotlinx.android.synthetic.main.ativo_item_footer.view.*


class ListAtivosAdapter(private var listAtivosView: ListAtivosView) :  BaseListItemsAdapter() {

    private val TYPE_HEADER: Int = 0
    private val TYPE_ITEM: Int = 1
    private val TYPE_FOOTER: Int = 2

    override fun update(view: AdaptableListItemsView) {
        this.listAtivosView = view as ListAtivosView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = layoutInflater(parent, viewType)
        return if (viewType == TYPE_HEADER) {
            AtivoHeaderViewHolder(
                itemView
            )
        } else if (viewType == TYPE_ITEM){
            AtivoItemViewHolder(
                itemView
            )
        }
        else{
            AtivoFooterViewHolder(itemView)
        }
    }

    private fun layoutInflater(parent: ViewGroup, viewType: Int): View {
        return if (viewType == TYPE_HEADER) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.ativo_item_header, parent, false)
        } else if (viewType == TYPE_ITEM) {
            LayoutInflater.from(parent.context).inflate(R.layout.ativo_item, parent, false)
        }
        else{
            LayoutInflater.from(parent.context).inflate(R.layout.ativo_item_footer, parent, false)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_HEADER
        } else if(position == listAtivosView.getAtivos().size + 1) {
            TYPE_FOOTER
        }
        else{
            TYPE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return listAtivosView.getAtivos().size + 2
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is AtivoItemViewHolder) {
            val holder: AtivoItemViewHolder? = viewHolder
            val item = listAtivosView.getAtivos()[position - 1]
            holder?.update(item)
        } else if (viewHolder is AtivoHeaderViewHolder) {
            val holder: AtivoHeaderViewHolder? = viewHolder
            holder?.update(listAtivosView)
        }
        else if (viewHolder is AtivoFooterViewHolder){
            val holder: AtivoFooterViewHolder? = viewHolder
            holder?.update()
        }
    }


    class AtivoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun update(ativo: AtivoListViewItem) {
            ativo.update(itemView)
        }
    }

    class AtivoHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun update(listItemsView: ListAtivosView) {
            listItemsView.update(itemView)
        }

    }

    class AtivoFooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnNovoAtivo : Button = itemView.btnNovoAtivo
        fun update(){
            btnNovoAtivo.setOnClickListener {
                val activity = it.context as AppCompatActivity
                UiUtils.showFragment(activity.supportFragmentManager, R.id.main_container,  AdicaoAtivoFragment())
            };

        }



    }

}