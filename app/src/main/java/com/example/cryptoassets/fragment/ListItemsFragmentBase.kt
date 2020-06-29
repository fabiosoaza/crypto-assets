package com.example.cryptoassets.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoassets.R
import com.example.cryptoassets.configuration.BeansFactory
import com.example.cryptoassets.ui.adapter.BaseListItemsAdapter
import com.example.cryptoassets.ui.util.UiUtils
import com.example.cryptoassets.ui.view.AdaptableListItemsView

abstract class ListItemsFragmentBase : Fragment() {

    private lateinit var fragmentContext: Context

    private lateinit var beanFactory : BeansFactory

    fun beanFactory() = beanFactory

    abstract fun recyclerViewId() : Int

    abstract fun fragmentLayoutId() : Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        beanFactory = BeansFactory(fragmentContext)
        updateAdapter()
        val root = inflater.inflate(fragmentLayoutId(), container, false)
        val recycleView = root.findViewById<RecyclerView>(recyclerViewId())
        initRecyclerView(this.fragmentContext, recycleView)
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }

    fun showFragment(fragment: Fragment) {
        UiUtils.showFragment(fragmentManager, R.id.main_container,  fragment)
    }

    abstract  fun listAdapter() : BaseListItemsAdapter

    abstract fun dataSet(): AdaptableListItemsView

    private fun initRecyclerView(
        context: Context?,
        recycleView: RecyclerView
    ) {
        val layoutManager = LinearLayoutManager(context)
        recycleView.adapter = listAdapter()
        recycleView.layoutManager = layoutManager
    }

    open fun updateAdapter() {
        listAdapter().update(dataSet())
        listAdapter().notifyDataSetChanged()
    }

}