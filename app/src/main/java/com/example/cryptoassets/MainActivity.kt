package com.example.cryptoassets

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cryptoassets.core.model.entidade.TipoTransacao
import com.example.cryptoassets.fragment.AdicaoTransacaoFragment
import com.example.cryptoassets.fragment.ListagemAtivosFragment
import com.example.cryptoassets.fragment.ListagemTransacoesFragment
import com.example.cryptoassets.fragment.ListagemAtivosCarteiraFragment
import com.example.cryptoassets.ui.util.UiUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottom_navigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            val id = item.itemId
            val fragment = when (id) {
                R.id.pageHome -> ListagemAtivosCarteiraFragment()
                R.id.pageTransactions -> ListagemTransacoesFragment()
                R.id.pageAssets -> ListagemAtivosFragment()
                else -> ListagemAtivosCarteiraFragment()
            }
            showFragment(fragment)
            true

        }

        bottom_navigation.selectedItemId = R.id.pageHome

        configureFloatActionButton(findViewById(R.id.buyFab), TipoTransacao.COMPRA)
        configureFloatActionButton(findViewById(R.id.sellFab), TipoTransacao.VENDA)

    }

    private fun showFragment(fragment: Fragment) {
        UiUtils.showFragment(supportFragmentManager, R.id.main_container,  fragment)
    }

    private fun configureFloatActionButton(fab: View, tipoOperacao: TipoTransacao) {
        fab.setOnClickListener {
            val fragment = AdicaoTransacaoFragment()
            val bundle = Bundle()
            bundle.putInt("tipoTransacao", tipoOperacao.codigo)
            fragment.arguments = bundle
            showFragment(fragment)

        }
    }

    override fun onBackPressed() {
        val fragments = supportFragmentManager.backStackEntryCount
        if (fragments == 1) {
            finish()
            return
        }
        super.onBackPressed()
    }



}
