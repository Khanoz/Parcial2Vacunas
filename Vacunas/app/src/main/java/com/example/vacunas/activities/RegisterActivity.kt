package com.example.vacunas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.example.vacunas.R
import com.example.vacunas.fragments.RegisterFragment
import com.example.vacunas.Utils
import com.example.vacunas.userData
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        findViewById<ImageButton>(R.id.goback).setOnClickListener {
            this.finish()
        }
        findViewById<Button>(R.id.register_user).setOnClickListener {
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            val birthdate: Date =
                formatter.parse(findViewById<EditText>(R.id.birthdate).text.toString())!!

            val user = userData(
                findViewById<EditText>(R.id.name).text.toString(),
                findViewById<EditText>(R.id.last_name_F).text.toString(),
                findViewById<EditText>(R.id.last_name_M).text.toString(),
                findViewById<EditText>(R.id.gender).text.toString(),
                birthdate,
                findViewById<EditText>(R.id.weight).text.toString(),
                findViewById<EditText>(R.id.edad_gestal).text.toString(),
                findViewById<EditText>(R.id.blood_type).text.toString()
                )
            val email = findViewById<EditText>(R.id.email).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()
            Utils.registerUser(user, email, password)
            this.finish()
        }
        supportFragmentManager.beginTransaction().replace(R.id.container, RegisterFragment()).commit()
    }
}