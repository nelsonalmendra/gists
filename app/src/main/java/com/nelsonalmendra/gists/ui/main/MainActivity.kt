package com.nelsonalmendra.gists.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nelsonalmendra.gists.R
import com.nelsonalmendra.gists.data.database.Gist
import com.nelsonalmendra.gists.ui.detail.GistFragmentDetail

class MainActivity : AppCompatActivity(), GistsListFragment.IGistListFragment {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_out_right, R.anim.slide_in_right)
                    .add(R.id.root_layout, GistsListFragment.newInstance(), "gistsList")
                    .commit()
        }
    }

    override fun onGistClick(gist: Gist) {
        val detailsFragment = GistFragmentDetail.newInstance()
        val arguments = Bundle()
        arguments.putSerializable(GistFragmentDetail.GIST, gist)
        detailsFragment.arguments = arguments
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_out_right, R.anim.slide_in_right)
                .replace(R.id.root_layout, detailsFragment, "gistDetails")
                .addToBackStack(null)
                .commit()
    }
}
