package com.example.feedbackmessages

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.view.Gravity
import android.widget.Toast
import com.example.feedbackmessages.databinding.ActivityMainBinding
import com.google.android.material.snackbar.BaseTransientBottomBar.*
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SnackBar
        binding.snackBar.setOnClickListener {
            Snackbar.make(binding.view,"This is Normal SnackBar",LENGTH_INDEFINITE).show()
        }
        binding.actionSnackBar.setOnClickListener {
            Snackbar.make(binding.view,"This is SnackBar with action",LENGTH_INDEFINITE).apply {
                this.setAction("Cancel") {
                    Snackbar.make(binding.view, "Cancelled", LENGTH_SHORT).show()
                }
                this.show()
            }
        }
        binding.customSnackBar.setOnClickListener {
            Snackbar.make(binding.view,"This is Custom SnackBar",LENGTH_INDEFINITE).apply {
                this.setAction("Cancel"){
                    Snackbar.make(binding.view,"Cancelled",LENGTH_LONG).apply {
                        this.setTextColor(Color.GREEN)
                        this.show()
                    }
                }
                this.setActionTextColor(Color.RED)
                this.setTextColor(Color.GREEN)
                this.show()
            }
        }

        // Toast
        binding.normalToast.setOnClickListener {
            Toast.makeText(this, "Normal Toast", Toast.LENGTH_LONG).show()
        }
        binding.customToast.setOnClickListener {
            Toast(this).showCustomToast()
        }

        // Dialogue
        binding.alertDialogue.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            //set title for alert dialog
            builder.setTitle("Sample Alert Dialogue")
            //set message for alert dialog
            builder.setMessage("Do you see the Alert")
            builder.setIcon(R.drawable.ic_baseline_add_alert_24)

            //performing positive action
            builder.setPositiveButton("Yes"){ _, _ ->
                Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_LONG).show()
            }
            //performing cancel action
            builder.setNeutralButton("Cancel"){ _, _ ->
                Toast.makeText(applicationContext,"clicked cancel\n operation cancel",Toast.LENGTH_LONG).show()
            }
            //performing negative action
            builder.setNegativeButton("No"){ _, _ ->
                Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
        binding.datePicker.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(this, null, year, month,day)
            datePickerDialog.show()
        }
        binding.timePicker.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(this, null, hour, minute,
                DateFormat.is24HourFormat(this))
            timePickerDialog.show()
        }

    }

    private fun Toast.showCustomToast(){
        val layout = layoutInflater.inflate(R.layout.toast_layout,findViewById(R.id.toastLayout))
        this.apply {
            // Since Android 11, custom toasts/ toast modifications are deprecated
            view = layout
            setGravity(Gravity.CENTER,0,0)
            duration = Toast.LENGTH_LONG
            show()
        }
    }

}

