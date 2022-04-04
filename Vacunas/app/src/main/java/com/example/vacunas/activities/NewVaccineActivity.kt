package com.example.vacunas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.example.vacunas.R
import com.example.vacunas.Utils

class NewVaccineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_vaccine)

        findViewById<ImageButton>(R.id.addvac_close).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        findViewById<TextView>(R.id.selected_vaccine).text = Utils.vaccineNames[Utils.selectedVaccine]
    }
}