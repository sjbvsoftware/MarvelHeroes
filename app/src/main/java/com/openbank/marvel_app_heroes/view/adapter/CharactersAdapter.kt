package com.openbank.marvel_app_heroes.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.openbank.marvel_app_heroes.R
import com.openbank.marvel_app_heroes.view.model.Characters

class SuperheroAdapter: RecyclerView.Adapter<SuperheroAdapter.ViewHolder>() {
    private lateinit var itemList: List<Characters>
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_superhero, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (::itemList.isInitialized) itemList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    fun updateData(list: List<Characters>) {
        itemList = list;
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind() {
            val item = itemList.get(adapterPosition)

           /* itemView.tvName.text = item.name
            itemView.tvSuperheroName.text = item.superheroName

            Glide.with(context)
                .load(item.photo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .circleCrop()
                .into(itemView.ivPhoto)*/
        }
    }

}