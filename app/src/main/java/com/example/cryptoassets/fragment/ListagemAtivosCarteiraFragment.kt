package com.example.cryptoassets.fragment

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import com.example.cryptoassets.R
import com.example.cryptoassets.core.domain.AtivoCarteira
import com.example.cryptoassets.core.domain.Cotacao
import com.example.cryptoassets.ui.BuscaCotacaoAsyncTask
import com.example.cryptoassets.ui.adapter.ListAtivosCarteiraAdapter
import com.example.cryptoassets.ui.component.ProgressBarComponent
import com.example.cryptoassets.ui.interactor.OnBuscaCotacao
import com.example.cryptoassets.ui.util.UiUtils
import com.example.cryptoassets.ui.view.ListAtivosCarteiraView
import com.example.cryptoassets.ui.view.impl.builder.ListAtivosCarteiraViewBuilder


class ListagemAtivosCarteiraFragment : OnBuscaCotacao, ListItemsFragmentBase() {

    private var asyncTaskBuscaCotacao: BuscaCotacaoAsyncTask?= null
    private var progressBarComponent: ProgressBarComponent?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Dependencia Temporal: a barra de status precisa ser instanciada antes de chamar o método super
        progressBarComponent = ProgressBarComponent(activity as ComponentActivity, R.id.container, R.id.progressOverlay )
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private var listAdapter =
        ListAtivosCarteiraAdapter(
            ListAtivosCarteiraViewBuilder()
                .build()
        )


    override fun recyclerViewId() = R.id.rvListagemMoedas

    override fun fragmentLayoutId() = R.layout.fragment_resumo

    override fun listAdapter() = listAdapter

    override fun dataSet(): ListAtivosCarteiraView {
        carregarCotacoes()
        return ListAtivosCarteiraViewBuilder()
            .build()
    }



    private fun carregarCotacoes() {
        if (asyncTaskBuscaCotacao?.status != AsyncTask.Status.RUNNING) {
            asyncTaskBuscaCotacao =
                BuscaCotacaoAsyncTask(fragmentContext(), beanFactory().cotacaoRepository(), this)
            asyncTaskBuscaCotacao?.execute()
        }
    }

    private fun ativos(): MutableList<AtivoCarteira> {
        return beanFactory().ativoCarteiraRepository().ativos().toMutableList()
    }

    override fun onErrorConnection(msg: String) {
        UiUtils.message(activity as ComponentActivity, msg)
    }

    override fun onErrorBuscaCotacao(msg: String) {
        UiUtils.message(activity as ComponentActivity, msg)
    }

    override fun onPreExecute() {
        progressBarComponent?.show()
    }

    override fun onPostExecute(cotacoes: List<Cotacao>) {
        progressBarComponent?.hide()
        val view = ListAtivosCarteiraViewBuilder()
            .cotacoes(cotacoes.toMutableList())
            .ativos(ativos())
            .build()
           updateAdapter(view)
    }

}

