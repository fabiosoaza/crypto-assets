package com.example.cryptoassets.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import com.example.cryptoassets.R
import com.example.cryptoassets.context.ApplicationComponentsContext
import com.example.cryptoassets.core.interactor.listener.OnSalvarAtivo
import com.example.cryptoassets.core.model.entidade.Ticker
import com.example.cryptoassets.presenter.EdicaoAtivoPresenter
import com.example.cryptoassets.ui.view.AtivoView
import com.example.cryptoassets.util.UiUtils
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class AdicaoAtivoFragment : AtivoView, OnSalvarAtivo, Fragment() {

    private lateinit var fragmentContext : Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val  beanFactory = ApplicationComponentsContext(fragmentContext)

        val root = inflater.inflate(R.layout.fragment_adicao_ativo, container, false)

        val presenter = EdicaoAtivoPresenter(
            beanFactory.ativoInteractor()
        )

        val spinner = root.findViewById<AutoCompleteTextView>(R.id.spinnerAtivo)
        spinner.setAdapter(tickersListAdapter())

        val btn = root.findViewById<Button>(R.id.btnAdicionar)

        btn.setOnClickListener {
            presenter.salvar(ticker(), nome(), this)
        };

        val spinnerTicker = root.findViewById<AutoCompleteTextView>(R.id.spinnerAtivo)
        val layoutTicker = root.findViewById<TextInputLayout>(R.id.layoutSpinnerAtivo)
        spinnerTicker.addTextChangedListener(UiUtils.createClearInputErrorMessageListener(layoutTicker))

        val editNomeAtivo = root.findViewById<TextInputEditText>(R.id.editNome)
        val layoutEditNomeAtivo = root.findViewById<TextInputLayout>(R.id.layoutEditNome)
        editNomeAtivo.addTextChangedListener(UiUtils.createClearInputErrorMessageListener(layoutEditNomeAtivo))

        return root
    }

    private fun tickersListAdapter(): ArrayAdapter<String> {
        val elements = Ticker.values().map { ticker -> ticker.name }.sorted()
        val adapter = ArrayAdapter<String>(
            this.fragmentContext,
            R.layout.moeda_spinner_item,
            R.id.txtViewNomeMoeda,
            elements
        )
        return adapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }

    fun ticker(): String {
        return editTicker()?.text?.toString()!!
    }

    fun nome(): String {
        return editNomeAtivo()?.text?.toString()!!
    }

    override fun onErrorAtivoJaCadastrado(msg: String) {
        UiUtils.showInputTextError(editTicker(), layoutEditTicker(), msg)
    }

    override fun onErrorTickerInvalido(msg: String) {
        UiUtils.showInputTextError(editTicker(), layoutEditTicker(), msg)
    }

    override fun onErrorNomeInvalido(msg: String) {
        UiUtils.showInputTextError(editNomeAtivo(), layoutEditNomeAtivo(), msg)
    }

    override fun onSuccess(msg: String) {
        UiUtils.message(activity as ComponentActivity, msg )
        UiUtils.showFragment(fragmentManager, R.id.main_container, ListagemAtivosFragment())
    }

    private fun layoutEditNomeAtivo() = view?.findViewById<TextInputLayout>(R.id.layoutEditNome)

    private fun editNomeAtivo() = view?.findViewById<TextInputEditText>(R.id.editNome)

    private fun layoutEditTicker() = view?.findViewById<TextInputLayout>(R.id.layoutSpinnerAtivo)

    private fun editTicker(): AutoCompleteTextView? {
        return view?.findViewById(R.id.spinnerAtivo)
    }



}
