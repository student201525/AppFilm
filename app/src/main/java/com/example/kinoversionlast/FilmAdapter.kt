package com.example.kinoversionlast

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import android.content.Context
import com.squareup.picasso.Picasso


class FilmAdapter(val items: Film.List,
                  val context: Context,
                  val onItemClick:(Film) -> Unit): RecyclerView.Adapter<FilmAdapter.ViewHolder>()
{

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) : ViewHolder
    {
        val viewHolder = LayoutInflater.from(parent?.context).inflate(R.layout.film_item,parent,false)

        return ViewHolder(viewHolder)
                .apply {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION)
                {
                    onItemClick(items[position])
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int)
    {
        holder?.textViewName?.text = items[position].name
        holder?.textViewYear?.text = items[position].year
        holder?.textViewCountry?.text = items[position].country
        holder?.textViewGenre?.text = items[position].genre
        Picasso.with(context).load(items[position].picture).into(holder?.imageView)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var textViewName: TextView? = null
        var textViewYear: TextView? = null
        var textViewCountry: TextView? = null
        var textViewGenre: TextView? = null
        var imageView: ImageView? = null

        init
        {
            textViewName  = itemView.findViewById(R.id.filmName)
            textViewYear  = itemView.findViewById(R.id.filmYear)
            textViewCountry  = itemView.findViewById(R.id.filmCountry)
            textViewGenre  = itemView.findViewById(R.id.filmGenre)
            imageView = itemView.findViewById(R.id.filmImage)
        }
    }
}