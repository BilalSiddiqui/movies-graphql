package com.test.app.movies.utils

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
interface DateSelectionListener{
    fun onDateSelected(date:String)
}

   lateinit var dateSelectionListener:DateSelectionListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(parentFragment is DateSelectionListener){
            dateSelectionListener= parentFragment as DateSelectionListener
        }else if(context is DateSelectionListener){
            dateSelectionListener= context as DateSelectionListener
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(requireActivity(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        dateSelectionListener.onDateSelected("$year/$month/$day")
    }
}