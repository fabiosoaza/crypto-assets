package com.example.cryptoassets.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import com.example.cryptoassets.R
import com.example.cryptoassets.context.ApplicationComponentsContext
import com.example.cryptoassets.core.interactor.listener.OnBuscarCotacao
import com.example.cryptoassets.core.model.entidade.AtivoCarteira
import com.example.cryptoassets.core.model.entidade.Cotacao
import com.example.cryptoassets.ui.adapter.ListAtivosCarteiraAdapter
import com.example.cryptoassets.ui.component.ProgressBarComponent
import com.example.cryptoassets.ui.view.listview.ListAtivosCarteiraView
import com.example.cryptoassets.ui.view.listview.impl.builder.ListAtivosCarteiraViewBuilder
import com.example.cryptoassets.util.UiUtils


class ListagemAtivosCarteiraFragment : OnBuscarCotacao, ListItemsFragmentBase() {

    private var progressBarComponent: ProgressBarComponent?=null
    private var listAdapter = ListAtivosCarteiraAdapter(
            ListAtivosCarteiraViewBuilder()
                .build()
        )

    override fun onPreCreateView(){
        //Dependencia da chamada do método da classe mãe
        super.onPreCreateView()
        progressBarComponent = ProgressBarComponent(activity as ComponentActivity, R.id.container, R.id.progressOverlay )
    }

    override fun recyclerViewId() = R.id.rvListagemMoedas

    override fun fragmentLayoutId() = R.layout.fragment_resumo

    override fun listAdapter() = listAdapter

    override fun dataSet(): ListAtivosCarteiraView {
        beanFactory().buscaCotacaoInteractor().buscarCotacoes(this)
        return ListAtivosCarteiraViewBuilder()
            .build()
    }

    private fun ativos(): MutableList<AtivoCarteira> {
        return beanFactory().ativoCarteiraRepository().ativos().toMutableList()
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

    override fun onPostExecute(cotacoes: List<Cotacao>) {
        progressBarComponent?.hide()
        val view = ListAtivosCarteiraViewBuilder()
            .cotacoes(cotacoes.toMutableList())
            .ativos(ativos())
            .build()
           updateAdapter(view)
    }

}

