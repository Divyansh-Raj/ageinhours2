package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate :TextView?=null
    private var tvAgeInMinutes :TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btndatepicker:Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate=findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes=findViewById(R.id.tvAgeInMinutes)

        btndatepicker.setOnClickListener {
           clickDatePicker()

        }

    }
    fun clickDatePicker(){
        val mycalender=Calendar.getInstance()
        val year=mycalender.get(Calendar.YEAR)
        val month= mycalender.get(Calendar.MONTH)
        val day=mycalender.get(Calendar.DAY_OF_MONTH)

        val dpd= DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(
                    this,
                    "year is $selectedYear, month is ${selectedMonth + 1}, day is $selectedDayOfMonth",
                    Toast.LENGTH_LONG
                ).show()
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate?.text = selectedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val dateInMin = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentDate?.let {
                        val currentDateInMin = currentDate.time / 60000
                        val difference = currentDateInMin - dateInMin
                        tvAgeInMinutes?.text = difference.toString()
                    }
                }
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis()- 86400000
        dpd.show()




    }
}