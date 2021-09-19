package com.target.app.movies.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.target.app.movies.R
import com.target.app.movies.ui.movielist.MovieListFragment

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private  val viewModel: HomeViewModel by  viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.add_movie).setOnClickListener {
            val action = HomeFragmentDirections.actionHomeDestToAddMovieFragment()
            findNavController().navigate(action)
        }
        view.findViewById<View>(R.id.list_movie).setOnClickListener {
            val action = HomeFragmentDirections.actionHomeDestToMovieListFragment()
            findNavController().navigate(action)
        }
    }

}