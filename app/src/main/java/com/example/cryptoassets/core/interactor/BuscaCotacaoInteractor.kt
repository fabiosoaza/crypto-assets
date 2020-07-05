package com.example.cryptoassets.core.interactor

import android.content.Context
import android.os.AsyncTask
import com.example.cryptoassets.core.interactor.listener.OnBuscarCotacao
import com.example.cryptoassets.core.repository.CotacaoRepository
import com.example.cryptoassets.core.task.BuscaCotacaoAsyncTask

class BuscaCotacaoInteractor(
    private val context: Context,
    private val cotacaoRepository: CotacaoRepository
) {

    private var asyncTaskBuscaCotacao: BuscaCotacaoAsyncTask?= null

    fun buscarCotacoes(listener: OnBuscarCotacao) {
        if (asyncTaskBuscaCotacao?.status != AsyncTask.Status.RUNNING) {
            asyncTaskBuscaCotacao =
                BuscaCotacaoAsyncTask(
                    context,
                    cotacaoRepository,
                    listener
                )
            asyncTaskBuscaCotacao?.execute()
        }
    }

}