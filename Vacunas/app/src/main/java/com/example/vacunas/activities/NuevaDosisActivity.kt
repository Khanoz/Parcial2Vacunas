package com.example.vacunas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import com.example.vacunas.R
import com.example.vacunas.Utils
import com.example.vacunas.userVaccine
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class NuevaDosisActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_dosis)

        findViewById<ImageButton>(R.id.addvac_close).setOnClickListener {
            this.finish()
        }
        findViewById<ImageButton>(R.id.addvac_save).setOnClickListener {
            val esquema: String = findViewById<EditText>(R.id.esquema).text.toString()
            val format: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
            val date: Date? = format.parse(findViewById<EditText>(R.id.fecha).text.toString())
            val marca: String = findViewById<EditText>(R.id.marca).text.toString()
            val lote: String = findViewById<EditText>(R.id.lote).text.toString()
            val doctor: String = findViewById<EditText>(R.id.doctor).text.toString()
            val centro: String = findViewById<EditText>(R.id.centroAplicacion).text.toString()
            val domicilio: String = findViewById<EditText>(R.id.domicilio).text.toString()

            val uservacc = userVaccine(domicilio,
                marca, lote, centro, doctor, esquema, date, true, Utils.selectedVaccine
            )

            db.collection("users").document(Utils.userid)
                .collection("userVaccines").document(Utils.selectedVaccine!!.id)
                .set(uservacc)
            val intent = Intent(this, NewVaccineActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}