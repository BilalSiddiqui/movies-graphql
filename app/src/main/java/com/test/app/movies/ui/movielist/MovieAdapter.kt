package com.test.app.movies.ui.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.app.movies.R
import com.test.app.movies.data.model.Movie
import com.test.app.movies.databinding.ItemMovieBinding

/**
 * Movie list adapter class
 */
class MovieAdapter : ListAdapter<Movie, MovieAdapter.ItemViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.ItemViewHolder {
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie, parent, false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder internal constructor(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

