package com.test.app.movies.ui.movielist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.app.movies.data.model.Result
import com.test.app.movies.data.repo.DefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repos :DefaultRepository) : ViewModel() {


    fun getListOfMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repos.getListOfMovies(0)
            if (response is Result.Success) {
                Log.d("movie", " count = " + response.data?.size)
            } else if (response is Result.Error) {
                Log.d("movie", " Error = " + response.errorMessage)
            }
        }
    }
}