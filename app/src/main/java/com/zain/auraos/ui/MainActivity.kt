package com.zain.auraos.ui

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zain.auraos.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnInit = findViewById<TextView>(R.id.btn_initialize_aura)
        btnInit?.setOnClickListener {
            Toast.makeText(this, "AURA OS Core Online // Initializing Systems...", Toast.LENGTH_SHORT).show()
        }
    }
}

