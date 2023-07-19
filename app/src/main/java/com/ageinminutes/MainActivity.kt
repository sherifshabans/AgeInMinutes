package com.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate :TextView ?=null
    private var tvAgeInMinutes :TextView ?=null
    private var tvAgeInMS :TextView ?=null
    private var tvAgeInS :TextView ?=null
    private var tvAgeInH :TextView ?=null
    private var tvAgeInD :TextView ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        tvSelectedDate= findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes= findViewById(R.id.tvAgeInMinutes)
        tvAgeInMS= findViewById(R.id.tvAgeInMS)
        tvAgeInS= findViewById(R.id.tvAgeInS)
        tvAgeInH= findViewById(R.id.tvAgeInH)
        tvAgeInD= findViewById(R.id.tvAgeInD)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }




    }

    private fun clickDatePicker() {
    val myCalendar =Calendar.getInstance()
    val year = myCalendar.get(Calendar.YEAR)
    val month = myCalendar.get(Calendar.MONTH)
    val day = myCalendar.get(Calendar.DAY_OF_MONTH)
    val dpd =  DatePickerDialog(this,
    {view,selectedYear,selectedMonth,selectedDayOfMonth ->
        val selectdDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
        tvSelectedDate?.text = selectdDate

        val sdf= SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
        val theDate=sdf.parse(selectdDate)
        theDate?.let {

            val selectedDateInMinutes=theDate.time/60000
            val selectedDateInH=theDate.time/3600000
            val selectedDateInS=theDate.time/1000
            val selectedDateInMS=theDate.time
            val selectedDateInD=theDate.time/86400000


            val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
            currentDate?.let {

                val currentDateInMinutes=currentDate.time/60000
                val currentDateInH=currentDate.time/3600000
                val currentDateInS=currentDate.time/1000
                val currentDateInMS=currentDate.time
                val currentDateInD=currentDate.time/86400000



                val differenceInMinutes=currentDateInMinutes-selectedDateInMinutes
                val differenceInMS=currentDateInMS-selectedDateInMS
                val differenceInD=currentDateInD-selectedDateInD
                val differenceInS=currentDateInS-selectedDateInS
                val differenceInH=currentDateInH-selectedDateInH

                tvAgeInMinutes?.text=differenceInMinutes.toString()
                tvAgeInMS?.text=differenceInMS.toString()
                tvAgeInH?.text=differenceInH.toString()
                tvAgeInD?.text=differenceInD.toString()
                tvAgeInS?.text=differenceInS.toString()

            }
        }




    },year,month,day
        )
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()

    }
}