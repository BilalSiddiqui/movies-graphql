package com.test.app.movies.ui.addmovie

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.api.Input
import com.test.app.movies.R
import com.test.app.movies.data.model.Movie
import com.test.app.movies.data.model.Result
import com.test.app.movies.data.model.type.CreateMovieFieldsInput
import com.test.app.movies.data.repo.DefaultRepository
import com.test.app.movies.utils.Event
import com.test.app.movies.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMovieViewModel @Inject constructor(val repo: DefaultRepository) : ViewModel() {

    var isLoading = ObservableField<Boolean>(false)
    var errorMesssage = MutableLiveData<Event<String>>()
    var title = ObservableField<String>("")
    var titleError = ObservableField<String>("")
    var seasons = ObservableField<String>("")
    var releaseDate = ObservableField<String>("")
    var addMovieSuccess=MutableLiveData<Movie>()

    fun saveTvShow(view: View) {
        isLoading.set(true)
        if (validate(view)) {

            val seasonsInput = if (seasons.get().isNullOrEmpty()) Input.absent() else Input.optional(seasons.get()?.toDouble())
            val releaseDateInput:Input<Any> = if (releaseDate.get().isNullOrEmpty()) Input.absent() else Input.optional(Utils.changeServerDateToLocalDate(Utils.changeToServerTimeFormat(releaseDate.get())))
            val inputData = CreateMovieFieldsInput(title=title.get()!!,seasons= seasonsInput, releaseDate =  releaseDateInput)
            viewModelScope.launch(Dispatchers.IO) {
                val movieSavedResponse=repo.addMovie(inputData)
                isLoading.set(false)
                if(movieSavedResponse is Result.Success){
                    addMovieSuccess.postValue(movieSavedResponse.data!!)
                }else if(movieSavedResponse is Result.Error){
                    errorMesssage.postValue(Event(movieSavedResponse.errorMessage!!))
                }
            }
        }
    }

    fun validate(view: View): Boolean {
        if (title.get()?.isEmpty()!!) {
            titleError.set(view.context.getString(R.string.fill_mandatory))
            return false
        } else {
            titleError.set(null)
            return true
        }

    }

}