package com.zain.auraos.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.zain.auraos.R
import com.zain.auraos.databinding.ActivityMainBinding
import com.zain.auraos.service.AuraCoreService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentScreenIndex = 0

    private val screenLayouts = listOf(
        R.layout.screen_01_reactor_hud,
        R.layout.screen_02_voice_duplex,
        R.layout.screen_03_engine_matrix,
        R.layout.screen_04_predictive_timeline,
        R.layout.screen_05_local_indexer,
        R.layout.screen_06_self_healing,
        R.layout.screen_07_swarm_mesh,
        R.layout.screen_08_neural_vault,
        R.layout.screen_09_task_queue,
        R.layout.screen_10_spatial_feed,
        R.layout.screen_11_thermals_governor,
        R.layout.screen_12_ambient_lockscreen,
        R.layout.screen_13_app_dispatcher,
        R.layout.screen_14_memory_sandbox,
        R.layout.screen_15_holographic_overlay
    )

    private val screenLabels = listOf(
        "SCREEN 01 // REACTOR HUD CORE",
        "SCREEN 02 // VOICE DUPLEX CONSOLE",
        "SCREEN 03 // ENGINE MATRIX MONITOR",
        "SCREEN 04 // PREDICTIVE TIMELINE",
        "SCREEN 05 // LOCAL KNOWLEDGE INDEXER",
        "SCREEN 06 // SELF-HEALING RECOVERY LOG",
        "SCREEN 07 // P2P SWARM MESH MAP",
        "SCREEN 08 // NEURAL SECURITY VAULT",
        "SCREEN 09 // AUTONOMOUS TASK QUEUE",
        "SCREEN 10 // SPATIAL SENSOR TELEMETRY",
        "SCREEN 11 // THERMAL & RESOURCE GOVERNOR",
        "SCREEN 12 // AMBIENT LOCKSCREEN",
        "SCREEN 13 // INTENT APP DISPATCHER",
        "SCREEN 14 // MEMORY SANDBOX CONSOLE",
        "SCREEN 15 // HOLOGRAPHIC OVERLAY HUD"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPermissions()
        startAuraBackgroundService()

        loadScreen(currentScreenIndex)

        binding.btnNextScreen.setOnClickListener {
            currentScreenIndex = (currentScreenIndex + 1) % screenLayouts.size
            loadScreen(currentScreenIndex)
        }

        binding.btnPrevScreen.setOnClickListener {
            currentScreenIndex = if (currentScreenIndex - 1 < 0) screenLayouts.size - 1 else currentScreenIndex - 1
            loadScreen(currentScreenIndex)
        }
    }

    private fun loadScreen(index: Int) {
        binding.screenContainer.removeAllViews()
        val view = LayoutInflater.from(this).inflate(screenLayouts[index], binding.screenContainer, false)
        binding.screenContainer.addView(view)
        binding.tvActiveScreenLabel.text = screenLabels[index]
    }

    private fun checkPermissions() {
        val permissions = mutableListOf(Manifest.permission.RECORD_AUDIO)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions.add(Manifest.permission.POST_NOTIFICATIONS)
        }

        val missing = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        if (missing.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, missing.toTypedArray(), 1001)
        }
    }

    private fun startAuraBackgroundService() {
        val serviceIntent = Intent(this, AuraCoreService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }
    }
}
