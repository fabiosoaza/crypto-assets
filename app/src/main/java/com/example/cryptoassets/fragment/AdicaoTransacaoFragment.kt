package com.example.cryptoassets.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.cryptoassets.R
import com.example.cryptoassets.configuration.BeansFactory
import com.example.cryptoassets.core.domain.TipoTransacao


class AdicaoTransacaoFragment : Fragment() {

    private lateinit var fragmentContext : Context

    private lateinit var beanFactory  : BeansFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        beanFactory  = BeansFactory(fragmentContext)
        val root = inflater.inflate(R.layout.fragment_adicao_transacao, container, false)
        beanFactory.ativoRepository().ativos()
        val elements =  beanFactory.ativoRepository().ativos().map { ativo -> ativo.ticker.name }.sorted()
        val adapter = ArrayAdapter<String>(this.fragmentContext, R.layout.moeda_spinner_item, R.id.txtViewNomeMoeda, elements)
        val spinner = root.findViewById<AutoCompleteTextView>(R.id.spinnerAtivo)
        spinner.setAdapter(adapter)
        val txtOperacao = root.findViewById<TextView>(R.id.textViewTituloForm)
        txtOperacao.text = titulo()

        val btn = root.findViewById<Button>(R.id.btnAdicionar)
        btn.text = titulo()
        btn.contentDescription = contentDecription()
        btn.setOnClickListener {
            fragmentManager?.beginTransaction()?.remove(this)?.commit();
            fragmentManager?.popBackStack()
        };

        return root
    }

    private fun contentDecription() : String{
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

}
