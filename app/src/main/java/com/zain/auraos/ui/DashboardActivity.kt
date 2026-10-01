package com.zain.auraos.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zain.auraos.R

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        findViewById<View>(R.id.card_creative_studio)?.setOnClickListener {
            Toast.makeText(this, "Creative Studio // Neural Engine Ready", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.card_storage_rag)?.setOnClickListener {
            Toast.makeText(this, "Storage RAG // Querying Neural Memory...", Toast.LENGTH_SHORT).show()
        }

        // Opens the Hardware & MIDI Console Screen
        findViewById<View>(R.id.card_hardware_radios)?.setOnClickListener {
            val intent = Intent(this, HardwareConsoleActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        findViewById<View>(R.id.card_swarm_mesh)?.setOnClickListener {
            Toast.makeText(this, "Swarm Mesh // Synchronizing Nodes...", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.card_active_engines)?.setOnClickListener {
            Toast.makeText(this, "Engine Center // 17 of 17 Engines Active", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.nav_center_orb)?.setOnClickListener {
            Toast.makeText(this, "Aura Core Voice Assistant Active", Toast.LENGTH_SHORT).show()
        }
    }
}

