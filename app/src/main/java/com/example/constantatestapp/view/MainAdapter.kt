package com.example.constantatestapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.constantatestapp.R
import com.example.constantatestapp.model.data.Items

class MainAdapter(
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<MyViewHolder>() {

    private var list = listOf<Items>()

    fun update(newList: List<Items>) {
        this.list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false),
        onItemClickListener
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}

class MyViewHolder(view: View, private val onItemClickListener: OnItemClickListener) :
    RecyclerView.ViewHolder(view) {
    fun bind(items: Items) {
        with(itemView) {
            findViewById<TextView>(R.id.title).text = items.title
            findViewById<TextView>(R.id.releaseYear).text = "(${items.releaseYear})"
            findViewById<TextView>(R.id.directorName).text = items.convertDirectorName()

            findViewById<TextView>(R.id.actorName).text = items.removeDuplicateElements()

            setOnClickListener { onItemClickListener.itemClick(items.title) }
        }
    }
}

interface OnItemClickListener {
    fun itemClick(title: String)
}