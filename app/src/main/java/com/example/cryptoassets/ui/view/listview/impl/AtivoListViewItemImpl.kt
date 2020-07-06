package com.example.cryptoassets.ui.view.listview.impl

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptoassets.R
import com.example.cryptoassets.core.interactor.listener.OnExcluirAtivo
import com.example.cryptoassets.core.model.entidade.Ativo
import com.example.cryptoassets.fragment.ListagemAtivosFragment
import com.example.cryptoassets.presenter.EdicaoAtivoPresenter
import com.example.cryptoassets.ui.view.listview.AtivoListViewItem
import com.example.cryptoassets.util.ResourceUtils
import com.example.cryptoassets.util.UiUtils
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.ativo_item.view.*
import java.text.MessageFormat

class AtivoListViewItemImpl(private val ativo: Ativo, private val context: Context, private val presenter: EdicaoAtivoPresenter) :  AtivoListViewItem, OnExcluirAtivo {

    private fun nome(): String {
       return  "${ativo.ticker.name} - ${ativo.nome}"
    }

    override fun update(itemView: View) {
        val txtNomeAtivo: TextView = itemView.txtNomeAtivo
        val iconeAtivo : ImageView = itemView.iconeAtivo
        val imageButtonExcluir : ImageButton = itemView.imageButtonExcluir

        iconeAtivo.setImageResource(ResourceUtils.getAssetImageResourceIdByTicker(ativo.ticker))
        updateTextViewCounter(txtNomeAtivo, nome())

        imageButtonExcluir.setOnClickListener {
            it.context as AppCompatActivity

            val templateTituloConfirmacao = it.context.getString(R.string.tituloMensagemConfirmacaoExclusaoAtivo)
            val templateMensagemConfirmacao = it.context.getString(R.string.mensagemConfirmacaoExclusaoAtivo)
            val tituloConfirmacao = MessageFormat.format(templateTituloConfirmacao, ativo.nome)
            val mensagemConfirmacao = MessageFormat.format(templateMensagemConfirmacao, ativo.nome)
            val decline = it.context.getString(R.string.labelDecline)
            val delete = it.context.getString(R.string.labelDelete)

            MaterialAlertDialogBuilder(it.context)
                .setTitle(tituloConfirmacao)
                .setMessage(mensagemConfirmacao)
                .setNegativeButton(decline) { _, _ ->
                }
                .setPositiveButton(delete) { _, _ ->
                    presenter.excluir(ativo.ticker.name, this)
                }
                .show()

        }

    }

    private fun updateTextViewCounter(viewTotal: TextView?, value: String?) {
        viewTotal?.text = value ?: "-"
    }

    override fun onError(msg: String) {
        onSuccess(msg)
    }

    override fun onSuccess(msg: String) {
        val view = context as AppCompatActivity
        UiUtils.message(view, msg )
        UiUtils.showFragment(view.supportFragmentManager, R.id.main_container, ListagemAtivosFragment())
    }

}