package com.example.gestionhotelerampr

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ReservaSalonActivity : AppCompatActivity() {

    private lateinit var spinnerFecha: Spinner
    private lateinit var spinnerTipoEvento: Spinner
    private lateinit var spinnerTipoCocina: Spinner
    private lateinit var layoutCongreso: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva_salon)

        spinnerFecha = findViewById(R.id.spinnerFecha)
        spinnerTipoEvento = findViewById(R.id.spinnerTipoEvento)
        spinnerTipoCocina = findViewById(R.id.spinnerTipoCocina)
        layoutCongreso = findViewById(R.id.layoutCongreso)

        setupSpinners()
        setupEventListeners()
    }

    private fun setupSpinners() {
        // Configurar spinner de fecha
        val fechas = generateFechas()
        val adapterFecha = ArrayAdapter(this, android.R.layout.simple_spinner_item, fechas)
        spinnerFecha.adapter = adapterFecha

        // Configurar spinner de tipo de evento
        val tiposEvento = arrayOf("Banquete", "Jornada", "Congreso")
        val adapterTipoEvento = ArrayAdapter(this, android.R.layout.simple_spinner_item, tiposEvento)
        spinnerTipoEvento.adapter = adapterTipoEvento

        // Configurar spinner de tipo de cocina
        val tiposCocina = arrayOf("Bufé", "Carta", "Pedir cita con el chef", "No precisa")
        val adapterTipoCocina = ArrayAdapter(this, android.R.layout.simple_spinner_item, tiposCocina)
        spinnerTipoCocina.adapter = adapterTipoCocina
    }

    private fun setupEventListeners() {
        spinnerTipoEvento.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                layoutCongreso.visibility = if (position == 2) View.VISIBLE else View.GONE
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        findViewById<Button>(R.id.btnReservar).setOnClickListener {
            // Aquí iría la lógica para procesar la reserva
            Toast.makeText(this, "Reserva realizada", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateFechas(): List<String> {
        // Generar fechas para los próximos 30 días
        val fechas = mutableListOf<String>()
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        for (i in 0 until 30) {
            fechas.add(dateFormat.format(calendar.time))
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        return fechas
    }
}
