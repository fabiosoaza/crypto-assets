package com.example.cryptoassets.core.task

import android.content.Context
import android.os.AsyncTask
import com.example.cryptoassets.R
import com.example.cryptoassets.core.model.entidade.Cotacao
import com.example.cryptoassets.core.exception.ConexaoDesabilitadaException
import com.example.cryptoassets.core.exception.FalhaConexaoException
import com.example.cryptoassets.core.repository.CotacaoRepository
import com.example.cryptoassets.core.interactor.listener.OnBuscarCotacao
import com.example.cryptoassets.util.ResourceUtils
import java.util.*

class BuscaCotacaoAsyncTask(private val context: Context, private val cotacaoRepository: CotacaoRepository, private val listener: OnBuscarCotacao) : AsyncTask<Void, Void, List<Cotacao>>() {

    override fun onPreExecute() {
        super.onPreExecute()
        listener.onPreExecute()
    }

    override fun onPostExecute(result: List<Cotacao>) {
        super.onPostExecute(result)
        listener.onPostExecute(result)

    }


    override fun doInBackground(vararg params: Void?): List<Cotacao> {
        return try{
            cotacaoRepository.cotacoes().toMutableList()
        }
        catch(cd: ConexaoDesabilitadaException){
            listener.onErrorConnection(ResourceUtils.getString(context, R.string.noInternetPermissionWarning)!!)
            return Collections.emptyList<Cotacao>()
        }
        catch(fc: FalhaConexaoException){
            listener.onErrorBuscarCotacao(ResourceUtils.getString(context, R.string.cantConnectToHost)!!)
            return Collections.emptyList<Cotacao>()
        }
    }


}