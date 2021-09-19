package com.test.app.movies.ui.movielist

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.app.movies.data.model.Result
import com.test.app.movies.data.repo.DefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repos :DefaultRepository) : ViewModel() {

val movieAdapter=MovieAdapter()
    var isLoading=ObservableField<Boolean>()
    var isEmpty=ObservableField<Boolean>(false)
    var errorMesssage=ObservableField<String>("")

    fun getListOfMovies() {
        isLoading.set(true)
        viewModelScope.launch(Dispatchers.IO) {

            val response = repos.getListOfMovies(0)
            isLoading.set(false)
            if (response is Result.Success&&response.data!=null) {

                if(response.data.isEmpty()){
                    //Only show empty message if empty list received from server and no movies are already showing
                    if(movieAdapter.currentList.isEmpty()) {
                        isEmpty.set(true)
                    }
                }else{
                    //In case list is not empty, show movies
                    isEmpty.set(false)
                    withContext(Dispatchers.Main) {
                        movieAdapter.submitList(response.data)
                    }
                }
                errorMesssage.set("")
                Timber.d("getListOfMovies: count = " + response.data?.size)
            } else if (response is Result.Error) {
                errorMesssage.set(response.errorMessage)
                Timber.d("getListOfMovies: Error = " + response.errorMessage)
            }
        }
    }
}