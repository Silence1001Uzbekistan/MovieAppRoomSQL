package com.example.movieapproomsql.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapproomsql.Models.Movie
import com.example.movieapproomsql.databinding.RvItemBinding

class MovieAdapter(var list: ArrayList<Movie>, var onMyItemClickListener: OnMyItemClickListener) :
    RecyclerView.Adapter<MovieAdapter.Vh>() {

    inner class Vh(var rvItemBinding: RvItemBinding) : RecyclerView.ViewHolder(rvItemBinding.root) {

        fun onBind(movie: Movie, position: Int) {

            rvItemBinding.nameRv.text = movie.movieName
            rvItemBinding.authorsRv.text = movie.movieAuthors
            rvItemBinding.dateRv.text = movie.movieDate

            rvItemBinding.editRv.setOnClickListener {

                onMyItemClickListener.itemCLickChange(movie, position)

            }

            rvItemBinding.deleteRv.setOnClickListener {

                onMyItemClickListener.itemClickDelete(movie, position)

            }

            rvItemBinding.root.setOnClickListener {

                onMyItemClickListener.itemClick(movie)

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {

        return Vh(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: Vh, position: Int) {

        holder.onBind(list[position], position)

    }

    override fun getItemCount(): Int {

        return list.size

    }


    interface OnMyItemClickListener {

        fun itemClick(movie: Movie)
        fun itemCLickChange(movie: Movie, position: Int)
        fun itemClickDelete(movie: Movie, position: Int)

    }

}