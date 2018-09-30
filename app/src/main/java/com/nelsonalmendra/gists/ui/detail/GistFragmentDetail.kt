package com.nelsonalmendra.gists.ui.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nelsonalmendra.gists.R
import com.nelsonalmendra.gists.data.database.Gist
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_gist_detail.view.*

class GistFragmentDetail : Fragment() {

    companion object {

        const val GIST = "gist"

        fun newInstance(): GistFragmentDetail {
            return GistFragmentDetail()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val gist = arguments?.getParcelable(GIST) as Gist
        val view: View = inflater.inflate(R.layout.fragment_gist_detail, container, false)
        Picasso.get()
                .load(gist.owner.avatar_url)
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .resize(200, 200)
                .into(view.avatar)

        view.description.text = gist.description
        view.updateAt.text = gist.updated_at
        return view
    }
}