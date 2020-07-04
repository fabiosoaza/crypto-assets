package com.example.cryptoassets.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import com.example.cryptoassets.R
import com.example.cryptoassets.context.ApplicationComponentsContext
import com.example.cryptoassets.core.interactor.listener.OnBuscarCotacao
import com.example.cryptoassets.core.model.entidade.Ativo
import com.example.cryptoassets.core.model.entidade.Cotacao
import com.example.cryptoassets.core.model.entidade.Ticker
import com.example.cryptoassets.core.model.entidade.TipoTransacao
import com.example.cryptoassets.presenter.EdicaoTransacaoPresenter
import com.example.cryptoassets.ui.component.ProgressBarComponent
import com.example.cryptoassets.ui.view.TransacaoView
import com.example.cryptoassets.util.FormatadorUtils
import com.example.cryptoassets.util.UiUtils
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.time.LocalDateTime


class AdicaoTransacaoFragment : TransacaoView, OnBuscarCotacao, Fragment() {

    private lateinit var fragmentContext: Context
    private var progressBarComponent: ProgressBarComponent?=null
    private lateinit var beanFactory : ApplicationComponentsContext
    private var cotacoes  = arrayListOf<Cotacao>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        beanFactory = ApplicationComponentsContext(fragmentContext)
        val root = inflater.inflate(R.layout.fragment_adicao_transacao, container, false)

        val presenter =
            EdicaoTransacaoPresenter(
                this,
                beanFactory.transacaoInteractor()
            )

        val spinner = root.findViewById<AutoCompleteTextView>(R.id.spinnerAtivo)
        spinner.setAdapter(adapterListagemAtivos(beanFactory.ativoRepository().ativos()))

        val txtOperacao = root.findViewById<TextView>(R.id.textViewTituloForm)
        txtOperacao.text = titulo()

        val btn = root.findViewById<Button>(R.id.btnAdicionar)
        btn.text = titulo()
        btn.contentDescription = contentDecription()

        btn.setOnClickListener {
            presenter.salvar(
                ticker(),
                precoMedio(),
                quantidade(),
                LocalDateTime.now(),
                tipoTransacao()
            )
        };

        val spinnerTicker = root.findViewById<AutoCompleteTextView>(R.id.spinnerAtivo)
        val layoutTicker = root.findViewById<TextInputLayout>(R.id.layoutSpinnerAtivo)
        spinnerTicker.addTextChangedListener(UiUtils.createClearInputErrorMessageListener(layoutTicker))

        spinnerTicker.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                updateTotais()
            }
        })


        val editPrecoMedio = root.findViewById<TextInputEditText>(R.id.editPrecoMedio)
        val layoutPrecoMedio = root.findViewById<TextInputLayout>(R.id.layoutEditPrecoMedio)
        editPrecoMedio.addTextChangedListener(UiUtils.createClearInputErrorMessageListener(layoutPrecoMedio))

        val editQuantidade = root.findViewById<TextInputEditText>(R.id.editQuantidade)
        val layoutQuantidade = root.findViewById<TextInputLayout>(R.id.layoutEditQuantidade)
        editQuantidade.addTextChangedListener(UiUtils.createClearInputErrorMessageListener(layoutQuantidade))

        progressBarComponent = ProgressBarComponent(activity as ComponentActivity, R.id.container, R.id.progressOverlay )
        beanFactory.buscaCotacaoInteractor().buscarCotacoes(this)

        return root
    }


    private fun txtValorCotacao(): TextView? {
        return view?.findViewById<TextView>(R.id.txtValorCotacao)
    }

    private fun layoutEditTicker(): TextInputLayout? {
        return view?.findViewById<TextInputLayout>(R.id.layoutSpinnerAtivo)
    }

    private fun editTicker(): AutoCompleteTextView? {
        return view?.findViewById(R.id.spinnerAtivo)
    }

    private fun ticker(): String {
        return editTicker()?.text?.toString()!!
    }

    private fun layoutEditPrecoMedio(): TextInputLayout? {
        return view?.findViewById<TextInputLayout>(R.id.layoutEditPrecoMedio)
    }

    private fun editPrecoMedio(): TextInputEditText? {
        return view?.findViewById(R.id.editPrecoMedio)
    }

    fun precoMedio(): String {
        return editPrecoMedio()?.text?.toString()!!
    }

    private fun editQuantidade(): TextInputEditText? {
        return view?.findViewById(R.id.editQuantidade)
    }

    fun quantidade(): String {
        return editQuantidade()?.text?.toString()!!
    }

    private fun layoutEditQuantidade(): TextInputLayout? {
        return view?.findViewById<TextInputLayout>(R.id.layoutEditQuantidade)
    }

    private fun adapterListagemAtivos(ativos: Set<Ativo>): ArrayAdapter<String> {
        val elements = ativos.map { ativo -> ativo.ticker.name }.sorted()
        val adapter = ArrayAdapter<String>(
            this.fragmentContext,
            R.layout.moeda_spinner_item,
            R.id.txtViewNomeMoeda,
            elements
        )
        return adapter
    }

    private fun contentDecription(): String {
        return if (tipoTransacao() == TipoTransacao.COMPRA)
            getString(R.string.contentDescriptionBuyAsset)
        else

            getString(R.string.contentDescriptionSellAsset)
    }

    private fun titulo(): String {
        return if (tipoTransacao() == TipoTransacao.COMPRA)
            getString(R.string.txtBuyAsset)
        else

            getString(R.string.txtSellAsset)
    }

    private fun tipoTransacao(): TipoTransacao? {
        val codigo = if (arguments?.get("tipoTransacao") != null)
            arguments!!.getInt("tipoTransacao")
        else
            TipoTransacao.COMPRA.codigo

        return TipoTransacao.criar(codigo)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }

    override fun onErrorQuantidadeInvalida(msg: String) {
        UiUtils.showInputTextError(editQuantidade(), layoutEditQuantidade(), msg)
    }

    override fun onErrorPrecoMedioInvalido(msg: String) {
        UiUtils.showInputTextError(editPrecoMedio(), layoutEditPrecoMedio(), msg)
    }

    override fun onErrorTickerInvalido(msg: String) {
        UiUtils.showInputTextError(editTicker(), layoutEditTicker(), msg)
    }

    override fun onErrorTipoTransacaoInvalida(msg: String) {
        UiUtils.message(activity as ComponentActivity, msg)
    }

    override fun onErrorDataTransacaoInvalida(msg: String) {
        UiUtils.message(activity as ComponentActivity, msg)
    }

    override fun onSuccess(msg: String) {
        UiUtils.message(activity as ComponentActivity, msg)
        UiUtils.closeAndGoBack(fragmentManager, this)
    }

    override fun onErrorConnection(msg: String) {
        UiUtils.message(activity as ComponentActivity, msg)
    }

    override fun onErrorBuscarCotacao(msg: String) {
        UiUtils.message(activity as ComponentActivity, msg)
    }

    override fun onPreExecute() {
        progressBarComponent?.show()
    }

    override fun onPostExecute(result: List<Cotacao>) {
        progressBarComponent?.hide()
        cotacoes.clear()
        cotacoes.addAll(result)
        updateTotais()
    }

    private fun updateTotais(){
        val ticker = Ticker.criar(ticker())
        if(ticker!=null){
            val cotacao = cotacoes.filter { cotacao -> cotacao.ticker ==  ticker}.first()
            txtValorCotacao()?.text = FormatadorUtils.formatarValor(cotacao.valor.numberStripped)
        }

    }

}