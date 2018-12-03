package com.example.liss.exam

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var toastButton: Button? = null
    private var countButton: Button? = null
    private var zeroButton: Button? = null
    private var counter: TextView? = null

    private var currentCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        counter = findViewById(R.id.counter)
        toastButton = findViewById(R.id.toast_button)
        countButton = findViewById(R.id.count_button)
        zeroButton = findViewById(R.id.zero_button)
        initCounter()
    }

    private fun initCounter() {
        val settings : SharedPreferences = getSharedPreferences("exam", MODE_PRIVATE)
        val prefEditor = settings.edit()
        if (!settings.contains("initialCounter")) {
            prefEditor.putString("initialCounter", currentCount.toString())
            prefEditor.apply()
        }
        counter!!.text = settings.getString("initialCounter", "Default")

        toastButton!!.setOnClickListener { toast() }
        countButton!!.setOnClickListener {
            currentCount++
            updateCounter()
        }
        zeroButton!!.setOnClickListener {
            currentCount = 0
            updateCounter()
        }
    }


    private fun updateCounter() {
        counter!!.text = currentCount.toString()
    }

    private fun toast() {
        Toast.makeText(this, "Counter: $currentCount", Toast.LENGTH_SHORT).show()
    }
}
