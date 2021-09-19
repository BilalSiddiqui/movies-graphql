package com.test.app.movies.ui.addmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.test.app.movies.databinding.AddMovieFragmentBinding
import com.test.app.movies.utils.DatePickerFragment
import com.test.app.movies.utils.setupStringSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddMovieFragment : Fragment(), DatePickerFragment.DateSelectionListener {

    companion object {
        fun newInstance() = AddMovieFragment()
    }

    private lateinit var binding: AddMovieFragmentBinding
    private val viewModel: AddMovieViewModel by viewModels<AddMovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddMovieFragmentBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.root.setupStringSnackbar(this, viewModel.errorMesssage, Snackbar.LENGTH_SHORT)
        setUpDatePicker()
        observerSuccess()
        return binding.root
    }

    private fun observerSuccess() {
        viewModel.addMovieSuccess.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            findNavController().popBackStack()
        })
    }

    private fun setUpDatePicker() {
        binding.txtInputReleaseDate.keyListener = null
        binding.txtInputReleaseDate.setOnClickListener { showDatePickerDialog() }
        binding.txtInputReleaseDate.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(p0: View?, hasFoucs: Boolean) {
                if (hasFoucs) {
                    showDatePickerDialog()
                }
            }

        }

    }

    fun showDatePickerDialog() {
        val newFragment = DatePickerFragment()
        newFragment.show(childFragmentManager, "datePicker")
    }

    override fun onDateSelected(date: String) {
        viewModel.releaseDate.set("$date")
    }
}