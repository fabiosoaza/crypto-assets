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
import com.example.cryptoassets.context.ApplicationComponentsContext
import com.example.cryptoassets.ui.adapter.BaseListItemsAdapter
import com.example.cryptoassets.util.UiUtils
import com.example.cryptoassets.ui.view.listview.AdaptableListItemsView

abstract class ListItemsFragmentBase : Fragment() {

    private lateinit var fragmentContext: Context

    private lateinit var beanFactory : ApplicationComponentsContext

    fun beanFactory() = beanFactory

    abstract fun recyclerViewId() : Int

    abstract fun fragmentLayoutId() : Int

    fun fragmentContext():Context{
        return fragmentContext
    }

    open fun onPreCreateView(){
        beanFactory = ApplicationComponentsContext(fragmentContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onPreCreateView()
        updateAdapter(dataSet())
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

    open fun updateAdapter(dataSet: AdaptableListItemsView) {
        listAdapter().update(dataSet)
        listAdapter().notifyDataSetChanged()
    }


}