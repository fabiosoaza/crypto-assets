package com.example.cryptoassets.ui

import android.content.Context
import android.os.AsyncTask
import com.example.cryptoassets.R
import com.example.cryptoassets.core.domain.Cotacao
import com.example.cryptoassets.core.exception.ConexaoDesabilitadaException
import com.example.cryptoassets.core.exception.FalhaConexaoException
import com.example.cryptoassets.core.repository.CotacaoRepository
import com.example.cryptoassets.ui.interactor.OnBuscaCotacao
import com.example.cryptoassets.util.ResourceUtil
import java.util.*

class BuscaCotacaoAsyncTask(private val context: Context, private val cotacaoRepository: CotacaoRepository, private val listener: OnBuscaCotacao) : AsyncTask<Void, Void, List<Cotacao>>() {

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
            listener.onErrorConnection(ResourceUtil.getString(context, R.string.noInternetPermissionWarning)!!)
            return Collections.emptyList<Cotacao>()
        }
        catch(fc: FalhaConexaoException){
            listener.onErrorBuscaCotacao(ResourceUtil.getString(context, R.string.cantConnectToHost)!!)
            return Collections.emptyList<Cotacao>()
        }
    }


}