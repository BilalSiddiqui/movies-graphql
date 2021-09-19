package com.test.app.movies.ui.addmovie

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.test.app.movies.R
import com.test.app.movies.ui.home.HomeViewModel

class AddMovieFragment : Fragment() {

    companion object {
        fun newInstance() = AddMovieFragment()
    }

    private val viewModel: AddMovieViewModel by   viewModels<AddMovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_movie_fragment, container, false)
    }


}