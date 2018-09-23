package com.nelsonalmendra.gists.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.nelsonalmendra.gists.R
import com.nelsonalmendra.gists.data.database.Gist
import com.squareup.picasso.Picasso

class GistListAdapter internal constructor(context: Context, val clickListener: (Gist) -> Unit) : RecyclerView.Adapter<GistListAdapter.GistViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var gists = emptyList<Gist>()

    inner class GistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val avatar: ImageView = itemView.findViewById(R.id.avatar)
        val gistDescription: TextView = itemView.findViewById(R.id.description)
        val gistUpdatedAt: TextView = itemView.findViewById(R.id.updateAt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return GistViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GistViewHolder, position: Int) {
        val current = gists[position]
        Picasso.get()
                .load(current.owner.avatar_url)
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .resize(200, 200)
                .into(holder.avatar)

        holder.gistDescription.text = current.description
        holder.gistUpdatedAt.text = current.updated_at
        holder.itemView.setOnClickListener { clickListener(current) }
    }

    fun setGists(gists: List<Gist>) {
        this.gists = gists
        notifyDataSetChanged()
    }

    override fun getItemCount() = gists.size
}