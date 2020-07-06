package com.example.cryptoassets.ui.view.listview.impl

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.cryptoassets.R
import com.example.cryptoassets.core.model.calculador.CalculadorCarteira
import com.example.cryptoassets.core.model.entidade.Carteira
import com.example.cryptoassets.core.model.entidade.Cotacao
import com.example.cryptoassets.core.model.entidade.Ticker
import com.example.cryptoassets.ui.view.listview.AtivoCarteiraListViewItem
import com.example.cryptoassets.ui.view.listview.ListAtivosCarteiraView
import com.example.cryptoassets.util.FormatadorUtils
import com.example.cryptoassets.util.MoneyUtils
import kotlinx.android.synthetic.main.moeda_item.view.*
import kotlinx.android.synthetic.main.moeda_item_header.view.*
import org.javamoney.moneta.Money
import java.math.BigDecimal

class ListAtivosCarteiraViewImpl(private val carteira: Carteira, private val cotacoes : List<Cotacao>) :
    ListAtivosCarteiraView {

    override fun getAtivos(): List<AtivoCarteiraListViewItem> {
        return carteira.investimentos.map { ativo ->
            AtivoCarteiraListViewItemImpl(
                ativo,
                cotacao(ativo.ativo.ticker)
            )
        }
    }

    override fun update(viewToUpdate: View){
        val txtTotalBalance: TextView = viewToUpdate.txtTotalBalance
        val txtTotalInvestido: TextView = viewToUpdate.txtTotalInvestido
        val txtVariacaoPorcentagem: TextView = viewToUpdate.txtVariacaoPorcentagem
        val txtVariacaoPatrimonio: TextView = viewToUpdate.txtVariacaoPatrimonio


        updateTextViewCounter(txtTotalBalance, getValorPatrimonioAtualFormatado())
        updateTextViewCounter(txtTotalInvestido, getTotalInvestidoFormatado())
        updateTextViewCounter(txtVariacaoPatrimonio, getVariacaoTotalFormatada())
        updateTextViewCounter(txtVariacaoPorcentagem, getVariacaoPorcentagemFormatada())
        var textStyle = R.style.txtLabelVariacaoPrecoMedioNeutra
        if(variacaoValorTotal().isNegative){
            textStyle =  R.style.txtLabelVariacaoPrecoNegativa
        }
        else if(variacaoValorTotal().isPositive){
            textStyle =  R.style.txtLabelVariacaoPrecoPositiva
        }
        txtVariacaoPatrimonio.setTextAppearance( textStyle)
        txtVariacaoPorcentagem.setTextAppearance(textStyle)


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

    private fun getValorPatrimonioAtualFormatado(): String {
        val valor = CalculadorCarteira().calculadorValorAtualCarteira(carteira, cotacoes)
        return formatar(valor)
    }

    private fun getTotalInvestidoFormatado(): String {
        return formatar(carteira.calcularValorTotalPago())
    }

    private fun getVariacaoTotalFormatada(): String {
        return formatar(
            variacaoValorTotal()
        )
    }

    private fun variacaoValorTotal() = CalculadorCarteira()
        .calcularVariacaoValor(carteira, cotacoes)

    private fun getVariacaoPorcentagemFormatada(): String {
        val variacaoPorcentagem = formatar(variacaoPorcentagem())
        return "($variacaoPorcentagem%)"
    }

    private fun variacaoPorcentagem() = CalculadorCarteira()
        .calcularVariacaoPorcentagem(carteira, cotacoes)

    private fun formatar(valor: Money) = MoneyUtils.getFormatWithSymbol().format(valor)
    private fun formatar(valor: BigDecimal) = FormatadorUtils.formatarValor(valor)



    private fun cotacao(ticker: Ticker) : Cotacao {
        return cotacoes.filter { cotacao -> cotacao.ticker == ticker }[0]
    }
}