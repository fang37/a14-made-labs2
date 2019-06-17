package com.dicoding.picodiploma.myrecyclerview.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.myrecyclerview.R
import com.dicoding.picodiploma.myrecyclerview.model.Hero
import java.util.*

class CardViewHeroAdapter(private val listHeroes: ArrayList<Hero>) : RecyclerView.Adapter<CardViewHeroAdapter.CardViewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_heroes, parent, false)
        return CardViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listHeroes.size
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val p = listHeroes[position]

        Glide.with(holder.itemView.context)
                .load(p.photo)
                .apply(RequestOptions().override(350, 550))
                .into(holder.imgPhoto)

        holder.tvName.text = p.name
        holder.tvFrom.text = p.from

        holder.btnFavorite.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Favorite " + listHeroes[position].name, Toast.LENGTH_SHORT).show()
        }

        holder.btnShare.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Share " + listHeroes[position].name, Toast.LENGTH_SHORT).show()
        }
    }

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvFrom: TextView = itemView.findViewById(R.id.tv_item_from)
        var btnFavorite: Button = itemView.findViewById(R.id.btn_set_favorite)
        var btnShare: Button = itemView.findViewById(R.id.btn_set_share)
    }
}