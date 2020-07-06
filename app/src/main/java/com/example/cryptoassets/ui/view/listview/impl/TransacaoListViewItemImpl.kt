package com.example.cryptoassets.ui.view.listview.impl

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptoassets.R
import com.example.cryptoassets.core.interactor.listener.OnExcluirTransacao
import com.example.cryptoassets.core.model.entidade.Transacao
import com.example.cryptoassets.fragment.ListagemTransacoesFragment
import com.example.cryptoassets.presenter.EdicaoTransacaoPresenter
import com.example.cryptoassets.ui.view.listview.TransacaoListViewItem
import com.example.cryptoassets.util.FormatadorUtils
import com.example.cryptoassets.util.ResourceUtils
import com.example.cryptoassets.util.UiUtils
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.transacao_item.view.*
import java.text.MessageFormat

class TransacaoListViewItemImpl(private val transacao: Transacao, private val context: Context, private val presenter: EdicaoTransacaoPresenter): TransacaoListViewItem, OnExcluirTransacao {

    override fun update(itemView:View){
        val txtNomeAtivo: TextView = itemView.txtNomeAtivo
        val txtTipoTransacao: TextView = itemView.txtTipoTransacao
        val txtQuantidade: TextView = itemView.txtQuantidade
        val txtPrecoMedio: TextView = itemView.txtPrecoMedio
        val txtValorTotal: TextView = itemView.txtValorTotal
        val txtData: TextView = itemView.txtData
        val iconeAtivo : ImageView = itemView.iconeAtivo
        val imageButtonExcluirTransacao : ImageButton = itemView.imageButtonExcluirTransacao

        iconeAtivo.setImageResource(ResourceUtils.getAssetImageResourceIdByTicker(transacao.ativo.ativo.ticker))

        updateTextViewCounter(txtNomeAtivo, nomeAtivo())
        updateTextViewCounter(txtTipoTransacao, tipoTransacao())
        updateTextViewCounter(txtQuantidade, getQuantidadeFormatada())
        updateTextViewCounter(txtPrecoMedio, getPrecoMedioFormatado())
        updateTextViewCounter(txtValorTotal, getValorTotalFormatado())
        updateTextViewCounter(txtData, data())

        imageButtonExcluirTransacao.setOnClickListener {
                it.context as AppCompatActivity

            val templateTituloConfirmacao = it.context.getString(R.string.tituloMensagemConfirmacaoExclusaoTransacao)
            val templateMensagemConfirmacao = it.context.getString(R.string.mensagemConfirmacaoExclusaoTransacao)
            val tituloConfirmacao = MessageFormat.format(templateTituloConfirmacao, transacao.ativo.ativo.nome)
            val mensagemConfirmacao = MessageFormat.format(templateMensagemConfirmacao, transacao.tipo.name, transacao.ativo.ativo.nome)
            val decline = it.context.getString(R.string.labelDecline)
            val delete = it.context.getString(R.string.labelDelete)
            MaterialAlertDialogBuilder(it.context)
                .setTitle(tituloConfirmacao)
                .setMessage(mensagemConfirmacao)
                .setNegativeButton(decline) { _, _ ->
                }
                .setPositiveButton(delete) { _, _ ->
                    presenter.excluir(transacao, this)
                }
                .show()
        }



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

    override fun onError(msg: String) {
        onSuccess(msg)
    }

    override fun onSuccess(msg: String) {
        val view = context as AppCompatActivity
        UiUtils.message(view, msg )
        UiUtils.showFragment(view.supportFragmentManager, R.id.main_container, ListagemTransacoesFragment())
    }
}