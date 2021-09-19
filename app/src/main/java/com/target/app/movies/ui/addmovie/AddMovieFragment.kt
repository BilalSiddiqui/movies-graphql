package com.target.app.movies.ui.addmovie

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.target.app.movies.R

class AddMovieFragment : Fragment() {

    companion object {
        fun newInstance() = AddMovieFragment()
    }

    private lateinit var viewModel: AddMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddMovieViewModel::class.java)
        // TODO: Use the ViewModel
    }

}