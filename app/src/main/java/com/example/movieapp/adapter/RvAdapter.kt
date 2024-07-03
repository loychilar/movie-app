package com.example.movieapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.EditActivity
import com.example.movieapp.R
import com.example.movieapp.models.Movie
import com.example.movieapp.models.Save
import com.google.android.material.button.MaterialButton

class RvAdapter(val context: Context, val list: ArrayList<Movie>, val rvClick: RvClick) :
    RecyclerView.Adapter<RvAdapter.VH>() {
    inner class VH(private var itemRv: View) : RecyclerView.ViewHolder(itemRv) {
        fun onBind(movie: Movie) {
            itemRv.setOnClickListener {

                rvClick.onClick(movie)
            }
            itemRv.findViewById<TextView>(R.id.movieDate).text = movie.date
            itemRv.findViewById<TextView>(R.id.movieName).text = movie.name
            itemRv.findViewById<TextView>(R.id.movieAuthors).text = movie.author

            itemRv.findViewById<MaterialButton>(R.id.btnEdit).setOnClickListener {
                val intent=Intent(context,EditActivity::class.java)
                Save.movie=Movie(movie.name,movie.author,movie.about,movie.date)
                context.startActivity(intent)
            }
            itemRv.findViewById<MaterialButton>(R.id.btnDelete).setOnClickListener {
                list.remove(movie)
                notifyDataSetChanged()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface RvClick {
        fun onClick(movie: Movie)
    }

}