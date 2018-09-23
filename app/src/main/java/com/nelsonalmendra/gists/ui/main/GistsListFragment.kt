package com.nelsonalmendra.gists.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nelsonalmendra.gists.R
import com.nelsonalmendra.gists.data.database.Gist
import kotlinx.android.synthetic.main.fragment_gists_list.*

class GistsListFragment : Fragment() {

    private lateinit var gistViewModel: GistViewModel
    private lateinit var adapter: GistListAdapter
    private lateinit var listener: IGistListFragment

    companion object {
        fun newInstance(): GistsListFragment {
            return GistsListFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as IGistListFragment
        gistViewModel = ViewModelProviders.of(this).get(GistViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        gistViewModel.allGists.observe(this, Observer { gists ->
            gists?.let { setGists(it) }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_gists_list, container,false)
        val activity = activity
        val recyclerView = view.findViewById(R.id.recyclerview) as RecyclerView
        adapter = GistListAdapter(activity!!.applicationContext) { it -> listener.onGistClick(it) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.setOnRefreshListener { gistViewModel.refreshData() }
    }

    private fun setGists(gists: List<Gist>) {
        adapter.setGists(gists)
        swipeRefreshLayout.isRefreshing = false
    }

    interface IGistListFragment {
        fun onGistClick(gist: Gist)
    }
}