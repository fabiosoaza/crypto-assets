package com.example.cryptoassets

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cryptoassets.fragment.ListagemAtivosCarteiraFragment
import com.example.cryptoassets.fragment.ListagemAtivosFragment
import com.example.cryptoassets.fragment.ListagemTransacoesFragment
import com.example.cryptoassets.util.UiUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            val fragment = when (item.itemId) {
                R.id.pageHome -> ListagemAtivosCarteiraFragment()
                R.id.pageTransactions -> ListagemTransacoesFragment()
                R.id.pageAssets -> ListagemAtivosFragment()
                else -> ListagemAtivosCarteiraFragment()
            }
            showFragment(fragment)
            true

        }

        bottom_navigation.selectedItemId = R.id.pageHome


    }

    private fun showFragment(fragment: Fragment) {
        UiUtils.showFragment(supportFragmentManager, R.id.main_container,  fragment)
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
