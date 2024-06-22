package com.example.platzi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class BPrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_bprincipal)

        // Encontrar el nuevo botón en el layout
        val registrarUsuariosButton: Button = findViewById(R.id.btn_registrar_usuarios)

        // Configurar el listener para el botón
        registrarUsuariosButton.setOnClickListener {
            // Crear un Intent para iniciar la nueva actividad
            val intent = Intent(this, CARegistrarUsuario::class.java)  // Aquí puedes decidir qué actividad iniciar
            startActivity(intent)
        }

        val pagoSocioButton: Button = findViewById(R.id.btn_pago_socio)

        pagoSocioButton.setOnClickListener {
            val intent = Intent(this, DAPagoSocio::class.java)
            startActivity(intent)
        }

        val pagoActividadButton: Button = findViewById(R.id.btn_pago_actividad)

        pagoActividadButton.setOnClickListener {
            val intent = Intent(this, FARegistroDeActividad::class.java)
            startActivity(intent)
        }

        val buttonPerfilUsuario: Button = findViewById(R.id.btn_perfil_usuario)
        buttonPerfilUsuario.setOnClickListener {
            val intent = Intent(this, UserProfileActivity::class.java)
            startActivity(intent)
        }

        val cerrarSesionButton: Button = findViewById(R.id.btn_cerrar_sesion)

        cerrarSesionButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
