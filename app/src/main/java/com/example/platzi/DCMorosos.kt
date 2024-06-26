package com.example.platzi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.platzi.model.Delinquent
import com.example.platzi.adapter.DelinquentAdapter

class MorososActivity : AppCompatActivity() {

    private lateinit var listViewMorosos: ListView
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var btnAtras: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dcmorosos)

        listViewMorosos = findViewById(R.id.listViewMorosos)
        dbHelper = DatabaseHelper.getInstance(this)
        btnAtras = findViewById(R.id.btn_atras)

        val morososList = getMorosos() // Obtener la lista de socios morosos

        val adapter = DelinquentAdapter(this, morososList)
        listViewMorosos.adapter = adapter

        btnAtras.setOnClickListener {
            val intent = Intent(this, DAPagoSocio::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getMorosos(): List<Delinquent> {
        val morososList = mutableListOf<Delinquent>()

        val cursor = dbHelper.getAllMorosos()
        cursor.use {
            if (it.moveToFirst()) {
                do {
                    val nombre = it.getString(it.getColumnIndexOrThrow("name"))
                    val apellido = it.getString(it.getColumnIndexOrThrow("last_name"))
                    val tipoDocumento = it.getString(it.getColumnIndexOrThrow("doc_type"))
                    val numeroDocumento = it.getString(it.getColumnIndexOrThrow("doc_number"))
                    val cuotasAdeudadas = it.getInt(it.getColumnIndexOrThrow("cuotas_adeudadas"))
                    val cuotaMensual = dbHelper.getMonthlyFee(numeroDocumento)

                    val totalAdeudado = cuotasAdeudadas * cuotaMensual

                    morososList.add(
                        Delinquent(
                            nombre,
                            apellido,
                            tipoDocumento,
                            numeroDocumento,
                            cuotasAdeudadas,
                            totalAdeudado
                        )
                    )
                } while (it.moveToNext())
            } else {
                // Si no hay usuarios morosos en la base de datos, agregar usuarios ficticios con cuotas mensuales ficticias
                morososList.addAll(getFictitiousDelinquents())
            }
        }

        return morososList
    }

    private fun getFictitiousDelinquents(): List<Delinquent> {
        // Aquí defines usuarios ficticios con cuotas mensuales ficticias
        val fictitiousUsers = listOf(
            Delinquent("Juan", "Pérez", "DNI", "12345678", 3, 15000.0),
            Delinquent("María", "Gómez", "CI", "87654321", 2, 10000.0),
            Delinquent("Pedro", "Martínez", "DNI", "56789123", 1, 5000.0)
            // Agrega más usuarios ficticios según sea necesario
        )
        return fictitiousUsers
    }

}
