package com.aatif.agecalculator2

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvageinminutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btndatepicker: Button = findViewById(R.id.btndatepicker)



        btndatepicker.setOnClickListener {
            clickdatepicker()

        }
    }

   private fun clickdatepicker() {
        var tvSelectedDate: TextView? = null
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        val mycalender = Calendar.getInstance()
        val year = mycalender.get(Calendar.YEAR)
        val month = mycalender.get(Calendar.MONTH)
        val day = mycalender.get(Calendar.DAY_OF_MONTH)

      val dpd =   DatePickerDialog(
          this,
          DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
              Toast.makeText(
                  this,
                  "year was $year, month was ${month + 1}, day was $dayOfMonth",
                  Toast.LENGTH_LONG
              ).show()
              var selecteddate = "$dayOfMonth/${month + 1}/$year"
              val tvageinminutes = findViewById<TextView>(R.id.tvageinminutes)
              tvSelectedDate?.text = selecteddate
              val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
              val theDate = sdf.parse(selecteddate)
            theDate?.let {
                val selecteddateinminutes = theDate.time / 60000

                val currentdate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentdate?.let {   val currentdateinminutes = currentdate.time / 60000

                        val differenceinminutes = currentdateinminutes - selecteddateinminutes
                        tvageinminutes?.text = differenceinminutes.toString() }



            }




          },
          year, month, day

      )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}